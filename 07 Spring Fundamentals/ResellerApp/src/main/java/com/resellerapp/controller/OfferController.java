package com.resellerapp.controller;

import com.resellerapp.model.binding.OfferAddBindingModel;
import com.resellerapp.model.service.OfferServiceModel;
import com.resellerapp.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class OfferController {

    private final OfferService offerService;
    private final ModelMapper modelMapper;

    public OfferController(OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/offer/add")
    public String addOffer() {
        return "offer-add";
    }

    @PostMapping("/offer/add")
    public String confirmOfferAdd(@Valid OfferAddBindingModel offerAddBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerAddBindingModel", offerAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerAddBindingModel", bindingResult);

            return "redirect:/offer/add";
        }

        this.offerService.addOffer(modelMapper.map(offerAddBindingModel, OfferServiceModel.class));


        return "redirect:/";
    }


    @ModelAttribute
    public OfferAddBindingModel offerAddBindingModel() {
        return new OfferAddBindingModel();
    }
}
