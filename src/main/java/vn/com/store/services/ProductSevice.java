package vn.com.store.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.com.store.repository.ProductRepo;
import vn.com.store.repository.documents.Product;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductSevice implements IProductSevice {
    private ProductRepo productRepo;

    @Override
    public Product saveProduct(Product product) {
        if (productRepo.findByProductId(product.getProductId()) == null) {
            return productRepo.save(product);
        }
        // TODO: throw ra exception
        return null;
    }

    @Override
    public Product update(Product product) {
        Product product1;

        if (productRepo.findByProductId(product.getProductId()) != null) {
            product1 = productRepo.findByProductId(product.getProductId());
            if (product.getName() != null) {
                product1.setName(product.getName());
            }
            if (product.getType() != null) {
                product1.setType(product.getType());
            }
            if (product.getColor() != null) {
                product1.setColor(product.getColor());
            }
            if (product.getSize() != null) {
                product1.setSize(product.getSize());
            }
            if (product.getBrand() != null) {
                product1.setBrand(product.getBrand());
            }
            if (product.getDescription() != null) {
                product1.setDescription(product.getDescription());
            }
            productRepo.save(product1);
            return product1;
        }
        return null;
    }

    @Override
    public List<Product> searchByName(String name) {
        List<Product> listProduct = productRepo.findByNameLikeOrderBy(name, "name");
        return listProduct;
    }

    @Override
    public void delete(String productId) {
        if (productRepo.findByProductId(productId) != null) {
            productRepo.deleteById(productId);
        }else {
            // TODO: throw ra exception
        }
    }

    @Override
    public Iterable<Product> listAllProducts() {
        return productRepo.findAll();
    }
}
