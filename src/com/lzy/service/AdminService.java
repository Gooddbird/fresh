package com.lzy.service;

import com.lzy.bean.Admin;
import com.lzy.dao.AdminDao;

import java.sql.SQLException;

public class AdminService {

    public Admin login(String a_name, String a_password) throws SQLException {
        AdminDao adminDao=new AdminDao();
        Admin admin=adminDao.login(a_name,a_password);
        return admin;

    }
}
