package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.entity.LanguageNameEnum;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.service.UserServiceModel;
import com.dictionaryapp.model.view.WordsViewModel;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);
        this.userRepository.save(user);
    }

    @Override
    public UserServiceModel findUser(String username, String password) {

        return userRepository.findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class)).orElse(null);

    }

    @Override
    public void login(Long id, String username) {
        currentUser.setId(id);
        currentUser.setUsername(username);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }



    @Override
    public void addWordToUser(Long userId, Word word) {


        User user = this.userRepository.findById(userId).orElse(null);
        assert user != null;
        if (user.getAddedWords().stream().noneMatch(w -> w.getId().equals(word.getId()))) {
                user.addWordToDictionary(word);
                this.userRepository.save(user);
            }


    }

    @Override
    public List<WordsViewModel> getAllGermanWords(Long id) {
        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        LanguageNameEnum language = LanguageNameEnum.valueOf("GERMAN");

        return getCollection(user,language);
    }

    private List<WordsViewModel> getCollection(User user, LanguageNameEnum language) {
        return user.getAddedWords()
                .stream().map(word -> modelMapper.map(word, WordsViewModel.class))
                .filter(w -> w.getLanguage().getName().equals(language))
                .collect(Collectors.toList());
    }

    @Override
    public List<WordsViewModel> getAllFrenchWords(Long id) {
        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        LanguageNameEnum language = LanguageNameEnum.valueOf("FRENCH");

        return getCollection(user, language);

    }


}
