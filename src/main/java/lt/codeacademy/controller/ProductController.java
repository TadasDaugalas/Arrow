package lt.codeacademy.controller;

import static lt.codeacademy.ApiPath.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import lt.codeacademy.entity.Product;
import lt.codeacademy.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(PRODUCTS)
@Api(tags = "Product Controller")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "Gauti visus produktus", tags = "getProducts", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Duomenys sekmingai uzkrauti"),
            @ApiResponse(code =403, message = "Vartotojas neturi teisiu"),
            @ApiResponse(code = 404, message = "Nepavyko rasti produktu")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProducts() {
        return productService.getProduct();
    }

    @GetMapping(value = PRODUCT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProduct(@PathVariable(ID_VARIABLE) UUID id) {
        return productService.getProduct(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
    }

    @DeleteMapping(PRODUCT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable(ID_VARIABLE) UUID uuid) {
        productService.deleteProduct(uuid);
    }

    @GetMapping(SEARCH)
    public List<Product> searchProducts(@RequestParam String query) {
        return productService.findProducts(query);
    }

}
