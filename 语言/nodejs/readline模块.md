# readline
- 提供了一个用于可读流,按行读取

- 基本使用的例子
```js
const readline=require('readline');
const rl=readline.createInterface({
    input:process.stdin,
    output:process.stdout
});
rl.question("你是谁?\n",(answer)=>{
    console.log(`你是${answer}\n`);
    rl.close();
});
```

## Interface
- 每个实例都关联一个`input`可读流和一个 `output`可写流
    - `output`流用于为到达的用户输入打印提示且从`input`流读取

### `close`事件
- `rl.close()`
- `input`流接收到`end`事件
- `input`流接收到表示结束传输的`-D`
- `input`流接收到表示`SIGINT`的`-C`,且`readline.Interface`实例上没有注册`SIGINT`事件监听器

### `line`事件
- 每当`input`流接收到接收行结束符()`\n`,`\r`,`\r\n`)时触发`line`事件

### `pause`事件
- `input`流被暂停
- `input`流不是暂停的,且接收到`SIGCONT`事件

### `SIGCONT`事件
- 当一个`Node.js`进程使用`-Z`移入后台之后再使用fg(1p)移回前台时，触发`SIGCONT`事件
    - `input`流在`SIGTSTP`请求之前被暂停,则事件不会被触发
> `Windows` 不支持该事件

### `SIGINT`事件
- `input`流接收到一`-C`输入时,触发`SIGINT`事件
    - 当`input`流接收到一个`SIGINT`时,如果没有注册`SIGINT`事件监听器,则`pause`事件会被触发
### `SIGTSTP`事件
- 每当`input`流接收到一个`-Z`输入时触发`SIGTSTP`事件
    - input流接收到一个`SIGTSTP 时,如果没有注册`SIGTSTP`事件监听器,则Node.js进程会被发送到后台
> `Windows` 不支持该事件

### rl.close()
- 关闭`readline.Interface`,撤销`input`,`output`流的控制

### rl.pause()
- 暂停`input`流,且稍后需要时可被恢复
    - 不会立刻暂停

### rl.prompt([preserveCursor])
- `preserveCursor`如果为`true`则阻止光标落点被设为`0`
- 用于为用户提供一个可供输入的新的位置
    - - 如果`input`流已被暂停,会恢复`input`流

### rl.question(query, callback)
- 接收输入前的提示
- `input`流已被暂停恢复`input`流

### `rl.resume()`
- 恢复暂停的`input`流

### rl.setPrompt(prompt)
- 写入之前的提示

### rl.write(data[, key])
- `data`或一个由`key`指定的按键序列写入到`output`
- data `<string>`
- key `<Object>`
    - ctrl `<boolean> `
        - `true`:`<ctrl>`键
    - meta `<boolean> `
        - `true`:`<Meta>`键
    - shift `<boolean> `
        -`true`:`<Shift>`键
    - name `<string>` 
        一个按键的名称  
> 写入数据到`readline`接口的`input`

### readline.clearLine(stream, dir)
- dir <number>
    - `-1` 
        - 光标左边
    - `1`
         - 光标右边
    - 0
         - 整行

### readline.clearScreenDown(stream)
- 从光标的当前位置向下清除给定的`TTY`流

### readline.createInterface(options)
- 创建一个新的`readline.Interface`实例
- options `<Object>`
    - input `<stream.Readable>` 
        - 监听的可读流
    - output `<stream.Writable> `
        - 写入逐行读取数据的可写流
    - completer `<Function>` 
        - 一个可选的函数,用于 Tab 自动补全
    - terminal `<boolean>` 
        - `input`和`output`应被当作一个`TTY`
            - 写入` ANSI/VT100`转换的代码则设为`true`
            - 默认:实例化时在`output`流上检查isTTY
    - historySize `<number>` 
        - 保留的历史行数的最大数量
            - 0 
                - 可禁用历史记录
        > `output`设为`true`时才有意义
    - prompt 
        - 要使用的提示字符串   
          -  默认:`>`
    - crlfDelay `<number> `
        - 如果`\r``\n`之间的延迟超过`crlfDelay`毫秒则`\r`,`\n`都会被当作换行分隔符
        - 默认:`100毫秒`
    - removeHistoryDuplicates `<boolean>` 
        - 默认:`false`

### readline.cursorTo(stream, x, y)
- 方法会移动光标到给定的`TTY stream`中指定的位置

### readline.emitKeypressEvents(stream[, interface])
- stream` <stream.Readable>`
    - 如果`stream 是一个`TTY`
        - 必须为原始模式
- interface `<readline.Interface>`
    - 指定了一个`readline.Interface`实例
        - 用于当自动补全被禁用时检测到复制粘贴输入
-  方法使给定的可读流`stream`相应于接收到的输入触发`keypress`事件

### readline.moveCursor(stream, dx, dy)
- 法会移动光标到给定的`TTY stream`中相对当前的位置
