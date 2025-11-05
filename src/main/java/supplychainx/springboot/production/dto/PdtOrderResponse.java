package supplychainx.springboot.production.dto;

import lombok.Getter;
import lombok.Setter;
import supplychainx.springboot.production.model.BillOfMaterial;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class PdtOrderResponse {
    private Long id;
    private String status;
    private int quantity;
    private LocalDate startDate;
    private LocalDate endDate;
    private double productionEstimatedTime;

    private Long productId;
    private String productName;
    private double productCost;

    private List<BillOfMaterialResponse> billOfMaterials;

    @Getter @Setter
    public static class BillOfMaterialResponse{
        private Long rawMaterialId;
        private String rawMaterialName;
        private int quantityPerUnit;
        private int totalQuantityNeeded;
        private int currentStock;
    }
}
