# child_process 
- 提供衍生子进程的功能
    - `child_process.spawn()`函数提供

```js

```

## 创建异步进程
- 以下四个都遵循nodejs惯用的异步编程模式
    1. `child_process.spawn()`
    2. `child_process.fork()`
    3. `child_process.exec()`
    4.  `child_process.execFile()`
        - `windows` 无法使用

## child_process.exec(command[, options][, callback])
- 衍生一个`shell`并在`shell`中执行`command`且缓冲任何产生的输出
- command `<string>` 运行的命令，参数使用空格分隔。
- options `<Object>`
    - cwd `<string>` 
        - 子进程的当前工作目录
    - env `<Object>` 
        - 环境变量键值对。
    - encoding `<string>` 
        - 默认:`utf8`
    - shell `<string>` 
        - 执行命令的 shell
        - `UNIX`默认:`/bin/sh`
        - `Windows`默认: `process.env.ComSpec`
    - timeout `<number>` 
        - 默认:`0`
    - maxBuffer `<number>` 
        - `stdout`或`stderr`允许的最大字节数
        - 默认:`200*1024`
        > 超过限制,则子进程会被终止
    - killSignal `<string> | <integer>` 
        - 默认:`SIGTERM`
    - uid `<number>` 
        - 设置进程的用户标识
    - gid `<number>` 
        - 设置进程的组标识
    - windowsHide `<boolean>`
        -  隐藏子进程的控制台窗口
        - 默认:`false`
        > 常用于 Windows 系统
- callback `<Function>` 
    - 进程终止时调用
    - error `<Error>`
    - stdout `<string> | <Buffer>`
    - stderr `<string> | <Buffer>`
- return : `<ChildProcess>`

```js
const { exec } = require('child_process');
exec('echo "shell"',(err, stdout, stderr) =>{
    console.log(stdout)
});
```

## child_process.execFile(file[, args][, options][, callback])
- 与`child_process.exec()`作用相同
    - 不衍生shell,指定file直接衍生新进程
- file `<string>`
    - 要运行的可执行文件的名称或路径
- args `<string[]>`
    - 字符串参数列表。
- options `<Object>`
    - cwd `<string>` 
        - 子进程的当前工作目录
    - env <Object> 
        - 环境变量键值对
    - encoding `<string>` 
        - 默认:`utf8`
    - timeout `<number>` 
        - 默认:`0`
    - maxBuffer `<number>` 
        - `stdout`或`stderr`允许的最大字节数
        - 默认:`200*1024`
        > 超过限制,则子进程会被终止
    - killSignal `<string> | <integer>` 
        - 默认:`SIGTERM`
    - uid `<number>` 
        - 设置该进程的用户标识
    - gid `<number>` 
        - 设置该进程的组标识
    - windowsHide `<boolean>` 
        - 是否隐藏在`Windows`系统下默认会弹出的子进程控制台窗口
        - 默认:`false`
    - windowsVerbatimArguments `<boolean>` 
        - 决定在Windows系统下是否使用转义参数
        - 默认:false
- callback `<Function>` 
    - 当进程终止时调用,并带上输出
    - error `<Error>`
    - stdout `<string> | <Buffer>`
    - stderr `<string> | <Buffer>`
- return : `<ChildProcess>`

```js
const { execFile } = require('child_process');
const child = execFile('node', ['--version'], (error, stdout, stderr) => {
    if (error) {
      throw error;
    }
    console.log(stdout);
  });
  
```

## child_process.fork(modulePath[, args][, options])
- 专门用于衍生新的 Node.js 进程
- modulePath `<string>` 
    - 要在子进程中运行的模块
- args `<Array>` 
    - 字符串参数列表
- options `<Object>`
- cwd `<string>` 
    - 子进程的当前工作目录
- env `<Object> `
    - 环境变量键值对
- execPath `<string>` 
    - 用来创建子进程的执行路径
- execArgv `<Array>` 
    - 要传给执行路径的字符串参数列表
    - 默认:`process.execArgv`
- silent `<boolean>`
    -  `true`: 则子进程中的`stdin``stdout``stderr`会被导流到父进程中,否则它们会继承自父进程
- stdio `<Array> | <string>`
    - 当提供了该选项,则它会覆盖`silent`
    - 使用了数组变量，则该数组必须包含一个值为`ipc`的子项
        - 否则会抛出错误,例如`[0, 1, 2, 'ipc']`
- windowsVerbatimArguments `<boolean> `
    - 决定在Windows系统下是否使用转义参数 
    - 在Linux平台下会自动忽略
    - 默认值:`false`
- uid `<number> `
    - 设置该进程的用户标识
- gid `<number> `
    - 设置该进程的组标识
- 返回: `<ChildProcess>`

## child_process.spawn(command[, args][, options])
- command `<string> `
    - 要运行的命令
- args `<Array> `
    - 字符串参数列表
- options `<Object>`
    - cwd `<string>` 
        - 子进程的当前工作目录
    - env `<Object> `
        - 环境变量键值对
    - argv0 `<string> `
        - 显式地设置要发给子进程的`argv[0]`
        - 默认:`command`
    - stdio `<Array> | <string>` 
        - 子进程的`stdio`配置
    - detached `<boolean>`
        - 子进程独立于父进程运行
        > 具体行为取决于平台
    - uid `<number>` 
        - 设置该进程的用户标识
    - gid `<number> `
        - 设置该进程的组标识
    - shell `<boolean> | <string>`
        - `true`:则在一个`shell`中运行 command。 
        - `UNIX`上使用`/bin/sh`
        - `Windows`上使用`process.env.ComSpec`
        -  默认:`false`
    - windowsVerbatimArguments `<boolean>`
        - 决定在Windows系统下是否使用转义参数
        - 默认值:`false`
    - windowsHide `<boolean> `
        - `Windows`系统下默认会弹出的子进程控制台窗口
        -  默认为:`false`
- 返回: `<ChildProcess>`

## options.detached
- `Windows`上,设置`options.detached`为`true`可以使子进程在父进程退出后继续运行
    - 子进程有自己的控制台窗口,一旦启用一个子进程，它将不能被禁用
- 非`Windows`平台，如果将`options.detached`设为`true`,则子进程会成为新的进程组和会话的领导者

## options.stdio
-  选项用于配置子进程与父进程之间建立的管道
- 默认:子进程的 `stdin``stdout``stderr`会重定向到`ChildProcess`对象上相应的`subprocess.stdin ``subprocess.stdout``subprocess.stderr`
- `options.stdio` 可以为如下几个字符串
   1. `pipe`
        -  等同于 `['pipe', 'pipe', 'pipe']`
        - 默认
   2. `ignore` 
        - 等同于 `['ignore', 'ignore', 'ignore']`
   3. `inherit` 
        - 等同于 `[process.stdin, process.stdout, process.stderr]` 或 `[0,1,2]`

## ChildProcess 
- 实例是 `EventEmitter`代表衍生的子进程
- 不能被直接创建

### close事件
- 当子进程的`stdio`流被关闭时会触发

### disconnect事件
- 在父进程或子进程中调用`subprocess.disconnect()`后会触发`disconnect`事件
- `subprocess.connected`属性被设置为`false`

### error事件
1. 进程无法被衍生
2. 进程无法被杀死
3. 向子进程发送信息失败
- 以上三种情况都会触发`error`事件
> 错误发生后可能不会触发`exit`事件

### exit事件
- 子进程结束后会触发`exit`事件
> 子进程的`stdio`依然可能依然是打开的

### message事件
- 当一个子进程使用`process.send()`发送消息时会触发

## subprocess.channel
- 当前子进程的`IPC`通道的引用

## subprocess.connected
- 是否仍可以从一个子进程发送和接收消息

## subprocess.disconnect()
- 关闭父进程与子进程之间的`IPC`通道,一旦没有其他的连接使其保持活跃,则允许子进程正常退出

## subprocess.kill([signal])
- 发送`SIGTERM`信号

## subprocess.killed
- 已成功发送信号给子进程后会被设置为`true`

## subprocess.pid
- 返回子进程的pid

## subprocess.send(message[, sendHandle[, options]][, callback])
- 用于发送消息子进程

## subprocess.stderr
- 子进程可读流

## subprocess.stdin
- 子进程的可写流

## subprocess.stdio
- 一个到子进程的管道的稀疏数组

## subprocess.stdout
- 可读流