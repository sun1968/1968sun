package cn.user;

import cn.JBDao.ADDao;
import cn.JBDao.ADDaoImpl;
import cn.jdbc.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//@WebServlet(name = "updateServlet")
public class updateServlet extends HttpServlet {
        public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doPost(request, response);

        }
        public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
           request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");

            int studentId = Integer.parseInt(request.getParameter("studentId"));
            String username=request.getParameter("username");
            String password=request.getParameter("password");

            String qq=request.getParameter("qq");
            String sex=request.getParameter("sex");
            String borth=request.getParameter("borthday");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date Borthday = null;
            java.sql.Date sqlB=null;

            try {
                Borthday = formatter.parse(borth);
                sqlB=new java.sql.Date(Borthday.getTime());

            } catch (ParseException e) {
                e.printStackTrace();
            }

            ADDao sd = new ADDaoImpl();

            try{
                sd.update(studentId, username,password,sqlB,qq,sex);
            }catch(Exception e) {
                System.out.println("修改失败！");
                e.printStackTrace();
            }

        }

    }