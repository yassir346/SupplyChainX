package supplychainx.springboot.production.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import supplychainx.springboot.production.dto.BillOfMaterialRequest;
import supplychainx.springboot.production.dto.BillOfMaterialResponse;

import java.util.List;


public interface IBillOfMaterialService {
    BillOfMaterialResponse create(BillOfMaterialRequest request);
    BillOfMaterialResponse update(BillOfMaterialRequest request, Long id);
    void delete(Long id);
    BillOfMaterialResponse findBomById(Long id);
    List<BillOfMaterialResponse> findAllBom();
}
