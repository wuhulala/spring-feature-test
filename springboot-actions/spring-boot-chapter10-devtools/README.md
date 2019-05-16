## Developer Tools
ps : 官方文档机翻，个别语句不通顺 会改改


依赖
```
<dependencies>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-devtools</artifactId>
		<optional>true</optional>
	</dependency>
</dependencies>
```

## 默认属性
Spring Boot支持的几个库使用缓存来提高性能。
例如，模板引擎缓存已编译的模板以避免重复解析模板文件。
此外，Spring MVC可以在提供静态资源时为响应添加HTTP缓存标头

虽然缓存在生产中非常有用，但在开发过程中可能会适得其反，使您无法看到刚刚在应用程序中进行的更改。
因此，spring-boot-devtools**默认禁用缓存**选项。

缓存选项通常由application.properties文件中的设置配置。
例如，Thymeleaf提供了spring.thymeleaf.cache属性。
spring-boot-devtools模块不需要手动设置这些属性，而是自动应用合理的开发时间配置。


因为在开发Spring MVC和Spring WebFlux应用程序时需要有关Web请求的更多信息，所以开发人员工具将为Web日志记录组启用DEBUG日志记录。
这将为您提供有关传入请求，处理程序正在处理它，响应结果等的信息。如果您希望记录所有请求详细信息（包括可能的敏感信息），您可以打开spring.http.log-request-
详细配置属性。

## 自动重启
### 触发重启

* IDEA 使用 `shift + F9` Rebuild 项目
* Eclipse 直接保存文件直接触发



### 重启 vs 重载
 
 > The restart technology provided by Spring Boot works by using two classloaders. Classes that do not change (for example, those from third-party jars) are loaded into a base classloader. Classes that you are actively developing are loaded into a restart classloader. When the application is restarted, the restart classloader is thrown away and a new one is created. This approach means that application restarts are typically much faster than “cold starts”, since the base classloader is already available and populated.
 
 > If you find that restarts are not quick enough for your applications or you encounter classloading issues, you could consider reloading technologies such as JRebel from ZeroTurnaround. These work by rewriting classes as they are loaded to make them more amenable to reloading.


#### 排除资源
某些资源在更改时不一定需要触发重启。
例如，可以就地编辑Thymeleaf模板。
默认情况下，更改`/META-INF/maven`，`/META-INF/resources`，`/resources`，`/static`，`/public`或`/templates`中的资源不会触发重新启动，但会触发实时重新加载。
如果要自定义这些排除项，可以使用spring.devtools.restart.exclude属性。
例如，要仅排除`/static`和`/public`，您需要设置以下属性：
```
spring.devtools.restart.exclude=static/**,public/**
```
#### 监听额外的路径
当您对不在类路径中的文件进行更改时，您可能希望重新启动或重新加载应用程序。
为此，请使用`spring.devtools.restart.additional-paths`属性配置其他路径以监视更改。
您可以使用前面描述的`spring.devtools.restart.exclude`属性来控制其他路径下的更改是触发完全重新启动还是实时重新加载。

