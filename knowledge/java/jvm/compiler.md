## Java 程序执行流程

<img src="/knowledge/assets/java/java-exec.png" width="750">

## JVM 编译器
* Client Compiler：C1 编译器，主要用于客户端，编译速度快，优化程度低。
* Server Compiler：默认使用 C2 编译器，主要用于服务端，编译速度慢，优化程度高（大概快 30%）。
### Client Compiler
Client Compiler 只做以下事情：
* 在字节码上做一些基础优化，比如**方法内联、常量传播**等。
* 将字节码构造为高级中间表示（HIR，High-level Intermediate Representation）,便于后续的优化。
* 将 HIR 转换为低级中间表示（LIR，Low-level Intermediate Representation），在 LIR 基础上做**寄存器分配、窥孔优化**等优化，最终生成机器码。

### Server Compiler
Server Compiler 有两种：C2 和 Graal。

## 编译优化
### 方法内联
方法内联是指将一个方法的代码直接插入到调用该方法的地方，减少方法调用的开销。

### 逃逸分析
逃逸分析是指分析对象的作用域，如果一个对象只在方法内部使用，不会被外部引用，那么这个对象就是不逃逸的。
* 栈上分配：逃逸分析后，对象不会被外部引用，可以在栈上分配，减少 GC 压力。
* 锁消除：如果一个对象只在单线程中访问，那么这个对象就是线程私有的，不需要加锁。
* 标量替换：如果一个对象的成员变量都是基本类型，那么这个对象可以被拆分成多个基本类型，分别存储在栈上。

### Loop Transformations
一般在 C2 编译器中使用，对循环进行优化，比如**循环展开、循环分离**等。
* 循环展开：将循环体重复执行多次，减少循环开销。空间换时间，增加字节码长度，提高执行性能。
* 循环分离：将循环中一次或多次的特殊迭代提取出来，减少循环次数。

### 窥孔优化
窥孔优化是指在生成的机器码中找到一些可以优化的地方，比如**常量合并、强度削减**等。
* 常量合并：将多个常量合并成一个常量，减少内存占用。
* 强度削减：将乘法运算转换为移位运算，减少计算量。

### 寄存器分配
将频繁使用的变量存储在寄存器中，减少内存访问次数，提高执行效率。

## 参考链接
* [JVM Compiler](https://tech.meituan.com/2020/10/22/java-jit-practice-in-meituan.html)
* [JVM 优化](https://www.nenggz.com/md/java/jvm/java-jvm-struct.html)