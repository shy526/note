# os
- 提供了一些操作系统相关的实用方法

## os EOL
- 一个字符串常量,定义操作系统相关的行末标志

## os.arch()
- 返回nodejs编译时所用的操作系统cpu架构

## os constants
- 返回一个包含错误码,处理信号等通用的操作系统特定常量的对象

- os.constants.signals 

|      常量       |                                  描述                                  |
|:---------------:|:----------------------------------------------------------------------:|
|     SIGHUP      |              发送来表明当一个控制终端关闭或者是父进程退出              |
|     SIGINT      |                 发送来表明当一个用户期望中断一个进程时                 |
|     SIGQUIT     |          发送来表明当一个用户希望终止一个进程并且执行核心转储          |
|     SIGILL      | 发送给一个进程来通知它已经试图执行一个非法的,畸形的,未知的或特权的指令 |
|     SIGTRAP     |                     发送给一个进程当异常已经发生了                     |
|     SIGABRT     |                        发送给一个进程来请求终止                        |
|     SIGIOT      |                            SIGABRT的同义词                             |
|     SIGBUS      |                发送给一个进程来通知它已经造成了总线错误                |
|     SIGFPE      |           发送给一个进程来通知它已经执行了一个非法的算术操作           |
|     SIGKILL     |                       发送给一个进程来立即终止它                       |
| SIGUSR1 SIGUSR2 |                  发送给一个进程来确定它的用户定义情况                  |
|     SIGSEGV     |                       发送给一个进程来通知段错误                       |
|     SIGPIPE     |              发送给一个进程当它试图写入一个非连接的管道时              |
|     SIGALRM     |                     发送给一个进程当系统时钟消逝时                     |
|     SIGTERM     |                        发送给一个进程来请求终止                        |
|     SIGCHLD     |                    发送给一个进程当一个子进程终止时                    |
|    SIGSTKFLT    |                发送给一个进程来表明一个协处理器的栈错误                |
|     SIGCONT     |                  发送来通知操作系统继续一个暂停的进程                  |
|     SIGSTOP     |                     发送来通知操作系统暂停一个进程                     |
|     SIGTSTP     |                       发送给一个进程来请求它停止                       |
|    SIGBREAK     |                  发送来表明当一个用户希望终止一个进程                  |
|     SIGTTIN     |                   发送给一个进程当它在后台读取TTY时                    |
|     SIGTTOU     |                   发送给一个进程当它在后台写入TTY时                    |
|     SIGURG      |              发送给一个进程当socket由紧急的数据需要读取时              |
|     SIGXCPU     |              发送给一个进程当它超过他在CPU使用上的限制时               |
|     SIGXFSZ     |            发送给一个进程当它文件成长的比最大允许的值还大时            |
|    SIGVTALRM    |                   发送给一个进程当一个虚拟时钟消逝时                   |
|     SIGPROF     |                   发送给一个进程当一个系统时钟消逝时                   |
|    SIGWINCH     |                  发送给一个进程当控制终端改变它的大小                  |
|      SIGIO      |                       发送给一个进程当I/O可用时                        |
|     SIGPOLL     |                              SIGIO同义词                               |
|     SIGLOST     |                      发送给一个进程当文件锁丢失时                      |
|     SIGPWR      |                      发送给一个进程来通知功率错误                      |
|     SIGINFO     |                              SIGPWR同义词                              |
|     SIGSYS      |                    发送给一个进程来通知有错误的参数                    |
|    SIGUNUSED    |                             SIGSYS的同义词                             |

> 其他查文档吧

## os.cpus()
-  方法返回一个对象数组, 包含每个逻辑 CPU 内核的信息
- 每个元素所包含的
    - model `<string>`
    - speed `<number>` 
        - 兆赫兹为单位
    - times <Object>
        - user `<number>` 
            - CPU花费在用户模式下的毫秒时间数
        - nice `<number>` 
            - CPU花费在良好模式下的毫秒时间数
        - sys `<number>` 
            - CPU花费在系统模式下的毫秒时间数
        - idle `<number>` 
            - CPU花费在空闲模式下的毫秒时间数
        - irq `<number>` 
            - CPU花费在中断请求模式下的毫秒时间数

## os.endianness()
- 返回一个字符串,表明Node.js二进制编译环境的字节顺序
- 可能返回的字符串
    - `BE`
        - 大端模式
    - `LE`
        - 小端模式
## os.freemem()
- 以整数的形式回空闲系统内存的字节数

## os.homedir()
- 返回当前用户的home目录

## os.hostname()
- 返回操作系统的主机名

## os.loadavg()
- 返回一个数组,包含1,5,15分钟平均负载
- `Windows`永远返回`[0,0,0]`

## os.networkInterfaces()
- 返回一个对象,包含只有被赋予网络地址的网络接口
- 对象包含如下值
    - address `<string>` 
        - 被赋予的`IPv4或`IPv6`地址
    - netmask `<string>` 
        - `IPv4`或`IPv6`子网掩码
    - family `<string>`
        - `IPv4`或`IPv6`
    - mac `<string>` 
        - 网络接口的MAC地址
    - internal `<boolean>` 
        - 如果网络接口是`loopback`或相似的远程不能用的接口时, 值为`true`,否则为`false`
    - scopeid `<number>` 
        - `IPv6`数字领域识别 (只有当 family是`IPv6`时可用)
    - cidr `<string>` 
        - 以 CIDR 表示法分配的带有路由前缀的`IPv4`或`IPv6`地址
            - `netmask`参数不可用，则该属性是`null`    

## os.platform()
- 返回 Node.js编译时的操作系统平台

## os.release()
-  返回操作系统的发行版
>  在`Windows`系统上,用`GetVersionExW()`

## os.tmpdir()
- 返回默认临时文件目录

## os.totalmem()
- 以整数的形式返回所有系统内存的字节数

## os.type()
-  表明操作系统的名字

## os.uptime()
- 在几秒内返回操作系统的上线时间

## os.userInfo([options])
- options <Object>
    - encoding <string> 
        - 用于解释结果字符串的字符编码
        -  默认: `utf8`
    - return: <Object>
