# buff
- JavaScript 有读取或操作二进制数据流的 机制Buffer类被引入作为Node.js的一部分,使其可以在TCP流或文件系统操作等场景中处理二进制数据流
- Buffer的大小在被创建时确定,且无法调整
- Buffer 类在Node.js中是一个全局变量,因此无需使用 `require('buffer').Buffer`

## 创建buff  
- `Buffer.alloc(num)`
    - 创建长度为num,且用0填充的BUffer
- `Buffer.allocUnsafe(num)`    
    - 创建长度为num,且未初始化的BUffer 
    - 可能包含旧数据 
- `Buffer.from(string|array,string)`
    - 参数1: 指定要编码的字符或数组
    - 参数2: 指定编码方式
    - 拥有多个重载
- `--zero-fill-buffers`
    - 命令选项
    - 自动用0填充
    > 主要针对被废弃的api 
    - [参考](http://nodejs.cn/api/buffer.html#buffer_the_zero_fill_buffers_command_line_option)

## Buffer与字符编码
- `Buffer.toString(string)`
    - 可以输出指定的编码序列
- 支持的字符编码
    - `ascii`
        - 7位ascll
    - `utf8`
    - `utf16le`
    - `ucs2`
        - `utf16le`的别名
    - `base64`
    - `latin1`
    - `binary`
        - `latin1`的别名
    - `hex`

## Buffer与 TypedArray
- `TypeArray.buffer`可以共享内存
    - `Buffer.from(TypeArray.buffer)`
        - TypeArray与Buffer共享内存

## 迭代
```js
const buf = Buffer.from([1, 2, 3]);
for(let b of buf){
    console.log(b);
};

```
- 其他创建迭代器的方法
    - `buf.values()`
    - `buf.keys()`
    - `buf.entries()` 

```blog
{type: "编程语言", tag:"编程语言,node.js",title:"nodejs-buff(缓冲模块)"}
```

             