package com.wuhulala.springboot.chapter1.i18n;

import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;


/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2019/3/29<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class MyLocalResolver implements LocaleResolver {
    private static final String LANG = "lang";
    private static final String LANG_SESSION = "lang_session";

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String lang = request.getHeader(LANG);
        Locale locale = Locale.getDefault();
        if (lang != null && !lang.equals("")) {
            String[] language = lang.split("_");
            locale = new Locale(language[0], language[1]);

            HttpSession session = request.getSession();
            session.setAttribute(LANG_SESSION, locale);
        } else {
            HttpSession session = request.getSession();
            Locale localeInSession = (Locale) session.getAttribute(LANG_SESSION);
            if (localeInSession != null) {
                locale = localeInSession;
            }
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
