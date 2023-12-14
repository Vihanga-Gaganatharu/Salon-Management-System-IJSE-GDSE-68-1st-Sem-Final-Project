package lk.ijse.salon.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class productTm {
    private String productId;
    private String name;
    private String date;
    private String qty;
    private String price;
    private String item_type;
    private String dis;
}
