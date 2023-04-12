package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final SongService songService;

    public HomeController(CurrentUser currentUser, SongService songService) {
        this.currentUser = currentUser;
        this.songService = songService;
    }


    @GetMapping("/")
    public String index(Model model) {
        if (currentUser.getId() == null) {
            return "index";
        }

      model.addAttribute("songs", songService.findAllSongs());
//        model.addAttribute("productsSum", songService.sumAllProducts());

        return "home";
    }
}
