# timer
- 提供了一个全局的api
- 实现了与web浏览器提供的定时器类似的api

## setImmediate(callback[, ...args])\
- callback `<Function>` 
    - 预定的调用的函数
- ...args `<any>` 
    - 调用`callback`时要传入的可选参数

## setInterval(callback, delay[, ...args])
- callback `<Function>`
- delay `<number>` 
    - 毫秒数。
- ...args `<any>`

## setTimeout(callback, delay[, ...args])
- callback `<Function>`
- delay `<number>`
> 大于 `2147483647`或小于 `1`时会被设为`1`
- ...args `<any>`
