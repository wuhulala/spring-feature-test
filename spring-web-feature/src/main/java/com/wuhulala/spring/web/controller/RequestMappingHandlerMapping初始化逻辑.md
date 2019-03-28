

```
    @Override
	protected void onRefresh(ApplicationContext context) {
		initStrategies(context);
	}

	/**
	 * Initialize the strategy objects that this servlet uses.
	 * <p>May be overridden in subclasses in order to initialize further strategy objects.
	 */
	protected void initStrategies(ApplicationContext context) {
		initMultipartResolver(context);
		initLocaleResolver(context);
		initThemeResolver(context);
		initHandlerMappings(context);
		initHandlerAdapters(context);
		initHandlerExceptionResolvers(context);
		initRequestToViewNameTranslator(context);
		initViewResolvers(context);
		initFlashMapManager(context);
	}
	
	private void initHandlerMappings(ApplicationContext context) {
    		this.handlerMappings = null;
            // 默认为true，也就是默认进这里
    		if (this.detectAllHandlerMappings) {
    			// Find all HandlerMappings in the ApplicationContext, including ancestor contexts.
    			Map<String, HandlerMapping> matchingBeans =
    					BeanFactoryUtils.beansOfTypeIncludingAncestors(context, HandlerMapping.class, true, false);
    			if (!matchingBeans.isEmpty()) {
    				this.handlerMappings = new ArrayList<>(matchingBeans.values());
    				// We keep HandlerMappings in sorted order.
    				AnnotationAwareOrderComparator.sort(this.handlerMappings);
    			}
    		}
    		else {
    			try {
    				HandlerMapping hm = context.getBean(HANDLER_MAPPING_BEAN_NAME, HandlerMapping.class);
    				this.handlerMappings = Collections.singletonList(hm);
    			}
    			catch (NoSuchBeanDefinitionException ex) {
    				// Ignore, we'll add a default HandlerMapping later.
    			}
    		}
    
    		// Ensure we have at least one HandlerMapping, by registering
    		// a default HandlerMapping if no other mappings are found.
    		if (this.handlerMappings == null) {
    			this.handlerMappings = getDefaultStrategies(context, HandlerMapping.class);
    			if (logger.isTraceEnabled()) {
    				logger.trace("No HandlerMappings declared for servlet '" + getServletName() +
    						"': using default strategies from DispatcherServlet.properties");
    			}
    		}
    	}
```

```
protected <T> List<T> getDefaultStrategies(ApplicationContext context, Class<T> strategyInterface) {
		String key = strategyInterface.getName();
		String value = defaultStrategies.getProperty(key);
		if (value != null) {
			String[] classNames = StringUtils.commaDelimitedListToStringArray(value);
			List<T> strategies = new ArrayList<>(classNames.length);
			for (String className : classNames) {
				try {
					Class<?> clazz = ClassUtils.forName(className, DispatcherServlet.class.getClassLoader());
					Object strategy = createDefaultStrategy(context, clazz);
					strategies.add((T) strategy);
				}
				catch (ClassNotFoundException ex) {
					throw new BeanInitializationException(
							"Could not find DispatcherServlet's default strategy class [" + className +
							"] for interface [" + key + "]", ex);
				}
				catch (LinkageError err) {
					throw new BeanInitializationException(
							"Unresolvable class definition for DispatcherServlet's default strategy class [" +
							className + "] for interface [" + key + "]", err);
				}
			}
			return strategies;
		}
		else {
			return new LinkedList<>();
		}
	}
```