package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.service.HomeService;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final SongService songService;
    private final HomeService homeService;

    public HomeController(CurrentUser currentUser, SongService songService, HomeService homeService) {
        this.currentUser = currentUser;
        this.songService = songService;
        this.homeService = homeService;
    }


    @GetMapping("/")
    public String index(Model model) {
        if (currentUser.getId() == null) {
            return "index";
        }

      model.addAttribute("songs", songService.findAllSongs());
      model.addAttribute("playlist", this.songService.getPlaylist(currentUser.getId()));
      model.addAttribute("totalDuration", this.songService.getTotalDuration(currentUser.getId()));


        return "home";
    }

    @GetMapping("/songs/add/{id}")
    public String addSongToPlaylist(@PathVariable("id") Long id) {

        if (currentUser.getId() == null) {
            return "redirect:/login";
        }

        this.homeService.addSong(id, this.currentUser.getId());
        return "redirect:/";
    }

    @GetMapping("/home/remove-all-from-playlist")
    public String deleteAllFromPlaylist() {
        if (currentUser.getId() == null) {
            return "redirect:/login";
        }

        this.homeService.deleteAll(this.currentUser.getId());
        return "redirect:/";
    }
}
