package supplychainx.springboot.supply.service.Impl;

import org.springframework.transaction.annotation.Transactional;
import supplychainx.springboot.supply.model.Supplier;
import supplychainx.springboot.supply.service.ISupplierService;

import java.util.List;

@Transactional
public class SupplierServiceImpl implements ISupplierService {
    @Override
    public Supplier save(Supplier supplier){
        if(supplier = null){

        }
        return null;
    }

    @Override
    public Supplier update(Supplier supplier, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Supplier> findAllSuppliers() {
        return List.of();
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
