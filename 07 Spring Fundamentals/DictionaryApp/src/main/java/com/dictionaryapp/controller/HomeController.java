package com.dictionaryapp.controller;

import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final WordService wordService;
    private final UserService userService;






    public HomeController(CurrentUser currentUser,
                          WordService wordService,
                          UserService userService) {
        this.currentUser = currentUser;
        this.wordService = wordService;
        this.userService = userService;

    }


    @GetMapping("/")
    public String index(Model model) {
        if (currentUser.getId() == null) {
            return "index";
        }

        model.addAttribute("germanWords", userService.getAllGermanWords(currentUser.getId()));
        model.addAttribute("frenchWords", userService.getAllFrenchWords(currentUser.getId()));
        model.addAttribute("spanishWords", userService.getAllSpanishWords(currentUser.getId()));
        model.addAttribute("italianWords", userService.getAllItalianWords(currentUser.getId()));




//        model.addAttribute("userDictionary", userService.getUserDictionary(currentUser.getId()));
//
//        model.addAttribute("germanWordsCount", wordService.germanWordsCount(currentUser.getId()));
//        model.addAttribute("frenchWordsCount", wordService.frenchWordsCount(currentUser.getId()));
//        model.addAttribute("spanishWordsCount", wordService.spanishWordsCount(currentUser.getId()));
//        model.addAttribute("italianWordsCount", wordService.italianWordsCount(currentUser.getId()));

        return "home";
    }

}
