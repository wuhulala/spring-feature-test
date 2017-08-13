1. 设置启动时间、设置关闭状态为false，启动状态为true
2. 在上下文环境中初始化占位符属性，默认空实现，在web环境中才有实现
3. 判断必需的属性是否存在，必需的属性可以通过context.getEnvironment().setRequiredProperties()设置

