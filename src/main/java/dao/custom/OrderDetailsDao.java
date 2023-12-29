package dao.custom;

import dao.CrudDao;
import dto.OrderDetailsDto;
import entity.OrderDetail;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsDao extends CrudDao<OrderDetail> {
    boolean saveOrderDetails(List<OrderDetailsDto> list) throws SQLException, ClassNotFoundException;
    ObservableList<OrderDetailsDto> getOrderDetail(String orderId) throws SQLException, ClassNotFoundException;
}
