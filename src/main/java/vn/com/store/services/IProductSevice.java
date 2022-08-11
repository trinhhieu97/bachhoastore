package vn.com.store.services;

import vn.com.store.repository.documents.Product;

import java.util.List;

public interface IProductSevice {
    Product saveProduct(Product product);
    Product update(Product product);
    List<Product> searchByName(String name);
    void delete(String idProduct);
    Iterable<Product> listAllProducts();
}
