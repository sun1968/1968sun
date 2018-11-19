package cn.jdbc;
import java.sql.*;
import java.sql.ResultSet;

public class UserDao {
    User user = null;
    private String SQL ="";
    public User login(String name, String word){
        SQL = "select * from login where username = ? and password = ?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = DBDao.getConnection();
            pstmt = (PreparedStatement) connection.prepareStatement(SQL);
            //这里的意思将用户名和密码填到SQL语句的问号处
            pstmt.setString(1, name);
            pstmt.setString(2, word);
            ResultSet rSet = (ResultSet) pstmt.executeQuery();//得到数据库的查询结果,一个数据集
            //判断结果集是否有效
            if(rSet.next()){
                user = new User();
                user.setUsername(rSet.getString("name"));
                user.setPassword(rSet.getString("word"));

            }
            connection.close();
            pstmt.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            DBDao.closeConnection(connection);
        }
        return user;
    }


}
