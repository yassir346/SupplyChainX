package supplychainx.springboot.production.dto;

import lombok.Data;

@Data
public class BillOfMaterialResponse {
    private Long billOfMaterialId;
    private String productName;
    private Long productStock;
    private String rawMaterialName;
    private Long rawMaterialStock;
    private int quantityTypePerProduct;


}
