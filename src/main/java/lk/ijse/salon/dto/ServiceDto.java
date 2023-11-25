package lk.ijse.salon.dto;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Data

public class ServiceDto {
    private String service_id;
    private String price;
    private String service_type;
    private String description;
    private String service_name;
}
