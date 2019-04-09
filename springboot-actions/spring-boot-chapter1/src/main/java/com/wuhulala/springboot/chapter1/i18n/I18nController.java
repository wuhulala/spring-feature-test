package com.wuhulala.springboot.chapter1.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class I18nController {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(I18nController.class);

    private final MessageSource messageSource;

    @Autowired
    public I18nController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * 设置当前的返回信息
     *
     * @param request
     * @param code
     * @return
     */
    @RequestMapping("/i18n/{code}")
    public String getMessage(HttpServletRequest request, @PathVariable String code, @RequestParam("") Object[] params) {
        String result = null;
        try {
            result = ErrorInfoTranslator.getErrorInfo(code, params);
        } catch (NoSuchMessageException e) {
            LOGGER.error("Cannot find the error message of internationalization, return the original error message.");
        }
        if (result == null) {
            return code;
        }
        return result;
    }



}