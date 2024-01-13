package irish.bla.productservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private String id;
    private String description;
    private Integer price;

}
