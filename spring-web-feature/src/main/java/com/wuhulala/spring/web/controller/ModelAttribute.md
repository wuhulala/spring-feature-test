## 使用
@ModelAttribute最主要的作用是将数据添加到模型对象中，用于视图页面展示时使用，可以加载方法参数、返回值、方法生命上面

全局，即通用逻辑，可以做一些通用属性的绑定与暴露
```
@ControllerAdvice
public class GlobalModelAttributeAdvice {

    @ModelAttribute
    public void bindModelAttribute(Model model){
        System.out.println(">>>>>>>>>>>>>>>enter global modelAttribute method .....");
        model.addAttribute("operName", "wuhulala--golbal");
        model.addAttribute("globalFlag", "golbal");
    }


    @ModelAttribute
    public void bindModelAttribute2(Model model){
        System.out.println(">>>>>>>>>>>>>>>enter global modelAttribute method2 .....");
        model.addAttribute("operName", "wuhulala--golbal2");
        model.addAttribute("globalFlag2", "第二个全局方法");
    }

}

```
个性化Controller 绑定，可以对本Controller 业务逻辑进行通用绑定
```
@Controller
public class ModelAttributeController {

    @ModelAttribute
    public void bindModelAttribute(Model model){
        System.out.println(">>>>>>>>>>>>>>>enter modelAttribute method .....");
        // 会覆盖全局
        model.addAttribute("operName", "wuhulala");
    }


    @RequestMapping("/modelAttribute")
    @ResponseBody
    public String index(Model model){
        System.out.println(model.asMap());
        return "modelAttribute";
    }

//    >>>>>>>>>>>>>>>enter global modelAttribute method .....
//    >>>>>>>>>>>>>>>enter global modelAttribute method2 .....
//    >>>>>>>>>>>>>>>enter modelAttribute method .....
//    {operName=wuhulala, globalFlag=golbal, globalFlag2=第二个全局方法}
}
```
其它用法参考 https://blog.csdn.net/leo3070/article/details/81046383


## 初始化
### 全局InitBinder 
```
    // 查询所有bean的 有ModelAttribute注解并且没有RequestMapping 的注解
	public static final MethodFilter MODEL_ATTRIBUTE_METHODS = method ->
			(!AnnotatedElementUtils.hasAnnotation(method, RequestMapping.class) &&
					AnnotatedElementUtils.hasAnnotation(method, ModelAttribute.class));
```
RequestMappingHandlerAdapter#afterPropertiesSet => RequestMappingHandlerAdapter#initControllerAdviceCache
```
    private void initControllerAdviceCache() {
    		if (getApplicationContext() == null) {
    			return;
    		}
    
            // 获取所有加了 @ControllerAdvice 注解的 Bean
    		List<ControllerAdviceBean> adviceBeans = ControllerAdviceBean.findAnnotatedBeans(getApplicationContext());
    		AnnotationAwareOrderComparator.sort(adviceBeans);
    
    		List<Object> requestResponseBodyAdviceBeans = new ArrayList<>();
    
    		for (ControllerAdviceBean adviceBean : adviceBeans) {
    			Class<?> beanType = adviceBean.getBeanType();
    			if (beanType == null) {
    				throw new IllegalStateException("Unresolvable type for ControllerAdviceBean: " + adviceBean);
    			}
    			// 获取所有的Bean的ModelAtrribute注解
    			Set<Method> attrMethods = MethodIntrospector.selectMethods(beanType, MODEL_ATTRIBUTE_METHODS);
    			if (!attrMethods.isEmpty()) {
    				this.modelAttributeAdviceCache.put(adviceBean, attrMethods);
    			}
    			// ....... 省略
    		}
    
    		// ... 省略
    	}

```

## 应用逻辑
