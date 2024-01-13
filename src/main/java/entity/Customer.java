package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;


@NoArgsConstructor
@Data
@Entity
public class Customer {

    @Id
    String custId;
    String custName;
    String custAddress;
    double salary;

    @OneToMany(mappedBy = "customer")
    private List<Orders> orderList;

    public Customer(String custId, String custName, String custAddress, double salary) {
        this.custId = custId;
        this.custName = custName;
        this.custAddress = custAddress;
        this.salary = salary;
    }

}
