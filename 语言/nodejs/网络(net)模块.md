# net
- 提供创建基于流的TCP或ipc服务器和客户端

## net.Server
- 这个类用于创建`TCP`或`IPCserver`

## `close`事件
- server关闭时触发

## `connection`事件
- 当有新连接是触发,会传入一个`net.socket`

## `error`事件
- 当错误出现的时候触发

## `listening`事件
- 当服务被绑定后调用

## server.address()
- 如果在`IPsocket`上监听一些信息

## server.close([callback])
- 停止`server`接受建立新的`connections`并保持已经存在的`connections`

## server.getConnections(callback)
- 异步获取服务器的当前并发连接数

## server.listen([port][, host][, backlog][, callback])
- 启动一个监听
- port `<number>`
    - 省略或者是0 则随机分配一个
- host `<string>`
- backlog `<number>`
- callback `<Function>`
- Returns: `<net.Server>`

## server.listening
- 布尔值,表示servier是否在监听

## server.maxConnections
- 最大连接数

## net.Socket
- 这个类是`TCP`` UNIX Socket`的抽象

## new net.Socket([options])
- options <Object> 
    - fd: `<number>`
        - 使用一个给定的文件描述符包装一个已存在的 socket
        - 不存在否则将创建一个新的socket。
    - allowHalfOpen `<boolean>` 
        - 指示是否允许半打开的TCP连接
        - 默认:`false`
    - readable `<boolean> `
        - 当传递了fd时允许读取`socket`
        - 默认:`false`
    - writable `<boolean>` 
        - 当传递了fd时允许写入`socket`
        - 默认:`false`
    - Returns: `<net.Socket>`

## `close`事件
- 一旦 socket 完全关闭就发出该事件
## `connect`事件
- 当一个`socket`连接成功建立的时候触发该事件

## `data`事件
- 当接收到数据的时触发该事件

## `drain`事件
- 当写入缓冲区变为空时触发

## `end`事件
- 当`socket`的另一端发送一个`FIN`包的时候触发

## `lookup`事件
- 在找到主机之后创建连接之前触发

## `timeout`事件
- 当`socket`超时的时候触发

## socket.address()
- socket的信息

## socket.connect(port[, host][, connectListener])
- port `<number> `
    - 端口
- host `<string> `
    - 主机
- connectListener `<Function>` 
    - 会被添加为'connect'事件的监听器
- Returns: `<net.Socket>`

## socket.destroy([exception])
- 销毁socket

## socket.destroyed
- 用来指示连接是否已经被销毁

## socket.end([data][, encoding])
- 半关闭 socket


## net.createConnection(port[, host][, connectListener])
- 初始化tcp连接
- port `<number>`
- host `<string>`
- connectListener `<Function>`
 - `connect`事件一次性监听器