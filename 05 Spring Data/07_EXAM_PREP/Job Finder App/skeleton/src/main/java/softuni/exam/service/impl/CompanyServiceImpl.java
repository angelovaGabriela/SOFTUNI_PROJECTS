package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.importXML.ImportCompany;
import softuni.exam.models.dto.importXML.RootImportCompanies;
import softuni.exam.models.entity.Company;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CompanyRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CompanyService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final static Path path = Path.of("src", "main", "resources", "files", "xml", "companies.xml");

    private final CompanyRepository companyRepository;
    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    private final Unmarshaller unmarshaller;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository,
                              CountryRepository countryRepository,
                              ModelMapper modelMapper, Validator validator) throws JAXBException {
        this.companyRepository = companyRepository;
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;

        JAXBContext context = JAXBContext.newInstance(RootImportCompanies.class);
        this.unmarshaller = context.createUnmarshaller();

    }

    @Override
    public boolean areImported() {
        return this.companyRepository.count() > 0;
    }

    @Override
    public String readCompaniesFromFile() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importCompanies() throws IOException, JAXBException {
        RootImportCompanies root = (RootImportCompanies) this.unmarshaller
                .unmarshal(new File(path.toAbsolutePath().toString()));
        List<String> result = new ArrayList<>();

        for (ImportCompany companyDTO : root.getCompanies()) {

            Set<ConstraintViolation<ImportCompany>> validationErrors = this.validator.validate(companyDTO);
            if (validationErrors.isEmpty()) {
                Optional<Company> optionalCompany = this.companyRepository.findByName(companyDTO.getName());
                if (optionalCompany.isEmpty()) {
                    Company company = this.modelMapper.map(companyDTO, Company.class);
                    Optional<Country> country = this.countryRepository.findById(companyDTO.getCountry());

                    company.setCountry(country.get());
                    this.companyRepository.save(company);

                    result.add(String.format("Successfully imported company %s - %d", company.getName(), company.getCountry().getId()));
                }  else {
                    result.add("Invalid company");
                }
            } else {
                result.add("Invalid company");
            }
        }


        return String.join("\n", result);
    }
}
