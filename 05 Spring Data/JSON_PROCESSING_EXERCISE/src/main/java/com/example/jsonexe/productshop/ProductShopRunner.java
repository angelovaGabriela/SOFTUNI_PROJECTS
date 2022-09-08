package com.example.jsonexe.productshop;

import com.example.jsonexe.productshop.entities.products.ProductWithoutBuyerDTO;
import com.example.jsonexe.productshop.entities.users.UserWithSoldProductsDTO;
import com.example.jsonexe.productshop.services.ProductsService;
import com.example.jsonexe.productshop.services.SeedService;
import com.example.jsonexe.productshop.services.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductShopRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final Gson gson;
    private final ProductsService productsService;
    private final UserService userService;

    @Autowired
    public ProductShopRunner(SeedService seedService, Gson gson, ProductsService productsService, UserService userService) {
        this.seedService = seedService;
        this.userService = userService;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        this.productsService = productsService;
    }

    @Override
    public void run(String... args) throws Exception {
        //this.seedService.seedAll();
        //productsBetweenPriceWithoutBuyer();

        List<UserWithSoldProductsDTO> usersWithSoldProducts = this.userService.getUsersWithSoldProducts();

        String json = this.gson.toJson(usersWithSoldProducts);

        System.out.println(json);

    }

    private void productsBetweenPriceWithoutBuyer() {
        List<ProductWithoutBuyerDTO> productsForSell = this.productsService.getProductsInPriceRangeForSell(500, 1000);

        String json = this.gson.toJson(productsForSell);

        System.out.println(json);
    }
}
