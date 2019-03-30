package com.wuhulala.springboot.chapter1.i18n.support;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 设置国际化
 *
 * @author wuhulala<br>
 * @date 2019/3/30<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class I18nInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        settingLocale(request);
        return true;
    }

    private void settingLocale(HttpServletRequest request) {
        ThreadContext.get().setLocale(request.getLocale());
    }

}
