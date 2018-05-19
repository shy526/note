# string_decoder
- 用于把 Buffer 对象解码成字符串,但会保留编码过的多字节`UTF-8`与`UTF-16`字符
- 基本用法
```js
const {StringDecoder}=require('string_decoder');
const decoder = new StringDecoder('utf8');
const cent = Buffer.from([0xC2, 0xA2]);
console.log(decoder.write(cent));
decoder.write(Buffer.from([0xE2]));
decoder.write(Buffer.from([0x82]));
console.log(decoder.end(Buffer.from([0xAC])));
```

## StringDecoder
- 默认:`utf8`

## stringDecoder.end([buffer])
- 以字符串的形式返回内部 buffer 中剩余的字节

## stringDecoder.write(buffer)
- 返回解码后的字符串不包含`Buffer 末尾残缺的多字节字符
    - 残缺的多字节字符会被保存在一个内部的`buffer`中用于下次调用 `stringDecoder.write()`或`stringDecoder.end()`

```blog
{type: "编程语言", tag:"编程语言,node.js",title:"nodejs-string_decoder(字符串解码模块)"}
```
