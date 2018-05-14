# http
-  设计成支持协议的许多特性

- 只涉及流处理与消息解析
- 一个消息解析成消息头和消息主体,但不解析具体的消息头或消息主体
    - 消息头,键名小写,键值不能修改

## http.Agent
- 负责为 HTTP 客户端管理连接的持续与复用
- 它为一个给定的主机与端口维护着一个等待请求的队列,且为每个请求重复使用一个单一的`socket`连接直到队列为空,此时`socket`会被销毁或被放入一个连接池中,在连接池中等待被有着相同主机与端口的请求再次使用

## new Agent([options])

- options `<Object>` 
    - keepAlive `<boolean>`
        - 保持`socket`可用即使没有请求
        - 默认:`false`
    - keepAliveMsecs `<number>` 
        - 指定`TCP Keep-Alive`数据包的初始延迟
        - 默认:`1000`
        > keepAlive为true时有效
    - maxSockets `<number>`
        - 每个主机允许的最大`socket`数量
            - 默认:`Infinity`
    - maxFreeSockets `<number>`
        - 在空闲状态下允许打开的最大`socket`数量
        - 默认:`256`
>  `http.request()`使用的都为默认值

## agent.createConnection(options[, callback])
- 创建一个用于`HTTP`请求的`socket`或流
    - options `<Object>` 
        - 包含连接详情的选项
    - callback <Function> 
        - 接收被创建的`socket`的回调函数
        - 拥有`err`,`stream`两个参数
    - return: `<net.Socket>`

## agent.keepSocketAlive(socket)
- 在`socket`被请求分离的时候调用
- 可能被代理持续使用

## agent.reuseSocket(socket, request)
- `keep-alive`选项被保持持久化, 在`socket`附加到`request`时调用

## agent.destroy()
- 销毁当前正被代理使用的任何`socket`

## agent.freeSockets
- 包含当前正在等待被启用了`keepAlive`的代理使用的`socket`数组
> 不要修改该属性

## agent.getName(options)
- 为请求选项的集合获取一个唯一的名称
- 用来判断一个连接是否可以被复用
- options `<Object>` 
    - host `<string>` 
        - 请求发送至的服务器的域名或`IP`地址 
    - port `<number> `
        - 远程服务器的端口
    - localAddress `<string>` 当发送请求时，为网络连接绑定的本地接口 
- renturn: `<string>`

## agent.maxFreeSockets
- 设置要保留的空闲`socket`的最大数量
- 默认:`256`
## agent.maxSockets
- 每个源头的最大并发`socket`的最大数量
- 默认:`不限制`
## agent.requests
- 包含还未被分配到`socket`的请求队列
## agent.sockets
- 包含当前正被代理使用的 socket数组

## http.ClientRequest 类
- 该对象在`http.request()`内部被创建并返回
- 表示着一个正在处理的请求,其请求头已进入队列
- 要获取响应，需为'response'事件添加一个监听器到请求对象上

## `abort`事件
- 当请求已被客户端终止时触发
    - 首次调用`abort()`
## `connect`事件
- 响应`CONNECT`请求时触发
    - 没有监听时会被关闭连接
    
## `continue`事件
- 发送了一个`100 Continue`的 HTTP 响应时触发

## ``response`事件
- 请求被响应时触发,该事件只触发一次

## `socket`事件
- 当`socket`被分配到请求时触发

## `timeout`事件
- 当底层`socket`超时的时触发
- 只会通知空闲的`socket`

## `upgrade`事件
- 响应`upgrade`请求时触发
    - 未监听时会被关闭连接

## request.abort()
- 标记请求为终止
- 剩余的数据被丢弃且`socket`被销毁

## request.aborted
- 记录请求终止的事件

## request.end([data[, encoding]][, callback])
- 结束发送请求
    - data `<string> | <Buffer>`
    - encoding `<string>`
    - callback `<Function>`

## request.flushHeaders()
- 刷新请求头

## request.getHeader(name)
- 获取请求头

## request.removeHeader(name)
- 删除请求头


## request.setHeader(name, value)
- 设置请求头

## request.setNoDelay([noDelay])
- 一旦`socket`被分配给请求且已连接`socket.setNoDelay()`会被调用

## request.setSocketKeepAlive([enable][, initialDelay])
- 一旦`socket`被分配给请求且已连接`socket.setKeepAlive() 会被调用

## request.setTimeout(timeout[, callback])
- 引用底层socket

## request.write(chunk[, encoding][, callback])
-  chunk `<string> | <Buffer>`
-  encoding `<string>`
-  callback `<Function> 
    - 数据块被刷新时调用

## http.Server
- 继承自`net.Server`

## `checkContinue`事件
- 接收到一个带有`HTTP Expect: 100-continue`请求头的请求时触发
    - 未被监听,自动响应 `100 Continue`
- 处理该事件时,如果客户端应该继续发送请求主体则调用`response.writeContinue()`
> 被触发时'request' 事件不会被触发

## `checkExpectation`事件
- 每当接收到一个带有`HTTP Expect`请求头不为`100-continue`时触发
    - 未被监听 自动响应 `417 Expectation Failed`
> 被触发时'request'事件不会被触发

## `clientError`事件
- 客户端发生错误时触发
- 该事件的监听器负责关闭或销毁底层的`socket`

## `close`事件
- 服务器关闭时触发

## `connect` 事件
- 每当客户端发送`HTTP CONNECT`请求时触发
    - 未被监听就关闭连接

## `connection`事件
- 当新的 TCP 流被建立时触发

## `request`事件
- 每次接收到一个请求时触发

## `upgrade`事件
- 每当客户端发送 `HTTP upgrade`请求时触发
    - 未被监听关闭连接

## server.close([callback])
- 停止服务端接收新的连接

## server.listen()
- 开启HTTP服务器监听连接
## server.listening
- 表示服务器是否在监听

## server.maxHeadersCount
- 限制请求头的最大数量
- 默认:`2000`
    - 0表示没有限制

## server.setTimeout([msecs][, callback])
- 超时事件,超时触发`timeout`事件
- 默认:`120000`

## server.timeout
- `socket`被认定为超时的空闲毫秒数
    - 表示静止该行为
    - 同上

## server.keepAliveTimeout
- 服务器完成最后的响应之后需要等待的额外的传入数据的活跃毫秒数
- 默认:`5000`
    - 0表示静止该行为


## http.ServerResponse 
- 实现了Writable接口

## `close`事件
-  `response.end()`被调用后触发

## `finish`事件
- 响应被发送时触发

- response.addTrailers(headers)
- 在尾部添加响应头

## response.end([data][, encoding][, callback])
- 发送响应头
- data `<string> | <Buffer>`
- encoding `<string>`
- callback `<Function>`
    - 响应流结束时被调用

## response.finished
- 响应是否已完成

## response.getHeader(name)
- 读取尚未发送的响应头

## response.getHeaderNames()
- 返回一个包含当前响应唯一名称的 http 头信息名称数组

## response.getHeaders()
- 返回当前响应头文件的浅拷贝
> 该返回对象不会继承javaScript Object 

## response.hasHeader(name)
- 检查响应头是否被设置

## response.hasHeader(name)
- 检查响应头是否被发送

## response.removeHeader(name)
- 移除未发送的响应头

## response.sendDate
- 为`true`时,没有日期响应头时,会自动生成

## response.setHeader(name, value)
- 设置响应头

## response.setTimeout(msecs[, callback])
- 设置socket超时事件
- 提供回调时,他会被注册到`timeout`事件中

## response.socket
- 引用底层socke

## response.statusCode
- 响应头刷新时将被发送到客户端的状态码

## response.statusMessage
- 响应头刷新时将被发送到客户端的状态信息

## response.write(chunk[, encoding][, callback])
- 发送一块响应主体
- chunk `<string> | <Buffer>`
- encoding `<string>`
- callback `<Function>`
- return: `<boolean>`

## response.writeContinue()
- 发送一个 `HTTP/1.1 100 Continue` 消息到客户端

## response.writeHead(statusCode[, statusMessage][, headers])
- 发送一个响应头给请求
> 该方法在消息中只能被调用一次,且必须在`response.end()`被调用之前调用

## http.IncomingMessage
- `http.Server`,`http.ClientReques`创建
- 它可以用来访问响应状态、消息头、以及数据
- 实现了Readable接口

## `aborted`事件
- 当请求已被终止且网络 socket 已关闭时触发
## `close`事件
- 当底层连接被关闭时触发

## message.destroy([error])
- 低层调用`IncomingMessage.socket.destroy()`
- 提供`error`时,会触发error

## message.headers
- 请求头或响应头对象

## message.httpVersion
- 返回客户端发送的 HTTP 版本

## message.method
- 返回一个字符串，表示请求的方法
> 仅在`http.Server`返回的请求中有效

## message.rawHeaders
- 接收到的原始的请求头或响应头列表

## message.rawTrailers
- 接收到的原始的`Trailer`请求头或响应头的的键和值
    - 只在`end`事件时被赋值
## message.url
- 返回请求的`URL`字符串


## http.createServer([requestListener])
- 返回一个新建的 http.Server 实例
- requestListener `<Function>`
    - 自动添加到`request`事件中
- return: `<http.Server>`

## http.get(options[, callback]);
- get请求
- options `<Object> | <string> | <URL> `
    - 与`http.request()`相同
- callback <Function>
- return: `<http.ClientRequest>`

## http.globalAgent
- `Agent`的全局实例

## http.request(options[, callback])
- options `<Object> | <string> | <URL>`
    - protocol `<string>` 
        - 使用的协议
        - 默认:`http:`
    - host `<string> `
        - 请求发送至的服务器的域名或IP地址
        - 默认:`localhost`
    - hostname `<string> `
        - host 的别名
        - 为了支持 url.parse()
        > hostname 优先于 host
    - family `<number> `
        - 当解析`host`和`hostname`时使用的 IP 地址族
        - 有效值是 4 或 6
        - 当未指定时，则同时使用`IP v4`和 v6`
    - port `<number>` 
        - 远程服务器的端口
        - 默认:`80` 
    - localAddress `<string>` 
        - 为网络连接绑定的本地接口 
    - socketPath `<string>` 
        - Unix 域 Socket
            - 使用`host:port`或`socketPath` 
    - method `<string> `
        - 指定 HTTP 请求方法
        - 默认为`GET` 
    - path `<string>` 
        - 请求的路径 
        - 默认:`/`
    - headers <Object> 
        - 包含请求头的对象 
    - auth <string> 
        - 基本身份验证
        - `Authorization` 请求头 
    - agent `<http.Agent> | <boolean>` 
        - 控制 Agent 的行为  可能的值有：
        - undefined 
            - 对该主机和端口使用 `http.globalAgent `
        - Agent 
            - Agent 对象
        - false: 
            - 创建一个新的使用默认值的 Agent 
    - createConnection <Function> 
        - 当不使用`agent`选项,为请求创建一个`socket`或流
    - timeout <number>:
        - 指定`socket`超时的毫秒数
    - callback `<Function>`
        - 会作为单次监听器被添加到`response`事件
    - return: `<http.ClientRequest>`