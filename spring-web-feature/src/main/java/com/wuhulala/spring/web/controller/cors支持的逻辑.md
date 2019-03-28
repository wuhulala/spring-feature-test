# spring 针对跨域的解决方案

```
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {

		if (CorsUtils.isCorsRequest(request)) {
			CorsConfiguration corsConfiguration = this.configSource.getCorsConfiguration(request);
			if (corsConfiguration != null) {
				boolean isValid = this.processor.processRequest(corsConfiguration, request, response);
				if (!isValid || CorsUtils.isPreFlightRequest(request)) {
					return;
				}
			}
		}

		filterChain.doFilter(request, response);
	}
```