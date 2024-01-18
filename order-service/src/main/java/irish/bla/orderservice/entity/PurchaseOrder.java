package irish.bla.orderservice.entity;

import irish.bla.orderservice.dto.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class PurchaseOrder {

    @Id
    @GeneratedValue
    private Integer id;

    private String productId;
    private Integer userId;
    private Integer amount;
    private OrderStatus status;
}
