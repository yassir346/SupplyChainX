package supplychainx.springboot.delivery.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomerResponseDto {
    private Long id;
    private String name;
    private String address;
    private String city;
}
