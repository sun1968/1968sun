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
import java.util.List;

@WebServlet(name = "showallServlet")
public class showallServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        ADDao sd = new ADDaoImpl();
        List<User> list = sd.selectall();
        request.setAttribute("list",list);
        request.getRequestDispatcher("/show.jsp").forward(request, response);
    }
}
