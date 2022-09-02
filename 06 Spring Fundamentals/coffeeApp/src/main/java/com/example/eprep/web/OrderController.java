package com.example.eprep.web;

import com.example.eprep.model.binding.OrderAddBindingModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/add")
    public String add() {
        return "order-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid OrderAddBindingModel orderAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("orderAddBindingModel", orderAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderAddBindingModel", bindingResult);

            return "redirect:add";
        }

        //TODO: add to DB
        return "redirect:/";
    }

    @ModelAttribute
    public OrderAddBindingModel orderAddBindingModel() {
        return new OrderAddBindingModel();
    }
}
