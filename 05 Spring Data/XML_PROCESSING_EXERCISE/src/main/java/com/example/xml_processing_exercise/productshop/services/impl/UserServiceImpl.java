package com.example.xml_processing_exercise.productshop.services.impl;
import com.example.xml_processing_exercise.productshop.entities.users.ExportSellersDTO;
import com.example.xml_processing_exercise.productshop.entities.users.ExportUserWithSoldProductsDTO;
import com.example.xml_processing_exercise.productshop.entities.users.User;
import com.example.xml_processing_exercise.productshop.repositories.UserRepository;
import com.example.xml_processing_exercise.productshop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    @Transactional
    public ExportSellersDTO findAllWithSoldProducts() {
        List<User> users = this.userRepository.findAllWithSoldProducts();

        List<ExportUserWithSoldProductsDTO> dtos
                = users.stream()
                .map(u -> this.modelMapper.map(u, ExportUserWithSoldProductsDTO.class))
                .collect(Collectors.toList());


        return new ExportSellersDTO(dtos);
    }
}
