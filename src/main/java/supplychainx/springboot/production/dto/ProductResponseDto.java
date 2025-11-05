package supplychainx.springboot.production.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    private String name;
    private int productionTime;
    private double cost;
    private int stock;
    private List<BillOfMaterialResponse> billOfMaterialResponseList;

    @Getter @Setter
    public static class BillOfMaterialResponse{
        private Long id;
        private Long rawMaterialId;
        private int quantity;
        private String rawMaterialName;
    }
}
