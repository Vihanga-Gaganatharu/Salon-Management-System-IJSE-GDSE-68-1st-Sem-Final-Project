package lk.ijse.salon.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProductDto {
    private String product_id;
    private String product_name;
    private String getDate;
    private String qty;
    private String rank;
    private String price;
    private String type;
    private String description;

}
