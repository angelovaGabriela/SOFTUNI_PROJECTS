package com.dictionaryapp.controller;

import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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


        model.addAttribute("germanWordsCount", userService.getGermanWordsCount(currentUser.getId()));
        model.addAttribute("frenchWordsCount", userService.getFrenchWordsCount(currentUser.getId()));
        model.addAttribute("spanishWordsCount", userService.getSpanishWordsCount(currentUser.getId()));
        model.addAttribute("italianWordsCount", userService.getItalianWordsCount(currentUser.getId()));

        model.addAttribute("wordsCount", userService.getAllWordsCount(currentUser.getId()));


        return "home";
    }


    @GetMapping ("/word/remove/{id}")
    public String removeWord(@PathVariable("id") Long id) {
        if (currentUser.getId() == null) {
            return "redirect:/login";
        }

        this.wordService.removeWord(id, currentUser.getId());

        return "redirect:/";
    }

    @GetMapping("/home/remove-all")
    public String removeAll() {
        if (currentUser.getId() == null) {
            return "redirect:login";
        }

        this.userService.removeAll(currentUser.getId());

        return "redirect:/";

    }

}
