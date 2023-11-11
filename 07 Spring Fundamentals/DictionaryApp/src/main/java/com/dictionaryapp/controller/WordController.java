package com.dictionaryapp.controller;

import com.dictionaryapp.model.binding.WordAddBindingModel;
import com.dictionaryapp.model.service.WordServiceModel;
import com.dictionaryapp.service.WordService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WordController {

    private final WordService wordService;
    private final ModelMapper modelMapper;

    public WordController(WordService wordService, ModelMapper modelMapper) {
        this.wordService = wordService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/word/add")
    public String wordAdd() {
        return "word-add";
    }

    @PostMapping("/word/add")
    public String confirmWordAdd(@Valid WordAddBindingModel wordAddBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {


            if (bindingResult.hasErrors()) {

                redirectAttributes.addFlashAttribute("wordAddBindingModel", wordAddBindingModel);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.wordAddBindingModel", bindingResult);

                return "redirect:/word/add";
            }


            wordService.addWord(modelMapper.map(wordAddBindingModel, WordServiceModel.class));


            return "redirect:/";


    }

    @ModelAttribute
    public WordAddBindingModel wordAddBindingModel() {
        return new WordAddBindingModel();
    }

}

