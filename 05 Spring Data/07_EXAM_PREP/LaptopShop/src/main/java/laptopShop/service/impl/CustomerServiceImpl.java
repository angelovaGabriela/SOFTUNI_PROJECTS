package laptopShop.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import laptopShop.model.Customer;
import laptopShop.model.Town;
import laptopShop.model.dtos.importCustomers.CustomersImportDTO;
import laptopShop.repository.CustomerRepository;
import laptopShop.repository.TownRepository;
import laptopShop.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final static Path path = Path.of("src","main", "resources", "files", "json", "customers.json");
    private final CustomerRepository customerRepository;
    private final Validator validator;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, TownRepository townRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.townRepository = townRepository;

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        this.gson = new GsonBuilder().create();
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.customerRepository.count() > 0 ;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importCustomers() throws IOException {
        List<String> result = new ArrayList<>();
        String json = readCustomersFileContent();

        CustomersImportDTO[] importDTOS = this.gson.fromJson(json, CustomersImportDTO[].class);

        for (CustomersImportDTO customerImport : importDTOS) {

            Set<ConstraintViolation<CustomersImportDTO>> errors =
                    this.validator.validate(customerImport);

            if (errors.isEmpty()) {
                Optional<Customer> optionalCustomer = this.customerRepository.findByEmail(customerImport.getEmail());

                if (optionalCustomer.isEmpty()) {
                    Customer customer = this.modelMapper.map(customerImport, Customer.class);

                    Optional<Town> town = this.townRepository.findByName(customerImport.getTown().getName());

                    customer.setTown(town.get());

                    String message = "Successfully imported Customer " + customer.getFirstName() + customer.getLastName() + " - " + customer.getEmail();
                    result.add(message);

                    this.customerRepository.save(customer);


                } else {
                    result.add("Invalid Customer");
                }
            } else {
                result.add("Invalid Customer");
            }
        }
        return String.join("\n", result);
    }
}
