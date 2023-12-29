package dao.custom.impl;

import dao.utill.CrudUtil;
import db.DBConnection;
import dto.CustomerDto;
import dao.custom.CustomerDao;
import entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        String sql = "insert into customer values('" + entity.getCustId() + "','" + entity.getCustName() + "','" + entity.getCustAddress()+ "'," + entity.getSalary() + ")";

        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();
        int res = stm.executeUpdate(sql);
        return res>0 ? true :false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        String sql = "delete from customer where custId='" + value + "'";
        Connection conn =DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();
        int res = stm.executeUpdate(sql);
        if (res>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        String sql = "update Customer set custName='" + entity.getCustName() + "'," + "custAddress='" + entity.getCustAddress() + "'," + "Salary=" + entity.getSalary() + " where custid='" + entity.getCustId() + "'";
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();
        int res = stm.executeUpdate(sql);
        if (res>0)return true;
        return false;
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        List<Customer> list=new ArrayList<>();
        String sql = "select * from customer";

//        Connection conn= DBConnection.getInstance().getConnection();
//        PreparedStatement pstm= conn.prepareStatement(sql);
        ResultSet rst = CrudUtil.exicute(sql);
        while (rst.next()){
            list.add(new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4)
            ));

        }
        return list;
    }
}