package supplychainx.springboot.production.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    private String name;
    private int productionTime;
    private double cost;
    private int stock;

    @Getter @Setter
    public static class BillOfMaterialResponse{
        private Long id;
        private Long rawMaterialId;
        private int quantity;
        private String rawMaterialName;
    }
}
