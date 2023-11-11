package com.dictionaryapp.service;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageNameEnum;

public interface LanguageService {
    void initLanguages();

    Language findByLanguageNameEnum(LanguageNameEnum language);
}
