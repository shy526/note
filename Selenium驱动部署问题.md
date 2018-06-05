# liunx

## 错误描述
- liunx下执行 selenium 应用
    - Exception in thread "main" java.lang.IllegalStateException: The driver is not executable:
## 解决方法
1. 检查权限
    - `file.canExecute()`
        - 检查执行权
2. 设置权限
    - `file.setExecutable(true);`

```blog
{type: "Selenium", tag:"Selenium",title:"Selenium driver is not executable问题"}
```
