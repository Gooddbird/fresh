package com.lzy.dao;

import com.lzy.bean.Room;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class RoomDao {
    public boolean addRoom(Room room) throws SQLException {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        QueryRunner queryRunner=new QueryRunner(dataSource);
        String sql="insert into room values(null,?,?,?)";
        int row=queryRunner.update(sql,room.getR_name(),room.getContent(),room.getStation());
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
        String sql="select count(*) from room";
        Long query = queryRunner.query(sql, new ScalarHandler<>());
        return query.intValue();
    }
    public List<Room> queryRoomList() throws SQLException {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        QueryRunner queryRunner=new QueryRunner(dataSource);
        String sql="select * from room";
        List<Room> roomList = queryRunner.query(sql, new BeanListHandler<Room>(Room.class));
        return roomList;
    }
    public List<Room> queryPageRoomList(int startPosition, int currentCount) throws SQLException {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        QueryRunner queryRunner=new QueryRunner(dataSource);
        String sql="select * from room limit ?,?";
        List<Room> roomList = queryRunner.query(sql, new BeanListHandler<Room>(Room.class),startPosition,currentCount);
        return roomList;
    }
    public boolean updateRoom(Room room) throws SQLException {

        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        QueryRunner queryRunner=new QueryRunner(dataSource);
        String sql="update room set r_name=?,content=?,station=? where r_id=?";
        int row = queryRunner.update(sql, room.getR_name(), room.getContent(), room.getStation(),room.getR_id());
        if (row>0){
            return true;
        }else {
            return false;
        }
    }
    public boolean deleteRoom(Room room) throws SQLException {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        QueryRunner queryRunner=new QueryRunner(dataSource);
        String sql="delete from room where r_id=?";
        int row = queryRunner.update(sql,room.getR_id());
        return row>0?true:false;
    }
}