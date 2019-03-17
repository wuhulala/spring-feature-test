## 初始化binder
RequestMappingHandlerAdapter#afterPropertiesSet => RequestMappingHandlerAdapter#initControllerAdviceCache
```
    private void initControllerAdviceCache() {
    		if (getApplicationContext() == null) {
    			return;
    		}
    
            // 获取所有加了 @ControllerAdvice 注解的 Bean
    		List<ControllerAdviceBean> adviceBeans = ControllerAdviceBean.findAnnotatedBeans(getApplicationContext());
    		AnnotationAwareOrderComparator.sort(adviceBeans);
    
    		List<Object> requestResponseBodyAdviceBeans = new ArrayList<>();
    
    		for (ControllerAdviceBean adviceBean : adviceBeans) {
    			Class<?> beanType = adviceBean.getBeanType();
    			if (beanType == null) {
    				throw new IllegalStateException("Unresolvable type for ControllerAdviceBean: " + adviceBean);
    			}
    			// 获取所有ModelAtrribute注解
    			Set<Method> attrMethods = MethodIntrospector.selectMethods(beanType, MODEL_ATTRIBUTE_METHODS);
    			if (!attrMethods.isEmpty()) {
    				this.modelAttributeAdviceCache.put(adviceBean, attrMethods);
    			}
    			// 搜索所有的@InitBinder注解，说明是用ControllerAdvice实现的InitBinder，这里是全局的InitBinder
    			Set<Method> binderMethods = MethodIntrospector.selectMethods(beanType, INIT_BINDER_METHODS);
    			if (!binderMethods.isEmpty()) {
    				this.initBinderAdviceCache.put(adviceBean, binderMethods);
    			}
    			// TODO RequestBodyAdvice
    			if (RequestBodyAdvice.class.isAssignableFrom(beanType)) {
    				requestResponseBodyAdviceBeans.add(adviceBean);
    			}
    			// TODO ResponseBodyAdvice
    			if (ResponseBodyAdvice.class.isAssignableFrom(beanType)) {
    				requestResponseBodyAdviceBeans.add(adviceBean);
    			}
    		}
    
    		if (!requestResponseBodyAdviceBeans.isEmpty()) {
    			this.requestResponseBodyAdvice.addAll(0, requestResponseBodyAdviceBeans);
    		}
    
    		if (logger.isDebugEnabled()) {
    			int modelSize = this.modelAttributeAdviceCache.size();
    			int binderSize = this.initBinderAdviceCache.size();
    			int reqCount = getBodyAdviceCount(RequestBodyAdvice.class);
    			int resCount = getBodyAdviceCount(ResponseBodyAdvice.class);
    			if (modelSize == 0 && binderSize == 0 && reqCount == 0 && resCount == 0) {
    				logger.debug("ControllerAdvice beans: none");
    			}
    			else {
    				logger.debug("ControllerAdvice beans: " + modelSize + " @ModelAttribute, " + binderSize +
    						" @InitBinder, " + reqCount + " RequestBodyAdvice, " + resCount + " ResponseBodyAdvice");
    			}
    		}
    	}

```


## 执行时
```
protected ModelAndView invokeHandlerMethod(HttpServletRequest request,
			HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {

		ServletWebRequest webRequest = new ServletWebRequest(request, response);
		try {
		    // handlerMethod 就是 RequestMapping注解的方法
		    // 获取binderFactory
			WebDataBinderFactory binderFactory = getDataBinderFactory(handlerMethod);
			ModelFactory modelFactory = getModelFactory(handlerMethod, binderFactory);

			ServletInvocableHandlerMethod invocableMethod = createInvocableHandlerMethod(handlerMethod);
			if (this.argumentResolvers != null) {
				invocableMethod.setHandlerMethodArgumentResolvers(this.argumentResolvers);
			}
			if (this.returnValueHandlers != null) {
				invocableMethod.setHandlerMethodReturnValueHandlers(this.returnValueHandlers);
			}
			invocableMethod.setDataBinderFactory(binderFactory);
			invocableMethod.setParameterNameDiscoverer(this.parameterNameDiscoverer);

			ModelAndViewContainer mavContainer = new ModelAndViewContainer();
			mavContainer.addAllAttributes(RequestContextUtils.getInputFlashMap(request));
			// 按照以下的顺序填充model
			// 检索列为@SessionAttributes的“已知”会话属性。
            // 执行ModelAttribute  
            // 查找同样列为@SessionAttributes的@ModelAttribute方法参数，并确保它们出现在模型中，如果需要则引发异常
			modelFactory.initModel(webRequest, mavContainer, invocableMethod);
			// false
			mavContainer.setIgnoreDefaultModelOnRedirect(this.ignoreDefaultModelOnRedirect);

            // 创建一步的web请求
			AsyncWebRequest asyncWebRequest = WebAsyncUtils.createAsyncWebRequest(request, response);
			asyncWebRequest.setTimeout(this.asyncRequestTimeout);

            // web请求管理器
			WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);
			asyncManager.setTaskExecutor(this.taskExecutor);
			asyncManager.setAsyncWebRequest(asyncWebRequest);
			asyncManager.registerCallableInterceptors(this.callableInterceptors);
			asyncManager.registerDeferredResultInterceptors(this.deferredResultInterceptors);

            // 如果有并发结果
			if (asyncManager.hasConcurrentResult()) {
				Object result = asyncManager.getConcurrentResult();
				mavContainer = (ModelAndViewContainer) asyncManager.getConcurrentResultContext()[0];
				asyncManager.clearConcurrentResult();
				LogFormatUtils.traceDebug(logger, traceOn -> {
					String formatted = LogFormatUtils.formatValue(result, !traceOn);
					return "Resume with async result [" + formatted + "]";
				});
				invocableMethod = invocableMethod.wrapConcurrentResult(result);
			}
			
			// 调用WebBinder
			invocableMethod.invokeAndHandle(webRequest, mavContainer);
			if (asyncManager.isConcurrentHandlingStarted()) {
				return null;
			}

            // 执行
			return getModelAndView(mavContainer, modelFactory, webRequest);
		}
		finally {
			webRequest.requestCompleted();
		}
	}
```

```
    private WebDataBinderFactory getDataBinderFactory(HandlerMethod handlerMethod) throws Exception {
		Class<?> handlerType = handlerMethod.getBeanType();
		// 从缓存中获取
		Set<Method> methods = this.initBinderCache.get(handlerType);
		if (methods == null) {
		    // 查找 当前 Controller 的 InitBinder， 优先级高
			methods = MethodIntrospector.selectMethods(handlerType, INIT_BINDER_METHODS);
			this.initBinderCache.put(handlerType, methods);
		}
		// 获取 全局，即ControllerAdvice 下面的 initBinder 的所有方法
		List<InvocableHandlerMethod> initBinderMethods = new ArrayList<>();
		// 全局的initBinder
		this.initBinderAdviceCache.forEach((clazz, methodSet) -> {
		    // 测试是否匹配
		    // 匹配条件： controller在Advice所在的包下面同一个包、同一个类型、controller上面有注解
			if (clazz.isApplicableToBeanType(handlerType)) {
				Object bean = clazz.resolveBean();
				for (Method method : methodSet) {
					initBinderMethods.add(createInitBinderMethod(bean, method));
				}
			}
		});
		for (Method method : methods) {
			Object bean = handlerMethod.getBean();
			initBinderMethods.add(createInitBinderMethod(bean, method));
		}
		// 创建一个DataBinderFactory
		return createDataBinderFactory(initBinderMethods);
	}

	private InvocableHandlerMethod createInitBinderMethod(Object bean, Method method) {
		InvocableHandlerMethod binderMethod = new InvocableHandlerMethod(bean, method);
		if (this.initBinderArgumentResolvers != null) {
			binderMethod.setHandlerMethodArgumentResolvers(this.initBinderArgumentResolvers);
		}
		// 设置 WebBinder ===> this.webBindingInitializer=ConfigurableWebBindingInitializer
		binderMethod.setDataBinderFactory(new DefaultDataBinderFactory(this.webBindingInitializer));
		binderMethod.setParameterNameDiscoverer(this.parameterNameDiscoverer);
		return binderMethod;
	}
	
	protected InitBinderDataBinderFactory createDataBinderFactory(List<InvocableHandlerMethod> binderMethods)
    			throws Exception {
            // 创建一个binder
            //  getWebBindingInitializer() ==> ConfigurableWebBindingInitializer
    		return new ServletRequestDataBinderFactory(binderMethods, getWebBindingInitializer());
    }

```


// InitBinder，用于标识初始化org.springframework.web.bind.WebDataBinder的方法，可以有多个，会按照声明顺序执行。
// 他可以认为在request的请求体和@RequestMapping对应方法参数绑定之前的一个钩子
// 该方法将用于填充带注释的处理程序方法的命令和表单对象参数。
// 这样的init-binder方法支持RequestMapping支持的所有参数，命令/表单对象和相应的验证结果对象除外。 
// Init-binder方法不能有返回值; 它们通常被宣布为无效