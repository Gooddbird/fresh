package com.lzy.web;

import com.lzy.bean.Page;
import com.lzy.bean.User;
import com.lzy.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "UserServlet",urlPatterns = "/user")
public class UserServlet extends BaseServlet {

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String u_name = request.getParameter("u_name");
        String u_password = request.getParameter("u_password");

        UserService userService=new UserService();
        User user=null;
        try {
            //调用service中登录方法
            user = userService.login(u_name, u_password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user!=null){
            //登录成功后我们再获取是否保存密码的信息，如果失败了保存密码就没有意义了
            String remember = request.getParameter("remember");
            if (remember!=null&&remember.equals("yes")){
                // 将用户名和密码加入到cookie中
                Cookie nameCookie = new Cookie("u_name", u_name);
                Cookie passwordCookie = new Cookie("u_password", u_password);
                //设置cookie的有效期 防止销毁
                nameCookie.setMaxAge(60*10);
                passwordCookie.setMaxAge(60*10);
                //将cookie发送给客户端保存
                response.addCookie(nameCookie);
                response.addCookie(passwordCookie);

            }
            request.getSession().setAttribute("user",user);
            //登录成功跳转
            response.sendRedirect(request.getContextPath()+"/room?method=getReserveRoomList&currentPage=1&currentCount=10");
        }else {
            //登录失败提示
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("用户登录失败");

        }

    }
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user=new User();
        BeanUtils.populate(user,parameterMap);
        UserService userService=new UserService();
        boolean register = userService.register(user);
        if (register) {
            response.sendRedirect(request.getContextPath()+"userLogin.jsp");
        }else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("注册失败");
        }
    }
    public void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // 获取参数 通过BeanUtils封装实体类
            Map<String, String[]> parameterMap = request.getParameterMap();
            User user=new User();
            BeanUtils.populate(user,parameterMap);
            UserService userService=new UserService();
            boolean b = userService.addUser(user);
            if (b){
                //添加成功
                response.setStatus(201);
                request.getRequestDispatcher("/user-add.jsp").forward(request,response);
            }else {
                // 添加失败
                response.setStatus(600);
                request.getRequestDispatcher("/user-add.jsp").forward(request,response);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void getUserList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1 调用service中的查询方法
        try {
            int currentPage = Integer.parseInt(request.getParameter("currentPage"));
            int currentCount = Integer.parseInt(request.getParameter("currentCount"));
            // 给分页数据设置默认值
            if (currentCount==0){
                currentCount=10;
            }
            if (currentPage==0){
                currentPage=1;
            }
            UserService service=new UserService();
            Page page = service.findPageUser(currentPage, currentCount);
            if (page!=null) {
                request.setAttribute("page",page);
                request.getRequestDispatcher("/user-list.jsp").forward(request,response);
            }else {
                request.getRequestDispatcher("/user-list.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1 调用service中的查询方法
            Map<String, String[]> parameterMap = request.getParameterMap();
            User user=new User();
            BeanUtils.populate(user,parameterMap);
            UserService service=new UserService();
            boolean updateUser = service.updateUser(user);

            if (updateUser){
                response.sendRedirect(request.getContextPath()+"/user?method=getRoomList&currentPage=1&currentCount=10");
            }else {
                // 失败了直接提示
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("修改失败");
            }


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1 调用service中的查询方法
            Map<String, String[]> parameterMap = request.getParameterMap();
            User user=new User();
            BeanUtils.populate(user,parameterMap);
            UserService service=new UserService();
            boolean deleteUser = service.deleteUser(user);

            if (deleteUser){

                response.sendRedirect(request.getContextPath()+"/user?method=getRoomList&currentPage=1&currentCount=10");

            }else {
                // 失败了直接提示
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("删除失败");
            }


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void getUserFace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            // 1 调用service中的查询方法
            Map<String, String[]> parameterMap = request.getParameterMap();
            User user=new User();
            BeanUtils.populate(user,parameterMap);
            UserService service=new UserService();
            boolean getUserFace = service.getUserFace(user);

            if (getUserFace){
                response.sendRedirect(request.getContextPath()+"/user?method=getRoomList&currentPage=1&currentCount=10");
            }else {
                // 失败了直接提示
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("失败");
            }


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
