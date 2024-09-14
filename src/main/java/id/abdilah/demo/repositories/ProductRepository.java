package id.abdilah.demo.repositories;

import id.abdilah.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product findByEanCode(String eanCode);
}
