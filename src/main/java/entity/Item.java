package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Item {
    @Id
    private String itemCode;
    private String description;
    private Double unitPrice;
    private int qtyOnHand;


    @OneToMany(mappedBy = "item")
    private List<OrderDetail> orderDetails=new ArrayList<>();

    public Item(String itemCode, String description, Double unitPrice, int qtyOnHand) {
        this.itemCode = itemCode;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
    }
}
