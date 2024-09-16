## JDBC 的使用
* 加载数据库驱动
* 获取数据库连接
* 创建 SQL 查询
* 创建 PreparedStatement，设置参数
* 执行查询
* 处理结果集
* 关闭资源
```java
public class JdbcExample {
private static final String URL = "jdbc:mysql://localhost:3306/mydatabase";
private static final String USER = "root";
private static final String PASSWORD = "password";

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 1. 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 获取数据库连接
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // 3. 创建SQL查询
            String sql = "SELECT id, username, age FROM users WHERE id = ?";

            // 4. 创建PreparedStatement
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);

            // 5. 执行查询
            resultSet = preparedStatement.executeQuery();

            // 6. 处理结果集
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                int age = resultSet.getInt("age");
                System.out.println("ID: " + id + ", Username: " + username + ", Age: " + age);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // 7. 关闭资源
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
```

## JDBC 的缺点
* 大量重复代码，比如加载驱动、获取连接、关闭资源等。
* SQL 语句硬编码在 Java 代码中，不利于维护。
* 无法实现对象关系映射（ORM）。
* 无法实现动态 SQL。

## 参考链接
* [JDBC vs MyBatis](https://www.mianshiya.com/bank/1801424748099739650/question/1802959311695323137)