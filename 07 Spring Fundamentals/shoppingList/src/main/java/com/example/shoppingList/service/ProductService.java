package com.example.shoppingList.service;

import com.example.shoppingList.model.service.ProductServiceModel;
import com.example.shoppingList.model.view.ProductViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void addProduct(ProductServiceModel productServiceModel);

    void buyProduct(Long id);

    List<ProductViewModel> findAllProducts();

    BigDecimal sumAllProducts();

    void buyAllProducts();
}
