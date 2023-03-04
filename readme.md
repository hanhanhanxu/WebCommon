web项目基础框架：
1、controller统一返回体：在方法上添加@ResultUnite注解，即可统一返回体。例如，业务返回：ok，包装统一返回体后：
{"code":200,"msg":"success","data":"ok"}

2、统一异常处理，任何异常都会被统一异常处理器：GlobalExceptionHandle 拦截处理，包装成统一返回体。例如业务运行时发生异常，返回：
{"code":500,"msg":"服务器异常","data":null}

3、日志全链路记录 traceId，请求进来时生成唯一id在整个调用链路的日志中记录，并记录在响应头的RequestId字段。通过该字段可以知道请求的所有行为，例如：
2023-03-03 09:47:10,592 [traceId:Nmyk5XOg3oEZIyyldY] [http-nio-8080-exec-6] INFO  com.example.webmaas.config.LogAspect- 外层请求类名: com.example.webmaas.controller.HsController
2023-03-03 09:47:10,592 [traceId:Nmyk5XOg3oEZIyyldY] [http-nio-8080-exec-6] INFO  com.example.webmaas.config.LogAspect- 外层请求方法: hs
2023-03-03 09:47:10,592 [traceId:Nmyk5XOg3oEZIyyldY] [http-nio-8080-exec-6] INFO  com.example.webmaas.config.LogAspect- 外层返回data: ok
2023-03-03 09:47:10,593 [traceId:Nmyk5XOg3oEZIyyldY] [http-nio-8080-exec-6] INFO  com.example.webmaas.config.LogAspect- 外层耗时: 0

包括多线程异步处理的日志（webCommon-1-thread-1线程是异步处理线程）：
2023-03-03 09:49:21,626 [traceId:NoRkX17K038S1lYAeW] [http-nio-8080-exec-1] INFO  com.example.webmaas.config.LogAspect- 外层请求类名: com.example.webmaas.controller.HsController
2023-03-03 09:49:21,628 [traceId:NoRkX17K038S1lYAeW] [http-nio-8080-exec-1] INFO  com.example.webmaas.config.LogAspect- 外层请求方法: test3
2023-03-03 09:49:21,639 [traceId:NoRkX17K038S1lYAeW] [webCommon-1-thread-1] INFO  com.example.webmaas.service.TestService- 耗时操作 start...
2023-03-03 09:49:21,690 [traceId:NoRkX17K038S1lYAeW] [http-nio-8080-exec-1] INFO  com.example.webmaas.config.LogAspect- 外层返回data: ok
2023-03-03 09:49:21,691 [traceId:NoRkX17K038S1lYAeW] [http-nio-8080-exec-1] INFO  com.example.webmaas.config.LogAspect- 外层耗时: 7
2023-03-03 09:49:23,651 [traceId:NoRkX17K038S1lYAeW] [webCommon-1-thread-1] INFO  com.example.webmaas.service.TestService- 耗时操作 end...