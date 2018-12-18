package com.wuhulala.spring.session.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2018/12/18<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
@Configuration
public class ExtConfig {

    @Bean
    public DefaultCookieSerializer cookieSerializer(){
        DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();
        defaultCookieSerializer.setCookiePath("wuhulala.com");
        defaultCookieSerializer.setCookieName("_u_");
        defaultCookieSerializer.setUseHttpOnlyCookie(true);
        //defaultCookieSerializer.setUseSecureCookie(true);
        return defaultCookieSerializer;
    }

}
