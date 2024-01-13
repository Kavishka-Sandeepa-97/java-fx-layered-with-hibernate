package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Data
@Entity
public class Orders {
    @Id
    private String orderId;
    private String orderDate;


    @ManyToOne
    @JoinColumn(name = "Customer_id",nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "orders")
    private List<OrderDetail> orderDetails=new ArrayList<>();

    public Orders(String orderId, String orderDate) {
        this.orderId = orderId;
        this.orderDate = orderDate;

    }

}
