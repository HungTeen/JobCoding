## 框架
### 层次结构
<img src="/knowledge/assets/mybatis/mybatis-arch.png" width="750">

* SqlSession 是 Mybatis 工作的顶层 API，作为会话访问。
* Executor 是执行层，管理缓存、负责动态语句以及事务管理的生成。

### Dao 接口
* Dao 接口即 Map 接口，定义了数据库操作的方法。
* Mybatis 运行时会为 Dao 接口生成对于的代理对象。
* Dao 接口的方法与 xml 文件中的 id 一一对应。
* 代理对象会拦截 Dao 接口的方法，转而执行 xml 文件中的 SQL 语句。

### 动态 SQL
* 解析动态 SQL：解析 XML 文件中的动态 SQL 标签。
* 参数绑定：SQL 语句执行前，通过反射设置参数。
* 生成最终SQL & 执行。

#### 动态 SQL 标签
* `<if>`：判断条件是否成立。
* `<foreach>`：遍历集合。
* `<choose>` & `<when>` & `<otherwise>`：类似于 Java 中的 switch 语句。

### MyBatis 与 Hibernate 的区别 ？
* Hibernate 是全自动的，不能精细化控制 SQL 语句。
* MyBatis 是半自动的，可以精细化控制 SQL 语句。
* Hibernate 适合快速开发，MyBatis 适合性能调优。

### Mybatis-Plus 与 Mybatis 的区别 ？
* Mybatis-Plus 是 Mybatis 的增强工具，提供了更多的功能。
* Mybatis-Plus 有代码生成器，使得重复的 CRUD 操作更加简单。
* Mybatis-Plus 提供条件构造器，使得 SQL 语句更加灵活。

### Mybatis 的优点 ？
* 相比 JDBC，Mybatis 可以减少代码量，提高开发效率。
* 相比 Hibernate，Mybatis 可以更加灵活地控制 SQL 语句。
* Mybatis 有缓存机制，可以提高查询效率。
* Mybatis 有连接池，可以提高数据库连接的复用率。
* Mybatis 有动态 SQL，可以根据条件生成不同的 SQL 语句。
* Mybatis 有插件机制，可以扩展功能。

### Mybatis 的缺点 ？
* 大量的 XML 配置文件，使得维护成本较高。
* 简单的 SQL 语句也需要写 XML 文件，使得开发效率降低。

## 连接池
* 有两种连接池，非池化连接（每次都创建新的）和池化连接（在连接池中复用）。
* 连接池中有活跃连接和空闲连接，活跃连接是正在使用的，空闲连接是可以复用的。

### 为什么需要连接池？
* 数据库连接的创建和销毁是非常耗时的。
* 使用多个数据库连接可以提高并发性能（所以不用单例模式）。

<img src="/knowledge/assets/mybatis/mybatis-conn.png">

## 缓存机制
### 一级缓存
* 基于 SqlSession 的缓存，只在当前 SqlSession 中有效，同一个会话中的多个相同查询可以得到优化。
* 当会话调用增删改时，会清空缓存。
* 但如果别的会话对数据库进行了增删改，当前会话的缓存不会失效。

### 二级缓存
* 基于 namespace 的缓存，多个 SqlSession 可以共用。
* 当进行多表联查时，如果多个命名空间对多个表的数据发生修改，还是会读到旧数据。
* 开启二级缓存后，会先查二级缓存，再查一级缓存，最后查数据库。

## 参考链接
* [MyBatis 缓存机制](https://www.mianshiya.com/bank/1801424748099739650/question/1802597639336042498)
* [Mybatis 连接池](https://www.nenggz.com/md/framework/orm-mybatis/mybatis-y-datasource.html)