package com.wuhulala.spring.web.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * WebBinder测试
 *FormContentFilter
 * @author wuhulala<br>
 * @date 2019/2/19<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
@Controller
public class WebBinderTestController {

    // 个性化 initBinder
    // value 表示只绑定表单的固定key
    @InitBinder("date")
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }


    // http://localhost:8080/?date=20180819&date2=2018-09-19
    @RequestMapping("/")
    @ResponseBody
    private String hello(HttpServletRequest request, Date date, Date date2) {
        System.out.println("----------------------------------------");
        showParams(request);
        getHeadersInfo(request);
        System.out.println("----------------------------------------");
        return "hello";
    }


    private void showParams(HttpServletRequest request) {
        Map map = new HashMap();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();

            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }

        Set<Map.Entry<String, String>> set = map.entrySet();
        System.out.println("------------------------------");
        for (Map.Entry entry : set) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("------------------------------");

    }

    private Map<String, String> getHeadersInfo(HttpServletRequest request) {
        System.out.println("---------------Headers-------------------");
        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            System.out.println(key + " : " + value);
            map.put(key, value);
        }
        System.out.println("---------------Headers-------------------");
        return map;
    }
}
