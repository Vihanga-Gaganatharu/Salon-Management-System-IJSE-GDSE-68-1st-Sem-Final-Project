package lk.ijse.salon.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProductDto {
    private String product_id;
    private String getDate;
    private String qty;
    private String price;
    private String type;
    private String description;
    private String product_name;

}
