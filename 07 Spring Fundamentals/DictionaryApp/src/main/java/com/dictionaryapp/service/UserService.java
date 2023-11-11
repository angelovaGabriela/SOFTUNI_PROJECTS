package com.dictionaryapp.service;

import com.dictionaryapp.model.entity.User;

import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.service.UserServiceModel;
import com.dictionaryapp.model.view.WordsViewModel;

import java.util.List;


public interface UserService {

    void registerUser(UserServiceModel userServiceModel);


    UserServiceModel findUser(String username, String password);

    void login(Long id, String username);

    User findUserByUsername(String username);


    void addWordToUser(Long userId, Word word);


    List<WordsViewModel> getAllGermanWords(Long id);

    List<WordsViewModel> getAllFrenchWords(Long id);

    List<WordsViewModel> getAllSpanishWords(Long id);

    List<WordsViewModel>  getAllItalianWords(Long id);

    Integer getGermanWordsCount(Long id);

    Integer getFrenchWordsCount(Long id);

    Integer getSpanishWordsCount(Long id);

    Integer getItalianWordsCount(Long id);


    Integer getAllWordsCount(Long id);
}