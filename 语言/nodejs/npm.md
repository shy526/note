# npm
- npm是随nodejs一起安装的包管理工具,能解决nodejs代码部署,第三方包使用的

## npm指令
- `npm -v`
  - 查看版本
- `npm install npm -g`
  - 更新npm
- `npm install {module name}`
  - 安装某个模块
  - 可选参数
    - `-g`
      - 全局安装将安装包放在node的安装目录
      - 命令可以直接使用
    - 本地安装
      - 在所在目录生成node_modules目录
      - 可以通过require引入
- `npm list -g`
  - 查看全局安装模块
- `npm uninstall {module name}`
  - 卸载模块'
- `npm update  {module name}`
  - 更新模块
- `npm search {module name}`
  - 搜索模块

## package.json
- 用于定义包的属性

## 替换npm 镜像
- `npm install -g cnpm --registry=https://registry.npm.taobao.org`
  - 淘宝
