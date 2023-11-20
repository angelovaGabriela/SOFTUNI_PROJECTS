package com.resellerapp.controller;

import com.resellerapp.service.HomeService;
import com.resellerapp.service.OfferService;
import com.resellerapp.util.CurrentUser;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final OfferService offerService;

    private final HomeService homeService;

    public HomeController(CurrentUser currentUser, OfferService offerService, HomeService homeService) {
        this.currentUser = currentUser;
        this.offerService = offerService;
        this.homeService = homeService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (currentUser.getId() == null) {
            return "index";
        }


        model.addAttribute("otherOffers", offerService.findAllOtherOffers());
        model.addAttribute("myOffers", offerService.findAllMyOffers());
        model.addAttribute("boughtOffers", offerService.findBoughtOffers());


        return "home";
    }


    @GetMapping("/offer/buy/{id}")
    public String buyOffer(@PathVariable("id") Long offerId) {

        if (currentUser.getId() == null) {
            return "redirect:/login";
        }

        this.homeService.buyOffer(offerId, currentUser.getId());

        return "redirect:/";
    }

}
