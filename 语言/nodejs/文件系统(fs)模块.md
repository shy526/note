# fs
- 文件I/O是对标准`POSIX`函数的简单封装
- 所有的方法都有异步和同步的形式
- 异步方法的最后一个参数都是一个回调函数
    - 回调函数的第一个参数都会保留给异常
    > 同步方法任何异常会被立即抛出


## fs.FSWatcher 
### fs.watch(filename[, options][, listener])
- 监控文件或目录发生变化时触发`change`事件
- filename <string> | <Buffer> | <URL>
    - 可以是一个文件或目录
- options <string> | <Object>
    - persistent <boolean> 
        - 文件正在被监视,进程是否应该继续运行
        - 默认:`true`
    - recursive <boolean> 
        - 指明是否全部子目录应该被监视，或只是当前目录
        - 默认`false`
        > 只支持`macOS`,`Windows`
    - encoding <string> 
        - 指定用于传给监听器的文件名的字符编码
        - 默认:`utf8`
    - listener <Function> | <undefined> Default: undefined
        - eventType <string>
            - 只会是`rename`或`change`
        - filename <string> | <Buffer>

### `change`事件
- 当一个被监视的目录或文件有变化时触发

```js
const fs = require(`fs`);

fs.watch("D:/my/nodejs/1.text",(eventType,filename)=>{
   console.log("被调用");
   console.log(eventType);
   console.log(filename);

})
console.log("开始监控")
```

### `error`事件
- 当发生错误时触发

### watcher.close()
- 停止监控  

## fs.ReadStream
- 可读流

### `close`事件
- 底层流被关闭时触发

### `open` 事件
- `ReadSteram` 打开文件时触发

### readStream.bytesRead
- 已经读取的字节数

### readStream.path
- 正在读取的文件的路径

## fs.Stats
- `fs.stat()`,`fs.lstat()`,`fs.fstat()`及其同步版本返回的对象都是该类型


## fs.WriteStream 类
- 可写流
- 和ReadStream一样拥有`close`,`open`事件,
- 拥有`bytesWritten`,`path`属性

## fs.access(path[, mode], callback)
- 测试`path`指定的文件或目录的用户权限
- path <string> | <Buffer> | <URL>
- mode <integer>
    - 指定要执行的可访问性检查
    - 默认:`fs.constants.F_OK`
        - `fs.constants.F_OK` 
            - 文件对调用进程可见
        - `fs.constants.R_OK`
            - 文件可被调用进程读取。
        - `fs.constants.W_OK`
            - 文件可被调用进程写入。
        - `fs.constants.X_OK`
            - 文件可被调用进程执行
            - 对`Windows`系统没作用(相当于 `fs.constants.F_OK`)
- callback <Function>
    - err <Error>
> 不建议在调用`fs.open()`,`fs.readFile()`,`fs.writeFile()`之前使用`fs.access()`检查一个文件的可访问性

## fs.appendFile(file, data[, options], callback)
- file <string> | <Buffer> | <URL> | <number> 文件名或文件描述符
- data <string> | <Buffer>
- options <Object> | <string>
    - encoding <string> | <null>
        - 默认:`utf8`
    - mode <integer> 
        - 默认:`0o666`
    - flag <string>
        -  默认:`a`
- callback <Function>
    - err <Error>

```js
const fs = require(`fs`);
fs.appendFile("D:/my/nodejs/1.text","\n我的天",(err)=>{
    if (err) throw err;
    console.log("no erroe")
})
```

## fs.chmod(path, mode, callback)
- 异步地改变文件的权限
- path <string> | <Buffer> | <URL>
- mode <integer>
- callback <Function>
    - err <Error>


|         常量         |   八进制   |           描述           |
|:--------------------:|:----------:|:------------------------:|
|                      | 文件所有者 |                          |
| fs.constants.S_IRUSR |   0o400    |      read by owner       |
| fs.constants.S_IWUSR |   0o200    |      write by owner      |
| fs.constants.S_IXUSR |   0o100    | execute/search by owner  |
|                      |  组的权限  |                          |
| fs.constants.S_IRGRP |    0o40    |      read by group       |
| fs.constants.S_IWGRP |    0o20    |      write by group      |
| fs.constants.S_IXGRP |    0o10    | execute/search by group  |
|                      |   其他人   |                          |
| fs.constants.S_IROTH |    0o4     |      read by others      |
| fs.constants.S_IWOTH |    0o2     |     write by others      |
| fs.constants.S_IXOTH |    0o1     | execute/search by others |

> 使用 或操作连接

- 简单的权限表

| 常量 |           描述           |
|:----:|:------------------------:|
|  7   | read, write, and execute |
|  6   |      read and write      |
|  5   |     read and execute     |
|  4   |        read only         |
|  3   |    write and execute     |
|  2   |        write only        |
|  1   |       execute only       |
|  0   |      no permission       |

###  fs.close(fd, callback)
- 关闭
- fd <integer>
- callback <Function>
- err <Error>

## FS常量
fs.constants
- 返回常用的操作的常量

| 常量 |           描述           |
|:----:|:------------------------:|
| F_OK | 文件对于调用进程是可见的 |
| R_OK |   文件可被调用进程读取   |
| W_OK |   文件可被调用进程写入   |
| X_OK |   文件可被调用进程执行   |
>  用于`fs.access()`



|    常量     |                                        描述                                         |
|:-----------:|:-----------------------------------------------------------------------------------:|
|  O_RDONLY   |                                一个文件用于只读访问                                 |
|  O_WRONLY   |                                一个文件用于只写访问                                 |
|   O_RDWR    |                                一个文件用于读写访问                                 |
|   O_CREAT   |                              文件不存在则创建一个文件                               |
|   O_EXCL    |              设置了`O_CREAT`标志且文件已经存在，则打开一个文件应该失败              |
|  O_NOCTTY   |         路径是一个终端设备，则打开该路径不应该造成该终端变成进程的控制终端          |
|   O_TRUNC   |      存在且为一个常规文件,且文件被成功打开为写入访问,则它的长度应该被截断至零       |
|  O_APPEND   |                              数据会被追加到文件的末尾                               |
| O_DIRECTORY |                           路径不是一个目录,则打开应该失败                           |
|  O_NOATIME  | 文件系统的读取访问权不再引起相关文件`atime`信息的更新,该标志只在 Linux 操作系统有效 |
| O_NOFOLLOW  |                         路径是一个符号链接，则打开应该失败                          |
|   O_SYNC    |                                文件打开用于同步 I/O                                 |
|   O_DSYNC   |                         同步I/O打开，写入操作会等待数据完整                         |
|  O_SYMLINK  |                        打开符号链接自身，而不是它指向的资源                         |
|  O_DIRECT   |                     当设置它时,会尝试最小化文件 I/O 的缓存效果                      |
| O_NONBLOCK  |                            当可能时以非阻塞模式打开文件                             |
> 用于`fs.open()`



|   常量   |                   描述                   |
|:--------:|:----------------------------------------:|
|  S_IFMT  |        用于提取文件类型码的位掩码        |
| S_IFREG  |        常规文件的文件类型常量      |
| S_IFDIR  |          目录的文件类型常量        |
| S_IFCHR  |   面向字符的设备文件的文件类型常量 |
| S_IFBLK  |    面向块的设备文件的文件类型常量  |
| S_IFIFO  |       FIFO/pipe 的文件类型常量     |
| S_IFLNK  |        符号链接的文件类型常量      |
| S_IFSOCK |         socket 的文件类型常量      |
>  用于`fs.Stats`对象中用于决定一个文件的类型的`mode`属性



|  常量   |          描述          |
|:-------:|:----------------------:|
| S_IRWXU | 所有者读取、写入、执行 |
| S_IRUSR |       所有者读取       |
| S_IWUSR |       所有者写入       |
| S_IXUSR |       所有者执行       |
| S_IRWXG |  群组读取、写入、执行  |
| S_IRGRP |        群组读取        |
| S_IWGRP |        群组写入        |
| S_IXGRP |        群组执行        |
| S_IRWXO | 其他人读取、写入、执行 |
| S_IROTH |       其他人读取       |
| S_IWOTH |       其他人写入       |
| S_IXOTH |       其他人执行       |

> 用于`fs.Stats`对象中用于决定一个文件访问权限的`mode`属性

## fs.copyFile(src, dest[, flags], callback)
- 将`src`拷贝到`dest`
    - `dest` 已经存在会被覆盖
- src <string> | <Buffer> | <URL> 
    - 要被拷贝的源文件名称
- dest <string> | <Buffer> | <URL> 
    - 拷贝操作的目标文件名
- flags <number> 
    - 拷贝操作修饰符 
    - 默认:`0`
    - `fs.constants.COPYFILE_EXCL`
        - `dest` 已经存在,则会导致拷贝操作失败
- callback <Function>
```js
fs.copyFile("D:/my/nodejs/1.text","D:/my/nodejs/2.text",(err)=>{
    if (err) throw err;
})
fs.copyFile("D:/my/nodejs/1.text",fs.constants.COPYFILE_EXCL,"D:/my/nodejs/2.text",(err)=>{
    if (err) throw err;
})
console.log("go")
```

## fs.createReadStream(path[, options])
- 创建读流
- path <string> | <Buffer> | <URL>
- options <string> | <Object>
    - flags <string>
        - 默认:`r`
    - encoding <string>
    - fd <integer>
        - 传入时不会触发`open`事件
    - mode <integer>
        - 默认:`0o666`
    - autoClose <boolean>
        - 默认:`true`
    - start <integer>
        - 默认:`0`
    - end <integer>
    - highWaterMark <integer>
        - 默认: `64 * 1024`

## fs.createWriteStream(path[, options])
- 创建写流
- path <string> | <Buffer> | <URL>
- options <string> | <Object>
    - flags <string>
        - 默认:`w`
    - encoding <string>
        - `utf8`
    - fd <integer>
    - mode <integer>
        - 默认:`0o666`
    - autoClose <boolean>
        - 默认:`true`
    - start <integer>
## fs.fchmod(fd, mode, callback)
- 更改权限

## fs.fchown(fd, uid, gid, callback)
    - 变更用户或组

## fs.fdatasync(fd, callback)
- 刷新数据

## fs.fstat(fd, callback)
- 获取文件表述对象

## fs.ftruncate(fd[, len], callback)
- 截取文件的前len个字节
> 小于`len`时用`\0`填充

## fs.futimes(fd, atime, mtime, callback)
- 改变文件系统的时间戳

## fs.utimes(path, atime, mtime, callback)
- 改变文件系统的时间戳

## fs.lchown(path, uid, gid, callback)
- 改变符号链接的所有权

## fs.link(existingPath, newPath, callback)
- 创建连接

## fs.mkdir(path[, mode], callback)
    - 创建文件夹

## fs.mkdtemp(prefix[, options], callback)
- 创建临时唯一的临时的文件夹

## fs.open(path, flags[, mode], callback)
- path <string> | <Buffer> | <URL>
    - flags <string> | <number>
    - mode <integer>
        - 默认:`0o666`
    - callback <Function>
        - err <Error>
        - fd <integer>

- flg参数
- `r` 
    - 以读取模式打开文件
    - 文件不存在则发生异常。
- `r+` 
    - 以读写模式打开文件
    - 文件不存在则发生异常
- `rs+` 
    - 以同步读写模式打开文件
    - 绕过本地文件系统缓存
    > 影响性能
- `w` 
    - 以写入模式打开文件
    - 文件不存在会被创建
    - 文件存在被截断
- `wx` 
    - 类似 `w`
    - `path`存在则失败
- `w+` 
    - 以读写模式打开文件
    - 文件不存在会被创建
    - 文件存在被截断
- `wx+` 
    - 类似 `w+`
    - `path`存在则失败
- `a` 
    - 以追加模式打开文件
    - 文件不存在被创建
- `ax` 
    - 类似于 `a`
    - `path`存在则失败
- `a+` 
    - 以读取和追加模式打开文件
        - 文件不存在被创建
- `ax+` 
    - 类似于 `a+`
    - `path`存在则失败


## fs.read(fd, buffer, offset, length, position, callback)
- fd <integer>
    - 指定的文件
- buffer <Buffer> | <Uint8Array>
    - 被写入的buffer
- offset <integer>
    - buffer写入时的偏移量
- length <integer>
    - 要读取的字节数
- position <integer>
    - 开始读取的位置
- callback <Function>
    - err <Error>
    - bytesRead <integer>
    - buffer <Buffer>  

## fs.readdir(path[, options], callback)
- 读取目录的内容
    - path <string> | <Buffer> | <URL>
    - options <string> | <Object>
    - encoding <string> 
        - 默认:`utf8`
        - 可以是一个`buffer`
    - callback <Function>
        -   err <Error>
        -   files <string[]> | <Buffer[]>
            -  `files`是不包括`.`,`..`的文件名的数组  
## fs.readFile(path[, options], callback)
- 读取一个文件的内容
- path <string> | <Buffer> | <URL> | <integer> 文件名或文件描述符。
    -  options <Object> | <string>
    -  encoding <string> | <null>
        - 默认:`null`
    -  flag <string>
        - 默认:`r`
    -  callback <Function>
        -  err <Error>
        -  data <string> | <Buffer>
> 不会被自动关闭

## fs.readlink(path[, options], callback)
- 获取连接的真实目录
    - path <string> | <Buffer> | <URL>
    - options <string> | <Object>
    - encoding <string> 
        - 默认:`utf8`
    - callback <Function>
        - err <Error>
        - linkString <string> | <Buffer>

## fs.realpath(path[, options], callback)
- 相对路径转换为绝对路径
    - path <string> | <Buffer> | <URL>
    - options <string> | <Object>
    - encoding <string> 默认 = 'utf8'
    - callback <Function>
        - err <Error>
        - resolvedPath <string> | <Buffer>
> 只支持可转换成`utf8`字符串的路径

## fs.rename(oldPath, newPath, callback)
- 重名或移动

## fs.rmdir(path, callback)
- 删除path
> 只能在文件上

## fs.stat(path, callback)
- 获取文件状态

## fs.symlink(target, path[, type], callback)
- 建立符号

## fs.truncate(path[, len], callback)
- 文件阶段

## fs.unlink(path, callback)
- 删除文件

## fs.unwatchFile(filename[, listener])
- 移除监听器

## fs.watchFile(filename[, options], listener)
- 监听文件

## fs.write(fd, buffer[, offset[, length[, position]]], callback)
- `fs.write(fd, string[, position[, encoding]], callback)`
    - fd <integer>
    - buffer <Buffer> | <Uint8Array>
    - offset <integer>
        - buffer中被写入的部分
    - length <integer>
        - 写入的字节数
    - position <integer>
        - 写入数据的位置的偏移量
    - callback <Function>
        - err <Error>
        - bytesWritten <integer>
            - 写入了多少字节
        - buffer <Buffer> | <Uint8Array>
> Linux上当文件以追加模式打开时,指定位置的写入是不起作用的
## fs.writeFile(file, data[, options], callback)
- 写数据
    - file <string> | <Buffer> | <URL> | <integer> 文件名或文件描述符
    - data <string> | <Buffer> | <Uint8Array>
    - options <Object> | <string>
    - encoding <string> | <null>
        - 默认:`utf8`
    - mode <integer>
        - 默认:`0o666`
    - flag <string>
        - 默认:`w`
    - callback <Function>
        - err <Error>   