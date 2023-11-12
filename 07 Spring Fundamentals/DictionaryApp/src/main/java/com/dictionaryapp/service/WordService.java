package com.dictionaryapp.service;

import com.dictionaryapp.model.service.WordServiceModel;
import com.dictionaryapp.model.view.WordsViewModel;

import java.util.List;

public interface WordService {
    void addWord(WordServiceModel wordServiceModel);


    void removeWord(Long id, Long id1);
}
