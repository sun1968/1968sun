package cn.jdbc;
import java.sql.*;
public class DBDao {

    //  private static String SQL = "";
    private static Connection connection = null;

    //连接数据库
    public static Connection getConnection(){
         String USER = "root";
         String PASSWORD = "root";
         String DB_URL = "jdbc:mysql://localhost:3306/test";
         String DB_DRIVER = "com.mysql.jdbc.Driver";

        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

        } catch (Exception e) {
            System.out.println("数据库连接异常");
            e.printStackTrace();
        }
        return connection;
    }
    public  static void closeConnection(Connection connection){

        if(connection != null){
            try {
                connection.close(); // 关闭数据库连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
