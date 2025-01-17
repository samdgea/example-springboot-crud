package id.abdilah.demo.service;

import id.abdilah.demo.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    List<Product> getAllProducts();

    Optional<Product> getProductById(UUID id);

    Product getProductByEanCode(String eanCode);

    Product createProduct(Product product);

    Optional<Product> updateProduct(UUID id, Product product);

    boolean deleteProduct(UUID id);
}
