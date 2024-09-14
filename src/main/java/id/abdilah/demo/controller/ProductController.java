package id.abdilah.demo.controller;

import id.abdilah.demo.entity.Product;
import id.abdilah.demo.repositories.ProductRepository;
import id.abdilah.demo.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<Object> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return ResponseHandler.generateResponseWithData(false, "List of Products", products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable UUID id) {
        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new Exception("Product not found"));

            return ResponseHandler.generateResponseWithData(false, "Product successfully fetched", product, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateResponseOnlyMessage(true, e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{eanCode}")
    public ResponseEntity<Object> getProductByEanCode(@PathVariable String eanCode) {
        try {
            Product product = productRepository.findByEanCode(eanCode);
            if (product == null) {
                throw new Exception("Product not found");
            }

            return ResponseHandler.generateResponseWithData(false, "Product successfully fetched", product, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateResponseOnlyMessage(true, e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return ResponseHandler.generateResponseWithData(false, "Product successfully created", savedProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable UUID id, @RequestBody Product product) {
        try {
            Product updatedProduct = productRepository.findById(id)
                    .map(p -> {
                        p.setName(product.getName());
                        p.setDescription(product.getDescription());
                        p.setPrice(product.getPrice());
                        p.setBasePrice(product.getBasePrice());
                        return productRepository.save(p);
                    })
                    .orElseThrow(() -> new Exception("Product not found"));

            return ResponseHandler.generateResponseWithData(false, "Product successfully updated", updatedProduct, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateResponseOnlyMessage(true, e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable UUID id) {
        try {
            boolean isDeleted = productRepository.findById(id)
                    .map(product -> {
                        productRepository.delete(product);
                        return true;
                    })
                    .orElseThrow(() -> new Exception("Product not found"));

            return ResponseHandler.generateResponseOnlyMessage(false, "Product successfully deleted", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateResponseOnlyMessage(true, e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
