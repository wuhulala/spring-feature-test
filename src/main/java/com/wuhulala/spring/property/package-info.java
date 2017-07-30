/**
 * author： wuhulala
 * date： 2017/7/30
 * version: 1.0
 * description: 用于在spring容器的bean注册完之后获取属性配置<br/>
 *    <p> 假设你已经知道了属性注入是在beanfactorypostprocessor时完成的 !!!
 *    在对于beanpostprocessor通过autowired注解自动注入的bean，如果这个bean不是特殊的
 *    ，比如Environment这种，不需要在经过bean后置处理器，否则此beanpostprocessor会自动跳过过不起走用
 *    </p>
 *
 */
package com.wuhulala.spring.property;