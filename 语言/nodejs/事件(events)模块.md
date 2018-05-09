# events
- Node.js 核心 API 都采用惯用的异步事件驱动架构

-`EventEmitter`
  - 素有触发事件的对象
- `eventEmitter.on()`
  - 用于注册监听器
- `eventEmitter.emit()`
  - 用于触发事件

  ```js
  const events = require('events');
  const event = new events.EventEmitter;
  event.on("go", () => {
    console.log("go,go,go")
  })
  event.emit("go");
  ```

  ## 监听器传参
  ```js
  const events = require('events');
  const event = new events.EventEmitter;
  event.on("go", (value) => {
      console.log("go,go,go,%s",value)
  })

  event.emit("go",54321);
  ```
## 异步与同步
- EventEmitter会按照监听器注册的顺序同步地调用所有监听器
- 监听器函数可以使用以下两个方法完成异步操作
  1. `setImmediate(func)`
  2. `process.nextTick(func)`
```js
event.on("go", () => {
    console.log("监听器触发");
    //异步触发
    process.nextTick(function(){
        console.log("go,go,go");
    })
   /* 
     异步触发
     setImmediate(function () {
       console.log("go,go,go");
     }) 
     */
    console.log("监听器结束");
});

```
## 单次触发
- 默认监听器可以被多次触发
- `eventEmitter.once(func)`
  - 最多被调用一次的监听器,触发时，监听器会被注销
```js
const events = require('events');
const event = new events.EventEmitter;
event.once("go", () => {
    console.log("go,go,go");
});
event.emit("go");
event.emit("go");
```
## error事件
- `EventEmitter`实例中发生错误时,会触发一个`error`事件
- 会抛出错误,打印堆栈跟踪,且退出Node.js进程
```js
const events = require('events');
const event = new events.EventEmitter;
event.emit('error',new Error("出错"));
console.log("无法执行")
```
> 建议为每一个监听器 添加`error`事件

## newListener 事件
- 当一个监听器被创建时触发该事件
- 该事件会拥有两个参数
  1. eventName <any> 
      - 要监听的事件的名称
  2. listener <Function> 
      - 事件的句柄函数
- `newListener` 回调函数中,同名的监听器会被插入的前面
```js
const events = require('events');
const event = new events.EventEmitter;
event.on("newListener",(event, listener)=>{
    console.log(event);
    console.info(listener);
    console.log("一个监听器被创建")
})
event.on("click",(event, listener)=>{
    console.log("我被点击了")
})
console.log("--------------------------------")
event.emit("click");
```
  
## removeListener事件
- listener 被移除后触发
- 该事件会拥有两个参数
  1. eventName <any> 
      - 事件名
  2. listener <Function> 
      - 事件句柄函数
```js
const events = require('events');
var event = new events.EventEmitter;
const eventvalue = "xxx";
let callback = function () {
    console.log("我被点击了1");
}
event.on(eventvalue, callback);
event.on("removeListener", (event, listener) => {
    console.log("事件被移除");
});
event.emit(eventvalue)

event.removeListener(eventvalue, callback);
event.emit(eventvalue)
```

## EventEmitter.defaultMaxListeners
- 每个事件默认可以注册最多10个监听器,超过时会打印警告
- ` EventEmitter.setMaxListeners(n)`
  - 每个实例可以通过此方法改变最多几个监听器
  - 不是正数会抛出`TypeError`异常

## 详细
- [nodejs中文-events](http://nodejs.cn/api/events.html)

```blog
{type: "编程语言", tag:"编程语言,node.js",title:"nodejs-events(事件模块)"}
```
