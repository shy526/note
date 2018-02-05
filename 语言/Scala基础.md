## Scala 基础

### 基础关键字
- `val`关键字
  - `val x=1;`
    - 声明一个常量
- `var` 关键字
  - `var x;`
    - 定义一个变量
- `:`
  - `var x :Int`
    - 定义一个int型的变量
  - 明确数据类型
  - 省略时会自动推倒 类似 C++`auto`关键字

- `{}`块
  - `println({var x=2; x*2;})`
  - 独立的代码块
    - 有其独立作用域
  - 类似 匿名内部类 不同的是不需要显式不可变或隐式不可变
- `lambda`匿名函数
  - `val add=(x:Int,y:Int)=>x+y`
    -  定义个匿名函数
  - 类似 c++指针 指向 方法的地址

- `def(参数列表)[(参数列表)...]:返回类型= [{执行语句}|执行语句(单行)]`
  - `def add(a:Int,b:Int)(y:Int): Int =(a+b)*y;` 
    - 定一个方法
  - 支持多个参数列表 
  - 不用显式的`return`关键字
  - 支持`Unit`
    - 与`void`类似
- `class`关键字
  - `class Point(x:Int,y:Int)`
  - 定义一个类和一个构造函数
  
- `case` 关键字
  - `case class Point(x:Int,y:Int)`
    - 定义一个不可变的类
  - 特性 类似 java String类
  -  创建实例时可以省略`new`关键字

- `object`关键字
  - 自动添加`static`使其变为静态变量,或静态方法
  - `def main(args: Array[String])` 
    - main 程序入口 采取一个参数为字符串数组

- `trait`
  - 特征
  - 类似抽象类 可以提供默认实现或者不实现
  - `class` 通过 `extends`关键字继承`trait`特征
    - 继承方法未实现 需要使用`override` 关键字修饰 重写

### 类型
- `Any`
  - 顶级父类
  - 定义了某些通用的方法
  - 与java Object类 类似
  - 拥有两个直接子类
    - `AnyVal`
    - `AnyRef`
- `AnyVal`
  - 定义了9种基本类型
  - `Double`,`Float`,`Long`,`Int`,`Short`,`Byte`,`Char`,`Unit`,`Boolean`
    - `Unit`:`()`可以用空括号声明
      - 没有任何意思
- `AnyRef`
  - 定义引用类型
  - 每一个自定义类型都是`AnyRef`的子类
  - 在java环境中运行则对应`java.lang.Object`
- `Nothing`
  - 是所有类型的子类型
    - 没有价值
  - `Null`
    - 所有引用类型的子类型 
      - 主要为其他JVM语言预留
- 所有数值类型都是对象

```scala
def main(args: Array[String]) {
    var list:List[Any]= List(   "字符串",
      111,
      'c',
      true,
      ( ) => "void",
      111L,
      1.0F,
      2.0D
    )
  list.foreach(value=>println(value))
}
```
### 隐式转换
- Byte->Short->Int->Long->Long->Float->Double
- Char->Int
> 不可逆
## 类







```blog
{type: "编程语言", tag:"编程语言,Scala",title:"Scala基础"}
```
