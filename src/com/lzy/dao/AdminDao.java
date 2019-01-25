package com.lzy.dao;

import com.lzy.bean.Admin;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class AdminDao {
    public Admin login(String a_name, String a_password) throws SQLException {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        QueryRunner queryRunner=new QueryRunner(dataSource);
        String sql="select * from admin where a_name=? and a_password=?";
        Admin admin = queryRunner.query(sql, new BeanHandler<Admin>(Admin.class),a_name,a_password);
        return  admin;
    }

}
