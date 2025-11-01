package supplychainx.springboot.supply.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class SupplyOrderRequestDTO {
    private LocalDate orderDate;
    private Long supplierId;
    private String status;
    private List<RawMaterialQuantity> rawMaterials;

    @Getter @Setter
    public static class RawMaterialQuantity{
        private Long rawMaterialId;
        private int quantity;
    }
}
