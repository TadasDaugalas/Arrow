package lt.codeacademy.service;

import lt.codeacademy.entity.Product;
import lt.codeacademy.exception.ProductNotFoundException;
import lt.codeacademy.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public UUID createProduct(Product product) {
        return productRepository.save(product).getId();
    }

    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    public Product getProduct(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(UUID uuid) {
        productRepository.deleteById(uuid);
    }

    public List<Product> findProducts(String query) {
        return productRepository.findLike(query);
    }
}