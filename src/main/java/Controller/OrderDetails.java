package Controller;

import boService.BoFactory;
import boService.Custom.OrderBo;
import dao.utill.BoType;
import dto.OrderDetailsDto;
import dto.OrderDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import dao.custom.OrderDetailsDao;
import dao.custom.OrderDao;
import dao.custom.impl.OrderDetailDaoImpl;
import dao.custom.impl.OrderDaoImpl;

import java.io.IOException;
import java.sql.SQLException;

public class OrderDetails {

    public TableColumn colOrderID;
    public TableColumn colOrderId;
    @FXML
    private TableView<OrderDto> tblAllOrders;




    @FXML
    private TableColumn colDate;

    @FXML
    private TableColumn colCustomerId;

    @FXML
    private TableView<OrderDetailsDto> tblAllOrderDetails;

    @FXML
    private TableColumn colItemID;

    @FXML
    private TableColumn colQTY;

    @FXML
    private TableColumn colUnitePrice;

    OrderDetailsDao orderDetailsDao =new OrderDetailDaoImpl();
    private OrderBo orderBo= BoFactory.getInstance().getBo(BoType.ORDER);

    public void initialize() {


        loadOrdersTable();

        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        colItemID.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("OrderQTY"));
        colUnitePrice.setCellValueFactory(new PropertyValueFactory<>("unitePrice"));



        tblAllOrders.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData((OrderDto) newValue);//we can use genarics above tblCustomer <CustomerTM>
        });
    }


    private void loadOrdersTable() {
        ObservableList<OrderDto> tmList = FXCollections.observableArrayList();
        try {

            for (OrderDto orderDto : orderBo.allOrder()) {
                tmList.add(orderDto);
            }
            tblAllOrders.setItems(tmList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setData(OrderDto newValue) {

        ObservableList<OrderDetailsDto> listOrderDetails = null;
        try {
            listOrderDetails = orderDetailsDao.getOrderDetail((newValue.getOrderId()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        tblAllOrderDetails.setItems(listOrderDetails);



    }

    public void backButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage)tblAllOrders.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml"))));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
