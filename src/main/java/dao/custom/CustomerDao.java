package dao.custom;

import dao.CrudDao;
import dao.SuperDao;
import dto.CustomerDto;
import entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao extends CrudDao<Customer> {

}
