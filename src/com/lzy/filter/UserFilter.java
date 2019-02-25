package com.lzy.filter;

import com.lzy.bean.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "UserFilter",urlPatterns = "/category")
public class UserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
            req.setCharacterEncoding("utf-8");
            resp.setContentType("text/html;charset=utf-8");

        // 1 获取serssion 校验用户信息
        HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // 2 判断user信息是否为空 ，空则跳转登录界面
        if (user==null) {
            response.sendRedirect(request.getContextPath()+"/login.jsp");
            return;
        }
        // 3 user不为空 放行
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
