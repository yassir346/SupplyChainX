package supplychainx.springboot.production.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import supplychainx.springboot.production.dto.ProductRequestDto;
import supplychainx.springboot.production.dto.ProductResponseDto;
import supplychainx.springboot.production.model.BillOfMaterial;
import supplychainx.springboot.production.model.Product;
import supplychainx.springboot.production.repository.ProductRepository;
import supplychainx.springboot.production.service.IProductService;
import supplychainx.springboot.supply.model.RawMaterial;
import supplychainx.springboot.supply.repository.IRawMaterialRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {
    private  final ProductRepository productRepository;
    private final IRawMaterialRepository rawMaterialRepository;

    @Override
    public ProductResponseDto create(ProductRequestDto productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setProductionTime(productRequest.getProductionTime());
        product.setCost(productRequest.getCost());
        product.setStock(productRequest.getStock());

        List<BillOfMaterial> billOfMaterials = productRequest.getBillOfMaterialRequestList().stream()
                .map(billOfMaterialRequest -> {
                    RawMaterial rawMaterial = rawMaterialRepository.findById(billOfMaterialRequest.getRawMaterialId()).orElseThrow();
                    BillOfMaterial bom = new BillOfMaterial();
                    bom.setRawMaterial(rawMaterial);
                    bom.setProduct(product);
                    bom.setQuantity(billOfMaterialRequest.getQuantity());
                    return bom;
                })
                .collect(Collectors.toList());

        product.setBillOfMaterials(billOfMaterials);
        Product savedProduct = productRepository.save(product);
        return toResponse(savedProduct);
    }

    @Override
    public Product update(ProductRequestDto productRequest, Long id) {
        Product foundProduct = productRepository.findById(id).orElseThrow();
        foundProduct.setName(productRequest.getName());
        foundProduct.setProductionTime(productRequest.getProductionTime());
        foundProduct.setCost(productRequest.getCost());
        foundProduct.setStock(productRequest.getStock());

        return productRepository.save(foundProduct);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponseDto toResponse(Product product) {
        ProductResponseDto productResponse = new ProductResponseDto();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setProductionTime(product.getProductionTime());
        productResponse.setCost(product.getCost());
        productResponse.setStock(product.getStock());
        List<ProductResponseDto.BillOfMaterialResponse> billOfMaterialResponseList = product.getBillOfMaterials()
                .stream().map(billOfMaterial -> {
                    ProductResponseDto.BillOfMaterialResponse bom = new ProductResponseDto.BillOfMaterialResponse();
                    bom.setId(billOfMaterial.getId());
                    bom.setQuantity(billOfMaterial.getQuantity());
                    bom.setRawMaterialId(billOfMaterial.getId());
                    bom.setRawMaterialName(billOfMaterial.getRawMaterial().getName());
                    return bom;
                })
                .toList();
        productResponse.setBillOfMaterialResponseList(billOfMaterialResponseList);
        return productResponse;
    }
}
