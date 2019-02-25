Bean配置
```
@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		RequestMappingHandlerAdapter adapter = createRequestMappingHandlerAdapter();
		adapter.setContentNegotiationManager(mvcContentNegotiationManager());
		adapter.setMessageConverters(getMessageConverters());
		adapter.setWebBindingInitializer(getConfigurableWebBindingInitializer());
		adapter.setCustomArgumentResolvers(getArgumentResolvers());
		adapter.setCustomReturnValueHandlers(getReturnValueHandlers());

        // 如果当前类路径已经有jackson2的类，设置一下Request、Response的切面类
		if (jackson2Present) {
			adapter.setRequestBodyAdvice(Collections.singletonList(new JsonViewRequestBodyAdvice()));
			adapter.setResponseBodyAdvice(Collections.singletonList(new JsonViewResponseBodyAdvice()));
		}

		AsyncSupportConfigurer configurer = new AsyncSupportConfigurer();
		configureAsyncSupport(configurer);
		if (configurer.getTaskExecutor() != null) {
			adapter.setTaskExecutor(configurer.getTaskExecutor());
		}
		if (configurer.getTimeout() != null) {
			adapter.setAsyncRequestTimeout(configurer.getTimeout());
		}
		adapter.setCallableInterceptors(configurer.getCallableInterceptors());
		adapter.setDeferredResultInterceptors(configurer.getDeferredResultInterceptors());

		return adapter;
	}

```

Bean初始化完成
```
    @Override
	public void afterPropertiesSet() {
		// 初始化Controller的切面
		initControllerAdviceCache();

        // 初始化入参解析器，比如我们的@RequestParam @RequestHeader @PathVariable
		if (this.argumentResolvers == null) {
			List<HandlerMethodArgumentResolver> resolvers = getDefaultArgumentResolvers();
			this.argumentResolvers = new HandlerMethodArgumentResolverComposite().addResolvers(resolvers);
		}
		// 初始化InitBind方法的参数解析器，比如void initBinder(Model model, ModelAndView mav),这样mav会被自动赋值
		if (this.initBinderArgumentResolvers == null) {
			List<HandlerMethodArgumentResolver> resolvers = getDefaultInitBinderArgumentResolvers();
			this.initBinderArgumentResolvers = new HandlerMethodArgumentResolverComposite().addResolvers(resolvers);
		}
		// 初始化出参解析器
		if (this.returnValueHandlers == null) {
			List<HandlerMethodReturnValueHandler> handlers = getDefaultReturnValueHandlers();
			this.returnValueHandlers = new HandlerMethodReturnValueHandlerComposite().addHandlers(handlers);
		}
	}

```

处理请求

```## 执行时
   ```
   protected ModelAndView invokeHandlerMethod(HttpServletRequest request,
   			HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
        // handlerMethod 就是 RequestMapping注解的方法,如下面的hello
        // @RequestMapping("/")
        // @ResponseBody
        // private String hello(Date date, Date date2){
        //     System.out.println(date);
        //     return "hello";
        // }
        
   		ServletWebRequest webRequest = new ServletWebRequest(request, response);
   		try {
   		    // 获取WebDataBinderFactory，绑定request 和 参数（Java）之间，
   		    // 也就是一段url?date=20181011=>date = Sun Aug 19 00:00:00 CST 2018
   		    // 这种1:1的关系的时候，spring封装了一个对象，叫做WebDataBinder
   		    
   		    // 这里面做了两个事：
   		    // 1. 查询当前方法匹配的所有InitBinder方法，并且每个InitBinder注解对应的方法会被创建为InvocableHandlerMethod，并在真实方法开始之前
   		    // 2. 创建一个 ServletRequestDataBinderFactory
   			WebDataBinderFactory binderFactory = getDataBinderFactory(handlerMethod);
   			ModelFactory modelFactory = getModelFactory(handlerMethod, binderFactory);
   
            // 创建一个Servlet的可执行方法
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
   			
   			// 在重定向之前从请求返回只读“输入”闪存属性
   			mavContainer.addAllAttributes(RequestContextUtils.getInputFlashMap(request));
   			// 按照以下的顺序填充model
   			// a. 检索@SessionAttributes上已经可以使用的会话属性。
            // b. 执行 @ModelAttribute 注释的方法 
            // c. 查找同样列为@SessionAttributes的@ModelAttribute方法参数，并确保它们出现在模型中，如果需要则引发异常
   			modelFactory.initModel(webRequest, mavContainer, invocableMethod);
   			// 是否在重定向的时候忽略默认的Model ；；；默认 false
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
   			
   			// 执行方法,真正的开始执行，之前都是在为这个做准备
   			// 分为两步 1. 处理逻辑执行  2. 对返回值进行处理
   			invocableMethod.invokeAndHandle(webRequest, mavContainer);
   			if (asyncManager.isConcurrentHandlingStarted()) {
   				return null;
   			}
   
            // 
   			return getModelAndView(mavContainer, modelFactory, webRequest);
   		}
   		finally {
   			webRequest.requestCompleted();
   		}
   	}

```


```
        public void invokeAndHandle(ServletWebRequest webRequest, ModelAndViewContainer mavContainer,
			Object... providedArgs) throws Exception {
        // 再执行
		Object returnValue = invokeForRequest(webRequest, mavContainer, providedArgs);
		setResponseStatus(webRequest);

		if (returnValue == null) {
			if (isRequestNotModified(webRequest) || getResponseStatus() != null || mavContainer.isRequestHandled()) {
				mavContainer.setRequestHandled(true);
				return;
			}
		}
		else if (StringUtils.hasText(getResponseStatusReason())) {
			mavContainer.setRequestHandled(true);
			return;
		}

		mavContainer.setRequestHandled(false);
		Assert.state(this.returnValueHandlers != null, "No return value handlers");
		try {
		    // 返回值处理器 处理，也就是RequestMappingAdapter初始化时设置的
			this.returnValueHandlers.handleReturnValue(
					returnValue, getReturnValueType(returnValue), mavContainer, webRequest);
		}
		catch (Exception ex) {
			if (logger.isTraceEnabled()) {
				logger.trace(formatErrorForReturnValue(returnValue), ex);
			}
			throw ex;
		}
	}
```

```
	@Nullable
	public Object invokeForRequest(NativeWebRequest request, @Nullable ModelAndViewContainer mavContainer,
			Object... providedArgs) throws Exception {
        // 获取方法的所有参数值
		Object[] args = getMethodArgumentValues(request, mavContainer, providedArgs);
		if (logger.isTraceEnabled()) {
			logger.trace("Arguments: " + Arrays.toString(args));
		}
		return doInvoke(args);
	}
```

```
	/**
	 * 从request中解析出handler对应方法的参数值，
	 * @since 5.1.2
	 */
	protected Object[] getMethodArgumentValues(NativeWebRequest request, @Nullable ModelAndViewContainer mavContainer,
			Object... providedArgs) throws Exception {

		if (ObjectUtils.isEmpty(getMethodParameters())) {
			return EMPTY_ARGS;
		}
		// 获取所有方法
		MethodParameter[] parameters = getMethodParameters();
		Object[] args = new Object[parameters.length];
		for (int i = 0; i < parameters.length; i++) {
			MethodParameter parameter = parameters[i];
			// 
			parameter.initParameterNameDiscovery(this.parameterNameDiscoverer);
			// 查询args
			args[i] = findProvidedArgument(parameter, providedArgs);
			if (args[i] != null) {
				continue;
			}
			if (!this.resolvers.supportsParameter(parameter)) {
				throw new IllegalStateException(formatArgumentError(parameter, "No suitable resolver"));
			}
			try {
			    // 由所有的解析器去解析这个东西
				args[i] = this.resolvers.resolveArgument(parameter, mavContainer, request, this.dataBinderFactory);
			}
			catch (Exception ex) {
				// Leave stack trace for later, exception may actually be resolved and handled..
				if (logger.isDebugEnabled()) {
					String error = ex.getMessage();
					if (error != null && !error.contains(parameter.getExecutable().toGenericString())) {
						logger.debug(formatArgumentError(parameter, error));
					}
				}
				throw ex;
			}
		}
		return args;
	}
```

解析并把@InitBinder对应的方法进行执行
![initbinder执行时机.png]()
HandlerMethodArgumentResolverComposite#resolveArgument
```
public Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {

		HandlerMethodArgumentResolver resolver = getArgumentResolver(parameter);
		if (resolver == null) {
			throw new IllegalArgumentException(
					"Unsupported parameter type [" + parameter.getParameterType().getName() + "]." +
							" supportsParameter should be called first.");
		}
		return resolver.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
	}
```

```
public final Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {

		NamedValueInfo namedValueInfo = getNamedValueInfo(parameter);
		MethodParameter nestedParameter = parameter.nestedIfOptional();

		Object resolvedName = resolveStringValue(namedValueInfo.name);
		if (resolvedName == null) {
			throw new IllegalArgumentException(
					"Specified name must not resolve to null: [" + namedValueInfo.name + "]");
		}
        
		Object arg = resolveName(resolvedName.toString(), nestedParameter, webRequest);
		if (arg == null) {
			if (namedValueInfo.defaultValue != null) {
				arg = resolveStringValue(namedValueInfo.defaultValue);
			}
			else if (namedValueInfo.required && !nestedParameter.isOptional()) {
				handleMissingValue(namedValueInfo.name, nestedParameter, webRequest);
			}
			arg = handleNullValue(namedValueInfo.name, arg, nestedParameter.getNestedParameterType());
		}
		else if ("".equals(arg) && namedValueInfo.defaultValue != null) {
			arg = resolveStringValue(namedValueInfo.defaultValue);
		}

		if (binderFactory != null) {
		    // 创建Binder , 
		    // 0. 创建一个WebDataBinder
		    // 1. 在这里会执行InitBinder对应的方法
		    // 2.
			WebDataBinder binder = binderFactory.createBinder(webRequest, null, namedValueInfo.name);
			try {
				arg = binder.convertIfNecessary(arg, parameter.getParameterType(), parameter);
			}
			catch (ConversionNotSupportedException ex) {
				throw new MethodArgumentConversionNotSupportedException(arg, ex.getRequiredType(),
						namedValueInfo.name, parameter, ex.getCause());
			}
			catch (TypeMismatchException ex) {
				throw new MethodArgumentTypeMismatchException(arg, ex.getRequiredType(),
						namedValueInfo.name, parameter, ex.getCause());

			}
		}

		handleResolvedValue(arg, namedValueInfo.name, parameter, mavContainer, webRequest);

		return arg;
	}

```
```

```


```
	/**
	 * Invoke the handler method with the given argument values.
	 */
	@Nullable
	protected Object doInvoke(Object... args) throws Exception {
		ReflectionUtils.makeAccessible(getBridgedMethod());
		try {
			return getBridgedMethod().invoke(getBean(), args);
		}
		catch (IllegalArgumentException ex) {
			assertTargetBean(getBridgedMethod(), getBean(), args);
			String text = (ex.getMessage() != null ? ex.getMessage() : "Illegal argument");
			throw new IllegalStateException(formatInvokeError(text, args), ex);
		}
		catch (InvocationTargetException ex) {
			// Unwrap for HandlerExceptionResolvers ...
			Throwable targetException = ex.getTargetException();
			if (targetException instanceof RuntimeException) {
				throw (RuntimeException) targetException;
			}
			else if (targetException instanceof Error) {
				throw (Error) targetException;
			}
			else if (targetException instanceof Exception) {
				throw (Exception) targetException;
			}
			else {
				throw new IllegalStateException(formatInvokeError("Invocation failure", args), targetException);
			}
		}
	}
```