package lk.ijse.salon.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BookingTm {

    private String cusId;
    private String sId;
    private String name;
    private String type;
    private String price;
}
