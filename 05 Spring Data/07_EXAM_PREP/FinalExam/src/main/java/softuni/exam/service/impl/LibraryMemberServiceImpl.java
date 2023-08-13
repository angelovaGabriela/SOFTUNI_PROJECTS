package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportMembersDTO;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.LibraryMemberService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LibraryMemberServiceImpl implements LibraryMemberService {
    private static final String MEMBERS_FILE_PATH = "src/main/resources/files/json/library-members.json";

    private final LibraryMemberRepository libraryMemberRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final Validator validator;

    public LibraryMemberServiceImpl(LibraryMemberRepository libraryMemberRepository,
                                    ModelMapper modelMapper,
                                    Gson gson,
                                    Validator validator) {
        this.libraryMemberRepository = libraryMemberRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.libraryMemberRepository.count() > 0;
    }

    @Override
    public String readLibraryMembersFileContent() throws IOException {
        return Files.readString(Path.of(MEMBERS_FILE_PATH));
    }

    @Override
    public String importLibraryMembers() throws IOException {
        String json = this.readLibraryMembersFileContent();
        ImportMembersDTO[] importMembersDTOS = this.gson.fromJson(json, ImportMembersDTO[].class);

        List<String> result = new ArrayList<>();

        for (ImportMembersDTO importDTO : importMembersDTOS) {
            Set<ConstraintViolation<ImportMembersDTO>> validationErrors =
                    this.validator.validate(importDTO);

            if (validationErrors.isEmpty()) {
                Optional<LibraryMember> optionalMember = this.libraryMemberRepository.findByPhoneNumber(importDTO.getPhoneNumber());

                if (optionalMember.isPresent()) {
                    result.add("Invalid library member");
                } else {
                    LibraryMember member = this.modelMapper.map(importDTO, LibraryMember.class);
                    this.libraryMemberRepository.save(member);

                    String message = String.format("Successfully imported library member %s - %s", member.getFirstName(), member.getLastName());
                    result.add(message);
                }
            } else {
                result.add("Invalid library member");
            }
        }

        return String.join("\n", result);
    }
}
