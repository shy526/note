# path
- 提供了一些工具函数,用于处理文件与目录的路径

## path.basename(path[, ext])
- 返回路径的最后一部分
```js
const path = require("path");
let fileName = path.posix.basename('D:/my/nodejs/1 - 副本 (9).txt');
console.log(fileName)
//output->1 - 副本 (9).txt
let fileName2 = path.posix.basename('D:/my/nodejs/1 - 副本 (9).txt','.txt');
console.log(fileName)
//output->1 - 副本 (9).txt
//windos,Path 无法去除扩展名
```

## path.delimiter
- 表示路径分隔符`;`,`:`

## path.dirname(path)
- 获取上一级路径
```js
let p=path.dirname("D:/my/nodejs");
console.log(p);
//output->D:/my/
```

## path.extname(path)
- 获取倒数第一个`.`到字符串结束
- `.`在第一位时返回空串
```js
const path = require("path");
let p=path.extname("D:/my/nodejs/1 - 副本 (3).txt.avi");
console.log(p);
//output->.avi
let p1=path.extname(".avi");
console.log(p1);
//output-> 
```

## path.format(pathObject)
- pathObject <Object>
    - dir <string>
    - root <string>
    - base <string>
    - name <string>
    - ext <string>
- 返回一个路径字符串
- 属性优先级
- `pathObject.dir`>`pathObject.root`
- `pathObject.base`>`pathObject.ext`AND`pathObject.name`

## path.isAbsolute(path)
- 判断是否为绝对路径

## path.join([...paths])
- 路径拼接,自动使用平台特定的路径   
- 长度为零的path会被忽然
-  连接后的路径字符串是一个长度为零的字符串,则用`.`代替

## path.normalize(path)
- 规范化path

## path.parse(path)
- 解析path为一个对象
    - 该对象有如下属性
        - dir <string>
        - root <string>
        - base <string>
        - name <string>
        - ext <string>
>  `path.format(pathObject)` 对应

## path.posix
- 针对POSIX的实现

## path.win32
- 针对Windows的实现

## path.relative(from, to)
- 返回从 from 到 to 的相对路径（基于当前工作目录）

## path.resolve([...paths])
- 把一个路径或路径片段的序列解析为一个绝对路径

## path.sep
- 提供了平台特定的路径分隔符
> `path.delimiter`是作用在多个路径,`path.sep`则作用在单个路径(`\`,`/`)

```blog
{type: "编程语言", tag:"编程语言,node.js",title:"nodejs-path(路径模块)"}
```