package irish.bla.orderservice.dto;

import lombok.Data;

@Data
public class TransactionRequestDto {
    private Integer userId;
    private Integer amount;

}
