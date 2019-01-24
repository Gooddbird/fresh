package net.zixue.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import net.zixue.bean.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * Created by Administrator on 2017/7/6.
 */
public class UserDao {

    /**
     * @method:checkUser 检查用户是否存在
     * @date: 2017/7/7
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
     * @date: 2017/7/7
     * @params:[name, password, email]
     * @return: boolean
     */

    public boolean register(User user) {


        try {
            ComboPooledDataSource dataSource=new ComboPooledDataSource();
            QueryRunner queryRunner=new QueryRunner(dataSource);
            String sql="insert into user values(null,?,?,?)";
            int row = queryRunner.update(sql, user.getName(), user.getPassword(), user.getEmail());
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


    /**
     * @method:login 查询数据库
     * @date: 2017/7/7
     * @params:[name, password]
     * @return: void
     */
    public User login(String name, String password) throws SQLException {
       ComboPooledDataSource dataSource=new ComboPooledDataSource();
       QueryRunner queryRunner=new QueryRunner(dataSource);
       String sql="select * from user where name=? and password=?";
        User user = queryRunner.query(sql, new BeanHandler<User>(User.class),name,password);
        return  user;
    }
}
