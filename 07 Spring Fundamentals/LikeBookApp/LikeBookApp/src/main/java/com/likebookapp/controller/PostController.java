package com.likebookapp.controller;

import com.likebookapp.model.binding.PostAddBindingModel;
import com.likebookapp.model.service.PostServiceModel;
import com.likebookapp.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PostController {
    private final PostService postService;
    private final ModelMapper modelMapper;

    public PostController(PostService postService, ModelMapper modelMapper) {
        this.postService = postService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/post/add")
    public String postAdd() {
        return "post-add";
    }

    @PostMapping("/post/add")
    public String postAddConfirm(@Valid PostAddBindingModel postAddBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("postAddBindingModel", postAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.postAddBindingModel", bindingResult);

            return "redirect:/post/add";
        }
        postService.addPost(modelMapper.map(postAddBindingModel, PostServiceModel.class));


        return "redirect:/";
    }

    @ModelAttribute
    public PostAddBindingModel postAddBindingModel() {
        return new PostAddBindingModel();
    }
}
