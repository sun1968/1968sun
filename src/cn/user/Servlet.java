package cn.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.jdbc.DBDao;
import cn.jdbc.User;

public class Servlet extends javax.servlet.http.HttpServlet {


    User user = null;
    private String SQL ="";


    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
       doGet(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setCharacterEncoding("GBK");

        //获取用户名和密码
        String username = request.getParameter("name");
        String password = request.getParameter("word");
        SQL = "select * from login where user = ? and pass = ?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = DBDao.getConnection();
            pstmt = (PreparedStatement) connection.prepareStatement(SQL);
            //这里的意思将用户名和密码填到SQL语句的问号处
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rSet = (ResultSet) pstmt.executeQuery();//得到数据库的查询结果,一个数据集
            //判断结果集是否有效
            if(rSet.next()){
                user = new User();
                user.setUsername(rSet.getString("user"));
                user.setPassword(rSet.getString("pass"));
                request.getRequestDispatcher("/main1.jsp").forward(request, response);
            }
            connection.close();
            pstmt.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            DBDao.closeConnection(connection);
        }

    }


    }



