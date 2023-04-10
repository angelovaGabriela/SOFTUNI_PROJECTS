package com.example.shoppingList.web;

import com.example.shoppingList.securityUtils.CurrentUser;
import com.example.shoppingList.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final ProductService productService;

    public HomeController(CurrentUser currentUser, ProductService productService) {
        this.currentUser = currentUser;
        this.productService = productService;
    }


    @GetMapping("/")
    public String index(Model model) {
        if (currentUser.getId() == null) {
            return "index";
        }

        model.addAttribute("products", productService.findAllProducts());
        model.addAttribute("productsSum", productService.sumAllProducts());

        return "home";
    }
}
