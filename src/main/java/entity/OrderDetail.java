package entity;

import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity

public class OrderDetail {
    @EmbeddedId
   private OrderDetailsKey id;

    @ManyToOne
    @MapsId("itemCode")
    @JoinColumn(name = "item_code")
    private Item item;

    @ManyToOne
    @MapsId("orderID")
    @JoinColumn(name = "order_id")
    private Orders orders;

    private int orderQty;
    private  double unitePrice;
}
