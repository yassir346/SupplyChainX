package supplychainx.springboot.delivery.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestDto {
    private String name;
    private String address;
    private String city;
}
