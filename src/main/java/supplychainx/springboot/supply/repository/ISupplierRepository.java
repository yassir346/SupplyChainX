package supplychainx.springboot.supply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import supplychainx.springboot.supply.model.Supplier;

import java.util.List;

@Repository
public interface ISupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findByName(String name);
    Supplier findByContact(String contact);
}
