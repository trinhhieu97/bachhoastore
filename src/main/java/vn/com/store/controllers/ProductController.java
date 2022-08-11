package vn.com.store.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.com.store.repository.documents.Product;
import vn.com.store.services.IProductSevice;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
public class ProductController {
    private IProductSevice productSevice;

    @PutMapping
    public @ResponseBody
    Product saveProduct(@RequestBody Product product, Principal principal){
        return productSevice.saveProduct(product);
    }

    @PostMapping
    public @ResponseBody Product update(@RequestBody Product product, Principal principal){
        return productSevice.update(product);
    }

    @DeleteMapping
    public @ResponseBody void delete(@RequestBody String productId, Principal principal){
        productSevice.delete(productId);
    }

    @GetMapping
    public @ResponseBody
    List<Product> listAllProducts(){
        return (List<Product>) productSevice.listAllProducts();
    }
}
