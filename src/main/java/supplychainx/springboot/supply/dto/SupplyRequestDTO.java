package supplychainx.springboot.supply.dto;

import lombok.*;
import supplychainx.springboot.supply.model.RawMaterial;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor

public class SupplyRequestDTO {
    private String name;
    private String contact;
    private double rating;
    private List<RawMaterial> rawMaterialList;

}
