package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.service.WordServiceModel;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.LanguageService;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final UserService userService;
    private final LanguageService languageService;



    public WordServiceImpl(WordRepository wordRepository,
                           ModelMapper modelMapper,
                           CurrentUser currentUser,
                           UserService userService, LanguageService languageService) {
        this.wordRepository = wordRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.userService = userService;
        this.languageService = languageService;

    }

    @Override
    public void addWord(WordServiceModel wordServiceModel) {
        Word word = modelMapper.map(wordServiceModel, Word.class);
        User user = userService.findUserByUsername(currentUser.getUsername());

        word.setLanguage(languageService.findByLanguageNameEnum(wordServiceModel.getLanguage()));
        word.setAddedBy(user);


        wordRepository.save(word);
        this.userService.addWordToUser(user.getId(), word);

    }

    @Transactional
    @Override
    public void removeWord(Long wordId, Long userId) {
       User user =  this.userService.getUserById(userId);
       Word word = this.wordRepository.findById(wordId).orElseThrow();

       user.removeWordFromDictionary(word);
       this.userService.saveUser(user);

       this.wordRepository.deleteById(wordId);
    }




}
