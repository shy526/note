# util
-  模块主要用于支持 Node.js内部API的需求,和一些调试用的函数

## util.callbackify(async)
- `return` func 
- 将异步回调转换为传统回调
```js
const util=require("util");
async function test(){
    console.log("test")
    return "哈哈哈";
}
var call=util.callbackify(test);
call((err,ret)=>{
    if (err) throw err;
    console.log(ret);
});

```

## util.debuglog(section)
- 返回日志函数
-  基于`NODE_DEBUG`环境变量的存在与否输出日志

## util.format(format[, ...args])
- 格式化字符串
    - `%s`
        - 字符串。
    - `%d` 
        - 数值（整数或浮点数）。
    - `%i`
    - Integer.
    - `%f`
        - Floating point value.
    - `%j`
    - JSON
    - `%o`
        - 对象
    - `%O`
        - 对象
    - `%%`
        - 单个百分号（'%'）。不消耗参数。

## util.inspect(object[, options])
- `options <Object>`
    - `showHidden <boolean>`
        - 不可枚举的符号与属性也会被包括在格式化后的结果中
        - 默认为 `false`
    -  `depth <number>`
        - object的递归次数
        - 默认为`2`
        - `null`表示无限递归
    -  `colors <boolean>`
        - 输出样式使用 ANSI 颜色代码
        - 默认为`false`
        - 自定义颜色[`util.inspect`](http://nodejs.cn/api/util.html#util_customizing_util_inspect_colors)
    -  `customInspect <boolean>`
        - `inspect(depth, opts)` 函数调用
        - 默认为 true。
    -  `showProxy <boolean>` 
        - `Proxy`对象的对象和函数会展示它们的`target`和 `handler`对象
        - 默认为`false`
    -  `maxArrayLength <number>`
        - 指定格式化时数组和 TypedArray 元素能包含的最大数量
        - 默认为`100`
        - `null` 则显式全部数组元素
        - `0`或负数则不显式数组元素
    -  `breakLength <number>` 
        - 一个对象的键被拆分成多行的长度
        - 设为`Infinity`则格式化一个对象为单行。
        - 默认为`60`
```js
const util=require("util")
console.log(util.inspect({
    "type": "node",
    "request": "launch",
    "name": "启动程序",
    "program": "测试"
}, { showHidden: true, depth: null }));

```

## util.promisify(original)
- 用于生成一个异步回调

## Class: util.TextDecoder
- `WHATWG`编码标准文本解码API的实现
    - 这里不做扩展
> HTML5的标准制定组织`WHATWG`(Web Hypertext Application Technology Working Group)


```blog
{type: "编程语言", tag:"编程语言,node.js",title:"nodejs-util(工具模块)"}
```
