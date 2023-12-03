package com.likebookapp.controller;

import com.likebookapp.service.HomeService;
import com.likebookapp.service.PostService;
import com.likebookapp.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final PostService postService;

    private final HomeService homeService;

    public HomeController(CurrentUser currentUser, PostService postService, HomeService homeService) {
        this.currentUser = currentUser;
        this.postService = postService;
        this.homeService = homeService;
    }

    @GetMapping("/")
        public String index(Model model) {
            if (currentUser.getId() == null) {
                return "index";
            }

        model.addAttribute("myPosts", postService.findAllMyPosts());
        model.addAttribute("otherPosts", postService.findAllOtherPosts());
        return "home";
        }
        ///post/remove/{id}(id = ${p.getId()})
        @GetMapping("/post/remove/{id}")
        public String removeMyPost(@PathVariable("id") Long postId) {
            if (currentUser.getId() == null) {
                return "redirect:/login";
            }

            this.homeService.removePost(postId, currentUser.getId());

            return "redirect:/";
        }

        @GetMapping("/post/like/{id}")
        public String likePost(@PathVariable ("id") Long postId) {
            if (currentUser.getId() == null) {
                return "redirect:/login";
            }

            this.homeService.likePost(postId, currentUser.getId());

            return "redirect:/";
        }
}
