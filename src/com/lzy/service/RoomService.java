package com.lzy.service;

import com.lzy.bean.Page;
import com.lzy.bean.Room;
import com.lzy.dao.RoomDao;

import java.sql.SQLException;
import java.util.List;

public class RoomService {
    public boolean addRoom(Room room) throws SQLException {
        RoomDao roomDao=new RoomDao();
        boolean addRoom=roomDao.addRoom(room);
        return addRoom;
    }

    public Page findPageRoom(int currentPage,int currentCount) throws SQLException {

        Page page=new Page();
        // 1 查询出会议室的总数
        RoomDao dao = new RoomDao();
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
        List<Room> rooms = dao.queryPageRoomList(startPosition,currentCount);
        // 将集合数据封装到page类中
        page.setList(rooms);

        return page;
    }
    public List<Room> findRoom() throws SQLException {
        RoomDao dao = new RoomDao();
        List<Room> rooms = dao.queryRoomList();
        return rooms;
    }

    public boolean updateRoom(Room room) throws SQLException {
        RoomDao dao=new RoomDao();
        boolean updateRoom = dao.updateRoom(room);
        return updateRoom;
    }
    public boolean deleteRoom(Room room) throws SQLException {
        RoomDao dao=new RoomDao();
        boolean deleteRoom = dao.deleteRoom(room);
        return deleteRoom;
    }

    public boolean reserveRoom(Room room) throws SQLException {
        RoomDao roomDao=new RoomDao();
        boolean reserveRoom=roomDao.reserveRoom(room);
        return reserveRoom;
    }
}
