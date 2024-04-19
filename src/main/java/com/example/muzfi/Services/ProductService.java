package com.example.muzfi.Services;

import com.example.muzfi.Enums.Formula;
import com.example.muzfi.Enums.ProductCondition;
import com.example.muzfi.Model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Iterable<Product> getAllProducts();

    Optional<Product> getProduct(String id);

    List<Product> getProductsByCondition(ProductCondition condition);

    List<Product> getProductsByBrand(String brandId);

    List<Product> getProductsByUser(String userId);

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByFormula(Formula formula);

    List<Product> getProductsBySize(Double size);

    List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice);

    List<String> getTopBrands();

    Product saveProduct(Product product);

    List<Product> getLatestProducts(int limit);


    void deleteProduct(String id);
}