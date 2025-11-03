package supplychainx.springboot.supply.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class SupplyOrderResponseDTO {
    private Long id;
    private LocalDate date;
    private String status;
    private SupplierResponse supplier;
    private List<RawMaterialResponse> rawMaterialResponseList;

    @Getter @Setter
    public static class SupplierResponse{
        private Long id;
        private String name;
        private String contact;
        private Double rating;
    }

    @Getter @Setter
    public static class RawMaterialResponse{
        private Long id;
        private String name;
        private int quantity;
    }
}
