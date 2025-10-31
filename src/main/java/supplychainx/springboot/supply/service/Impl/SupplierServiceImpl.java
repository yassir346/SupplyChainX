package supplychainx.springboot.supply.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import supplychainx.springboot.supply.model.Supplier;
import supplychainx.springboot.supply.repository.ISupplierRepository;
import supplychainx.springboot.supply.service.ISupplierService;

import java.util.List;

@Transactional
@Service
public class SupplierServiceImpl implements ISupplierService {
    @Autowired
    ISupplierRepository supplierRepository;

    @Override
    public Supplier save(Supplier supplier){
        if(supplier == null){
            System.out.println("invalid Supplier Object");
        }
        supplierRepository.save(supplier);
        return supplier;
    }

    @Override
    public Supplier update(Supplier supplier, Long id) {
        if(supplier == null){
            System.out.println("invalid Supplier Object");
        }
        Supplier foundSupplier = supplierRepository.findById(id).orElse(null);
        foundSupplier.setName(supplier.getName());
        foundSupplier.setContact(supplier.getContact());
        foundSupplier.setRating(supplier.getRating());
        supplierRepository.save(foundSupplier);
        return foundSupplier;
    }

    @Override
    public void delete(Long id) {
        Supplier foundSupplier = supplierRepository.findById(id).orElse(null);
        supplierRepository.delete(foundSupplier);
    }

    @Override
    public Supplier findById(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElse(null);
        return supplier;
    }

    @Override
    public List<Supplier> findAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers;
    }

    @Override
    public List<Supplier> findByName(String name) {
        return List.of();
    }

    @Override
    public Supplier findByContact(String contact) {
        return null;
    }
}
