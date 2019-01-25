package com.lzy.web;

import com.lzy.bean.Admin;
import com.lzy.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminServlet",urlPatterns = "/admin")
public class AdminServlet extends BaseServlet {

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String a_name = request.getParameter("a_name");
        String a_password = request.getParameter("a_password");
        AdminService adminService=new AdminService();
        Admin admin =null;
        try {
            //调用service中登录方法
            admin = adminService.login(a_name, a_password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (admin!=null){
            //登录成功后我们再获取是否保存密码的信息，如果失败了保存密码就没有意义了
            String remember = request.getParameter("remember");
            if (remember!=null&&remember.equals("yes")){
                // 将用户名和密码加入到cookie中
                Cookie nameCookie = new Cookie("a_name", a_name);
                Cookie passwordCookie = new Cookie("a_password", a_password);
                //设置cookie的有效期 防止销毁
                nameCookie.setMaxAge(60*10);
                passwordCookie.setMaxAge(60*10);
                //将cookie发送给客户端保存
                response.addCookie(nameCookie);
                response.addCookie(passwordCookie);

            }
            request.getSession().setAttribute("admin",admin);
            //登录成功跳转生鲜种类列表界面
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("登录成功");
            System.out.println("1");
            response.sendRedirect(request.getContextPath()+"/room?method=getRoomList&currentPage=1&currentCount=10");
        }else {
            //登录失败提示
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("登录失败");

        }

    }
}
