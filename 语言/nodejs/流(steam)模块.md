# stream

## 流的类型
- Readable
    - 可读流
- Writable
    - 可写流
- Duplex
    - 可读写流
- Transoform
    - 可以修改和变换数据的Diplex流

## 缓冲
- 流都会将数据存储到内部缓冲区中
- 通过`writable._writableState.getBuffer()`或`readable._readableState.buffer`获取
- 缓冲区大取决于`highWaterMar`属性
- 读,缓冲区到达阀值时会暂停获取底部数据
- 写,缓冲区到达阀值时,`wrtitable.write`会返回true

## stream.Writable
- `close`事件
    - 流被关闭时触发
- `drain`事件
    - 底层流恢复时触发
- `error`事件
    - 数据出错或流出错时触发
    > 流不会被关闭
- `finish`事件
    - 调用`stream.end()`,且缓冲区数据已经写入后触发
- `pipe`事件
    - 读和写发生转换时触发
- `unpipe`事件
    - 移除流时触发
- `writable.cork()`
    - 强制所有数据都皴法到缓冲区
- `writable.end([chunk][, encoding][, callback])`
    - chunk `<string> | <Buffer> | <Uint8Array> | <any>` 
        - 可选的,需要写入的数据
    - encoding `<string>`
        -  可选,字符编码。
    - callback `<Function>`
        -  可选,流结束时的回调函数
- `writable.setDefaultEncoding(encoding)` 
    - 设置默认的字符编码
- `writable.uncork()`
    - 输出缓冲区的数据
    > ` writable.cork()`调用次数要和`writable.uncork() `调用次数相同
- `writable.writableHighWaterMark`
    - buff缓冲区的大小
- `writable.write(chunk[, encoding][, callback])`
    - chunk `<string> | <Buffer> | <Uint8Array> | <any>` 
        - 要写入的数据
    - encoding `<string>` 
        - 字符编码
    - callback `<Function>` 
        - 函数
    - return `<boolean>` 
        - 流需要等待`drain`事件触发才能继续写入数据返回`false`,否则返回`true`
- `writable.destroy([error])`
    - 摧毁流

## 可读流

- 两种模式
    - `flowing`
        - 可读流自动从系统底层读取数据,并通过`EventEmitter`接口的事件尽快将数据提供给应用
    - `paused`
        - 必须显式调用`stream.read()`方法来从流中读取数据片
    - 切换
        - `paused`->`flowing`
            - 监听`data`事件
            - `stream.resume()`
            - `stream.pipe()`
        - `flowing`->`paused`
            - `stream.pause()`
            - `取消`data`事件监听,并调用`stream.unpipe()`
            > 可能会导致数据丢失
- 三种状态
    - `readable._readableState.flowing = null`
        - 数据消费者不存在,可读流将不会产生数据
    - `readable._readableState.flowing = false`
        - `readable.pause()`,`readable.unpipe()`
    - `readable._readableState.flowing = true`
        - `readable.resume()`,`readable.pipe()` 

`stream.Readable`
- `data`事件
    - `chunk <Buffer> | <string> | <any> `
    - `流将数据传递给消费者时触发
    - 流转换到`flowing`模式时会触发该事件
        - `readable.pipe()`,`readable.resume()`
        - 添加`data`事件回调
    - 调用`readable.read()`
- `readable`
    - 流中有数据可供读取时触发
- `readable.isPaused()`
    - 获取流的状态
- `readable.pause()`
    - 会使`flowing`模式的流停止触发`data`事件
- `readable.pipe(destination[, options])`
    - destination `<stream.Writable>`
        - 数据写入目标
    - options `<Object>`
        - end <boolean> 
            - 在`reader`结束时结束`writer`
            - 默认:`true`
- `readable.readableHighWaterMark`
    - 返回 `highWaterMark`属性
- `readable.read([size])`
    - 读取size个数据
- `readable.resume()`
    - 重新触发`data`事件
- `readable.setEncoding(encoding)`
    - 指定编码
- `readable.unpipe([destination])`
    - 分离目标流
- `readable.unshift(chunk)`
    - 数据块移动到可读队列底部
- `readable.destroy([error])`
    - 摧毁流

##  API for Stream Implementers
- stream模块API的设计是为了让JavaScript的原型继承模式可以简单的实现流

- 新的流类必须实现


|          用例          |    类     |         实现的方法         |
|:----------------------:|:---------:|:--------------------------:|
|         只读流         | Readable  |           _read            |
|         只写流         | writable  |   _write,_writev,_final    |
|       可读可写流       |  Duplex   | _read,write,_writev,_final |
| 操作写数据，然后读结果 | Transform |  _transform,_flush,_final  |

> 不做详细介绍 
- [具体参考](http://nodejs.cn/api/stream.html#stream_api_for_stream_implementers) 


```blog
{type: "编程语言", tag:"编程语言,node.js",title:"nodejs-stram(流模块)"}
```

