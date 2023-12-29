package dao.custom.impl;

import dao.utill.CrudUtil;
import db.DBConnection;
import dto.OrderDto;
import dao.custom.OrderDetailsDao;
import dao.custom.OrderDao;
import entity.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @Override
    public OrderDto lastOrder() throws SQLException, ClassNotFoundException {
        String sql="select * from orders ORDER BY OrderId DESC limit 1";

        ResultSet res=CrudUtil.exicute(sql);

            while (res.next()){
                return new OrderDto(
                        res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        null
                );
            }

        return null;
    }

    @Override
    public boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException {
        OrderDetailsDao orderDetailsModel=new OrderDetailDaoImpl();
        Connection connection=null;
        try {
            connection = DBConnection.getInstance().getConnection();

            connection.setAutoCommit(false);//*************8

            String sql = "insert into orders Values(?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, dto.getOrderId());
            pstm.setString(2, dto.getDate());
            pstm.setString(3, dto.getCustomerId());
            if (pstm.executeUpdate() > 0) {
                boolean isSaved = orderDetailsModel.saveOrderDetails(dto.getList());
                if (isSaved) {
                    connection.commit();
                    return true;
                }
            }
        }catch (SQLException|ClassNotFoundException ex){
            connection.rollback();
            ex.printStackTrace();
            connection.setAutoCommit(true);
        }

        return false;
    }



    @Override
    public List<Orders> getAll() throws SQLException, ClassNotFoundException {
        String sql="select * from orders";
        List<Orders> list=new ArrayList<>();

        ResultSet res=CrudUtil.exicute(sql);
        while (res.next()){
            list.add( new Orders(
                    res.getString(1),
                    res.getString(2),
                    res.getString(3)
            ));
        }
        return list;
    }

    @Override
    public boolean save(Orders entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Orders entity) throws SQLException, ClassNotFoundException {
        return false;
    }
}
