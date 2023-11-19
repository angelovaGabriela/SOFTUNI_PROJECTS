package com.resellerapp.controller;

import com.resellerapp.service.OfferService;
import com.resellerapp.util.CurrentUser;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final OfferService offerService;

    public HomeController(CurrentUser currentUser, OfferService offerService) {
        this.currentUser = currentUser;
        this.offerService = offerService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (currentUser.getId() == null) {
            return "index";
        }


        model.addAttribute("otherOffers", offerService.findAllOtherOffers());

        return "home";
    }

}
