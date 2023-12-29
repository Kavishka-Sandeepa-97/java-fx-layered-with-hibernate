package boService.Custom.impl;

import boService.Custom.OrderBo;
import dao.DaoFactory;
import dao.custom.OrderDao;
import dao.utill.DaoType;
import dto.ItemDto;
import dto.OrderDto;
import entity.Item;
import entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OrderBoImpl implements OrderBo {

    public OrderDao orderDao=DaoFactory.getInstance().getDao(DaoType.ORDER);

    public boolean saveOrder(OrderDto orderDto) throws SQLException, ClassNotFoundException {
        return orderDao.saveOrder(orderDto);
    }

    @Override
    public String genarateOrderId() throws SQLException, ClassNotFoundException {

            OrderDto orderDto = orderDao.lastOrder();
            if (orderDto == null) {
                return ("D001");
            } else {
                String string = orderDto.getOrderId();
                int id = Integer.parseInt(string.split("D")[1]);
                id++;
                return String.format("D%03d", id);
            }

    }

    @Override
    public List<OrderDto> allOrder() throws SQLException, ClassNotFoundException {
        List<Orders> all = orderDao.getAll();
        List<OrderDto> dtoList=new ArrayList<>();
        for( Orders dto:all){
            dtoList.add(new OrderDto(
                    dto.getOrderId(),
                    dto.getOrderDate(),
                    dto.getCustId(),
                    null
            ));
        }
        return dtoList;
    }


}
