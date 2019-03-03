package com.lzy.web;

import com.lzy.bean.Page;
import com.lzy.bean.Room;
import com.lzy.service.RoomService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "RoomServlet",urlPatterns = "/room")
public class RoomServlet extends BaseServlet {
    public void addRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // 获取参数 通过BeanUtils封装实体类
            Map<String, String[]> parameterMap = request.getParameterMap();
            Room room=new Room();
            BeanUtils.populate(room,parameterMap);
            RoomService roomService=new RoomService();
            boolean b = roomService.addRoom(room);
            if (b){
                //添加成功
                response.setStatus(201);
                request.getRequestDispatcher("/room-add.jsp").forward(request,response);
            }else {
                // 添加失败
                response.setStatus(600);
                request.getRequestDispatcher("/room-add.jsp").forward(request,response);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void getRoomList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            RoomService service=new RoomService();
            Page page = service.findPageRoom(currentPage, currentCount);
            if (page!=null) {
                request.setAttribute("page",page);

                    request.getRequestDispatcher("/room-list.jsp").forward(request,response);

            }else {
                    request.getRequestDispatcher("/room-list.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void getReserveRoomList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            RoomService service=new RoomService();
            Page page = service.findPageRoom(currentPage, currentCount);
            if (page!=null) {
                request.setAttribute("page",page);
                request.getRequestDispatcher("/reserve.jsp").forward(request,response);
            }else {
                request.getRequestDispatcher("/reserve.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1 调用service中的查询方法
            Map<String, String[]> parameterMap = request.getParameterMap();
            Room room=new Room();
            BeanUtils.populate(room,parameterMap);
            RoomService service=new RoomService();
            boolean updateRoom = service.updateRoom(room);

            if (updateRoom){
                // 修改成功
                response.sendRedirect(request.getContextPath()+"/room?method=getRoomList&currentPage=1&currentCount=10");

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
    public void deleteRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1 调用service中的查询方法
            Map<String, String[]> parameterMap = request.getParameterMap();
            Room room=new Room();
            BeanUtils.populate(room,parameterMap);
            RoomService service=new RoomService();
            boolean updateRoom = service.deleteRoom(room);

            if (updateRoom){
                // 删除成功后重定向到生鲜列表界面
                response.sendRedirect(request.getContextPath()+"/room?method=getRoomList&currentPage=1&currentCount=10");

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
    public void reserveRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try {
            Map<String, String[]> parameterMap = request.getParameterMap();
            Room room=new Room();
            BeanUtils.populate(room,parameterMap);
            RoomService roomService=new RoomService();
            boolean reserveRoom=roomService.reserveRoom(room);
            if(reserveRoom) {
                //预订成功后显示成功页面
                response.sendRedirect(request.getContextPath()+"/room?method=getReserveRoomList&currentPage=1&currentCount=10");
            }else{
                //预订失败后显示失败信息
                System.out.println("1");
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
