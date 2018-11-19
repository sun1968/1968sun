package cn.JBDao;

import cn.jdbc.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public interface ADDao {
    public boolean add(String username,String password,java.sql.Date borthday, String qq, String sex );
    public List<User> selectall();
    public boolean update(int studentId,String username,String password,java.sql.Date borthday, String qq, String sex );
    public boolean delete(int studentId);
    public  List<User> selectById(String string);
    public  List<User> selectByQq(String string);



}
