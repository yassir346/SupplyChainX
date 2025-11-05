package supplychainx.springboot.production.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BillOfMaterialRequest {
    private Long productId;
    private Long rawMaterialId;
    private int quantityPerProduct;
}
