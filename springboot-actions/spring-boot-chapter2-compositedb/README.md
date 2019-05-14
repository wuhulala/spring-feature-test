## 简介

实现一个可以动态切换数据类型的demo

### 方案1

每次启动的时候，根据`db.type`配置，加载对应的路径下的xml

```
db.type=mysql
mybatis.mapper-locations=classpath:mapper/${db.type}/*Mapper.xml
```

### 方案2

配置多个`Configuration`、`SqlSessionFactory`进行隔离。
在Spring容器里面，Mapper动态依赖注入的问题?如何解决


