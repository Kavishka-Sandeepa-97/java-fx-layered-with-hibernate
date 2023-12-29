package boService.Custom.impl;

import boService.Custom.ItemBo;
import dao.DaoFactory;
import dao.custom.ItemDao;
import dao.utill.DaoType;
import dto.CustomerDto;
import dto.ItemDto;
import entity.Customer;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBo {
    ItemDao itemDao= DaoFactory.getInstance().getDao(DaoType.ITEM);
    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDao.save(new Item(
                dto.getItemCode(),
                dto.getDesc(),
                dto.getUnitePrice(),
                dto.getQty()
        ));

    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDao.update(new Item(
                dto.getItemCode(),
                dto.getDesc(),
                dto.getUnitePrice(),
                dto.getQty()
        ));
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDao.delete(id);
    }

    @Override
    public List<ItemDto> allItem() throws SQLException, ClassNotFoundException {
        List<Item> list = itemDao.getAll();
        List<ItemDto> dtoList=new ArrayList<>();
        for( Item dto:list){
            dtoList.add(new ItemDto(
                    dto.getItemCode(),
                    dto.getDescription(),
                    dto.getUnitPrice(),
                    dto.getQtyOnHand()
            ));
        }
        return dtoList;
    }

    @Override
    public ItemDto getItem(String id) throws SQLException, ClassNotFoundException {
        Item item = itemDao.getItem(id);
       return new ItemDto(
               item.getItemCode(),
               item.getDescription(),
               item.getUnitPrice(),
               item.getQtyOnHand()
        );
    }

}
