package com.lzy.service;

import com.lzy.bean.Page;
import com.lzy.dao.UserDao;
import com.lzy.bean.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
public class UserService {
    /**
     * @method:register 用户注册
     * @date: 2017/7/7
     * @params:[name, password, email]
     * @return: boolean
     */
    //1. 判断注册用户是否存在
    public boolean register(User user) {


        boolean register=false;
        UserDao userDao = new UserDao();
        boolean checkUser = userDao.checkUser(user.getName());
        //2. 如果不存在就将用户信息添加到数据库
        if (checkUser) {
             register = userDao.register(user);
        }
        return register;
    }



    public User login(String name, String password) throws SQLException {
        UserDao userDao=new UserDao();
        User user = userDao.login(name, password);
        return  user;
    }


    public boolean addUser(User user) throws SQLException {
        UserDao userDao=new UserDao();
        boolean addUser=userDao.addUser(user);
        return addUser;

    }
    public Page findPageUser(int currentPage, int currentCount) throws SQLException {

        Page page=new Page();
        // 1 查询出会议室的总数
        UserDao dao = new UserDao();
        int totalCount = dao.queryCount();

        // 2 根据总数和当前页显示数 计算出总页数
        int totalPage= (int) Math.ceil(1.0*totalCount/currentCount);
        //3 将分页相关信息封装到page类中
        page.setCurrentCount(currentCount);
        page.setCurrentPage(currentPage);
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);

        int startPosition=(currentPage-1)*currentCount;
        // 分页查询数据
        List<User> users = dao.queryPageUserList(startPosition,currentCount);
        // 将集合数据封装到page类中
        page.setList(users);

        return page;
    }
    public List<User> findUser() throws SQLException {
        UserDao dao = new UserDao();
        List<User> users = dao.queryUserList();
        return users;
    }

    public boolean updateUser(User user) throws SQLException {
        UserDao dao=new UserDao();
        boolean updateUser = dao.updateUser(user);
        return updateUser;
    }
    public boolean deleteUser(User user) throws SQLException {
        UserDao dao=new UserDao();
        boolean deleteUser = dao.deleteUser(user);
        return deleteUser;
    }
}
