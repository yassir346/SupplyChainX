package supplychainx.springboot.production.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class ProductRequestDto {
    private String name;
    private int productionTime;
    private double cost;
    private int stock;
    private List<BillOfMaterialRequest> billOfMaterialRequestList;

    @Getter @Setter
    public static class BillOfMaterialRequest{
        private Long rawMaterialId;
        private int quantity;
    }
}
