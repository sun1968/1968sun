package cn.user;

import cn.JBDao.ADDao;
import cn.JBDao.ADDaoImpl;
import cn.jdbc.DBDao;
import cn.jdbc.User;
import org.apache.commons.fileupload.util.LimitedInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static java.lang.System.out;


//@WebServlet(name = "adServlet")
public class adServlet extends HttpServlet {
    Connection conn = (Connection) DBDao.getConnection();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        String passWord = request.getParameter("password");
        String borth = request.getParameter("borthday");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date Borthday = null;
        java.sql.Date sqlB = null;
        try {
            Borthday = formatter.parse(borth);
            sqlB = new java.sql.Date(Borthday.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        String qq = request.getParameter("qq");
        String sex = request.getParameter("sex");
        User User = new User();
        User.setQq(qq);
        User.setUsername(username);
        User.setPassword(passWord);
        User.setBorthday(Borthday);
        User.setSex(sex);
        ADDao sd = new ADDaoImpl();
        try {
            sd.add(username, passWord, sqlB, qq, sex);
        } catch (Exception e) {
            out.println("添加失败");
            e.printStackTrace();
        }
        try {
            String sql = "select * from user where qq=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,qq );
            ResultSet rs = pstmt.executeQuery();
        if(rs!=null){
            JOptionPane.showMessageDialog(null, "你输入的qq已存在，不要重复添加", "alert", JOptionPane.ERROR_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, "添加成功", "alert", JOptionPane.ERROR_MESSAGE);
        }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
