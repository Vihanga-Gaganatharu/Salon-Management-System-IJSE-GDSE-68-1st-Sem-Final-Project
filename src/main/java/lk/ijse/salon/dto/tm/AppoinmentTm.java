package lk.ijse.salon.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AppoinmentTm {
    private String b_id;
    private String emp_id;
    private String c_id;
    private String date;
    private String time;
    private String service;
}
