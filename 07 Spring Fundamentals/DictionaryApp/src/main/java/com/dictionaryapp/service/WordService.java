package com.dictionaryapp.service;


import com.dictionaryapp.model.service.WordServiceModel;


public interface WordService {
    void addWord(WordServiceModel wordServiceModel);


    void removeWord(Long wordId, Long userId);

}
