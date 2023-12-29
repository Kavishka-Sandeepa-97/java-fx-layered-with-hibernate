package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    String itemCode;
    String description;
    Double unitPrice;
    int qtyOnHand;
}
