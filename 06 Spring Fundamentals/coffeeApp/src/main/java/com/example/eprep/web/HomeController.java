package com.example.eprep.web;

import com.example.eprep.model.view.OrderViewModel;
import com.example.eprep.securityUtils.CurrentUser;
import com.example.eprep.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final OrderService orderService;
    public HomeController(CurrentUser currentUser, OrderService orderService) {
        this.currentUser = currentUser;
        this.orderService = orderService;
    }

    @GetMapping()
    public String index(Model model) {
        if (currentUser.getId() == null) {
            return "index";
        }
        List<OrderViewModel> orders = orderService.findAllOrdersOrderByPriceDesc();
        model.addAttribute("orders", orders);
        model.addAttribute("totalTime",
                orders.stream()
                        .map(orderViewModel ->  orderViewModel.getCategory().getNeededTime())
                        .reduce(Integer::sum)//.reduce((a, b) -> a + b)
                        .orElse(0));
        return "home";
    }
}
