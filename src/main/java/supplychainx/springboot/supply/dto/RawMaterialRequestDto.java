package supplychainx.springboot.supply.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RawMaterialRequestDto {
    private String name;
    private int stock;
    private int stockMin;
    private String unit;
}
