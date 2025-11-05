package supplychainx.springboot.production.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PdtOrderRequest {
    private Long productId;
    private int quantity;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

}
