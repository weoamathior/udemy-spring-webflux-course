package irish.bla.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseOrderResponseDto {
    private Integer orderId;
    private Integer userId;
    private String productId;
    private Integer amount;
    private OrderStatus status;
}
