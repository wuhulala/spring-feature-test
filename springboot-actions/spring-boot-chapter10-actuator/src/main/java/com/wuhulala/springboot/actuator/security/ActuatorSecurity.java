package com.wuhulala.springboot.actuator.security;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ActuatorSecurity extends WebSecurityConfigurerAdapter {

	/**
	 * 对暴露的端点进行保护
	 * @param http
	 * @throws Exception
	 */
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests()
//				.anyRequest().hasRole("ENDPOINT_ADMIN")
//				.and()
//			.httpBasic();
//	}

	/**
	 * 如果开启了spring-security，需要指定以下的代码以允许所有人可以访问
	 * @param http
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests()
				.anyRequest().permitAll();
	}

}