package com.wuhulala.springboot.chapter1.i18n;

import com.wuhulala.springboot.chapter1.i18n.support.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * 错误号翻译器
 *
 * @author wuhulala<br>
 * @date 2019/3/30<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
@Component
public class ErrorInfoTranslator {

    private static MessageSource messageSource;

    @Autowired
    public ErrorInfoTranslator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public static String getErrorInfo(String errCode, Object... errParams) {
        return messageSource.getMessage(errCode, errParams, ThreadContext.get().getLocale());
    }

    public static String getErrorInfo(Locale locale, String errCode, Object... errParams) {
        return messageSource.getMessage(errCode, errParams, locale);
    }


}
