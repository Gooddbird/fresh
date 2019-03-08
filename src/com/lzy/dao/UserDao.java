package com.lzy.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.lzy.bean.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;


public class UserDao {

    /**
     * @method:checkUser 检查用户是否存在
     * @params:[name]
     * @return: boolean
     */

    public boolean checkUser(String name){

        try {
            ComboPooledDataSource dataSource=new ComboPooledDataSource();
            QueryRunner queryRunner=new QueryRunner(dataSource);
            String sql="select name from user where name=?";
            User user = queryRunner.query(sql, new BeanHandler<User>(User.class),name);
            //如果没有查询到数据 说明这个用户名没有注册过
            if (user==null) {
                return  true;
            }else {
                return  false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }

    }
    /**
     * @method:register 用户注册
     * @params:[name, password, email]
     * @return: boolean
     */

    public boolean register(User user) {


        try {
            ComboPooledDataSource dataSource=new ComboPooledDataSource();
            QueryRunner queryRunner=new QueryRunner(dataSource);
            String sql="insert into user values(null,?,?,?,?,?)";
            int row = queryRunner.update(sql, user.getName(), user.getPassword(), user.getSex(),user.getFace(),user.getEmail());
            //行数大于零说明注册成功
            if (row>0) {
                System.out.println(row);
                return  true;
            }else {
                return  false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }

    }


    public User login(String name, String password) throws SQLException {
       ComboPooledDataSource dataSource=new ComboPooledDataSource();
       QueryRunner queryRunner=new QueryRunner(dataSource);
       String sql="select * from user where name=? and password=?";
        User user = queryRunner.query(sql, new BeanHandler<User>(User.class),name,password);
        return  user;
    }

    public boolean addUser(User user) throws SQLException {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        QueryRunner queryRunner=new QueryRunner(dataSource);
        String sql="insert into user values(null,?,?,?,?,?)";
        int row=queryRunner.update(sql,user.getName(),user.getPassword(),user.getSex(),user.getFace(),user.getEmail());
        if(row>0)
        {
            return true;
        }else{
            return false;
        }
    }
    public int queryCount() throws SQLException {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        QueryRunner queryRunner=new QueryRunner(dataSource);
        String sql="select count(*) from user";
        Long query = queryRunner.query(sql, new ScalarHandler<>());
        return query.intValue();
    }
    public List<User> queryUserList() throws SQLException {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        QueryRunner queryRunner=new QueryRunner(dataSource);
        String sql="select * from user";
        List<User> userList = queryRunner.query(sql, new BeanListHandler<User>(User.class));
        return userList;
    }
    public List<User> queryPageUserList(int startPosition, int currentCount) throws SQLException {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        QueryRunner queryRunner=new QueryRunner(dataSource);
        String sql="select * from user limit ?,?";
        List<User> userList = queryRunner.query(sql, new BeanListHandler<User>(User.class),startPosition,currentCount);
        return userList;
    }
    public boolean updateUser(User user) throws SQLException {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        QueryRunner queryRunner=new QueryRunner(dataSource);
        String sql="update user set name=?,password=?,sex=? ,face=?,email=? where id=?";
        int row=queryRunner.update(sql,user.getName(),user.getPassword(),user.getSex(),user.getFace(),user.getEmail());
        if(row>0)
        {
            return true;
        }else{
            return false;
        }
    }
    public boolean deleteUser(User user) throws SQLException {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        QueryRunner queryRunner=new QueryRunner(dataSource);
        String sql="delete from user where id=?";
        int row=queryRunner.update(sql,user.getName(),user.getPassword(),user.getSex(),user.getFace(),user.getEmail());
        if(row>0)
        {
            return true;
        }else{
            return false;
        }
    }
    public boolean getUserFace(User user) throws SQLException {
        try {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql = "select face from user where id=?";
            user = queryRunner.query(sql, new BeanHandler<User>(User.class), user.getId());
            //如果没有查询到数据 说明这个用户名没有注册过
            if (user == null) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
