package supplychainx.springboot.supply.service;

import supplychainx.springboot.supply.model.Supplier;

import java.util.List;

public interface ISupplierService {

    Supplier save(Supplier supplier);
    Supplier update(Supplier supplier, Long id);
    void delete(Long id);
    List<Supplier> findAllSuppliers();
    List<Supplier> findByName(String name);
    Supplier findByContact(String contact);

}
