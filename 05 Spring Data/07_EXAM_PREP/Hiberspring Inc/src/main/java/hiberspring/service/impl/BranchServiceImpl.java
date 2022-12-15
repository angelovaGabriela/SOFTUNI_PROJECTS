package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.common.Constants;
import hiberspring.domain.dtos.importJSON.ImportBranchDTO;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Town;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.TownRepository;
import hiberspring.service.BranchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class BranchServiceImpl implements BranchService {
    private final static Path path = Path.of("src","main", "resources", "files", "branches.json");

    private final BranchRepository branchRepository;
    private final TownRepository townRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository,
                             TownRepository townRepository, Gson gson,
                             ModelMapper modelMapper,
                             Validator validator) {
        this.branchRepository = branchRepository;
        this.townRepository = townRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public Boolean branchesAreImported() {
        return branchRepository.count() > 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importBranches(String branchesFileContent) throws IOException {
        String json = this.readBranchesJsonFile();
        ImportBranchDTO[] branches = this.gson.fromJson(json, ImportBranchDTO[].class);

        List<String> result = new ArrayList<>();
        for (ImportBranchDTO branch : branches) {
            Set<ConstraintViolation<ImportBranchDTO>> validationErrors
                    = this.validator.validate(branch);

            if (validationErrors.isEmpty()) {
                Optional<Branch> optionalBranch = this.branchRepository.findByName(branch.getName());
                if (optionalBranch.isPresent()) {
                    result.add(Constants.INCORRECT_DATA_MESSAGE);
                } else {
                    Branch branchToSave = modelMapper.map(branch, Branch.class);

                    Optional<Town> town = this.townRepository.findByName(branch.getTown());
                    branchToSave.setTown(town.get());

                    this.branchRepository.save(branchToSave);

                    result.add(String.format("Successfully added branch %s %s",
                            branchToSave.getTown(), branchToSave.getName()));
                }
            } else {
                result.add(Constants.INCORRECT_DATA_MESSAGE);
            }

        }


        return String.join("\n", result);
    }
}
