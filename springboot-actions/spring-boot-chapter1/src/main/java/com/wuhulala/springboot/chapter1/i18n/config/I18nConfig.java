package com.wuhulala.springboot.chapter1.i18n.config;

import com.wuhulala.springboot.chapter1.i18n.support.I18nInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2019/3/30<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
@Configuration
public class I18nConfig implements WebMvcConfigurer {

    @Bean
    public I18nInterceptor i18nInterceptor(){
        return new I18nInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(i18nInterceptor());
//        registry.addInterceptor(new LocaleChangeInterceptor());
//        registry.addInterceptor(new ThemeChangeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");
//        registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/secure/*");
    }

}
