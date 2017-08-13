1. 初始化转换服务，http://www.cnblogs.com/jyyzzjl/p/5478620.html
2. 注册一个嵌入式的value解析器，默认是一个String，用来解析${}
3. Initialize LoadTimeWeaverAware beans early to allow for registering their transformers early
4. 停止使用临时ClassLoader进行类型匹配。
5. 冻结所有bean定义元数据，不能修改
6. 初始化所有非懒加载的bean