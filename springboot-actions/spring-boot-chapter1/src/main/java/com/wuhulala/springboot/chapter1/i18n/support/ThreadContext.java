package com.wuhulala.springboot.chapter1.i18n.support;

import java.util.Locale;

public class ThreadContext extends MapSupport<String, Object>{

    private static final ThreadLocal<ThreadContext> ThreadContextLocal = ThreadLocal.withInitial(ThreadContext::new);

    public static final String LOCALE_KEY = "locale";
    /**
     * 当前线程id
     */
    private final long currentThreadId;
    /**
     * 当前开始执行时间
     */
    private final long beginTime;


    /**
     * 实例线程上下文对象
     */
    private ThreadContext() {
        currentThreadId = Thread.currentThread().getId();
        beginTime = System.currentTimeMillis();
    }

    /**
     * 设置当前线程名
     *
     * @param name
     */
    public ThreadContext setName(String name) {
        Thread.currentThread().setName(name);
        return this;
    }

    /**
     * 获取当前线程id
     *
     * @return
     */
    public long getCurrentThreadId() {
        return this.currentThreadId;
    }

    /**
     * 获取上下文对象生成时间
     *
     * @return
     */
    public long getBeginTime() {
        return this.beginTime;
    }

    /**
     * 获取当前请求的地区
     */
    public Locale getLocale() {
        return (Locale) get(LOCALE_KEY);
    }

    /**
     * 设置当前请求的地区
     * @param locale 地区
     */
    public void setLocale(Locale locale){
        put(LOCALE_KEY, locale);
    }


    /**
     * 获取当前线程上下文
     *
     * @return
     */
    public static ThreadContext get() {
        return ThreadContextLocal.get();
    }

    /**
     * 清理当前线程上下文
     */
    public static void remove() {
        ThreadContextLocal.remove();
    }


}