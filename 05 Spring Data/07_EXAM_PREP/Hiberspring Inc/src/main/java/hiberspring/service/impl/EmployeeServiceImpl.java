package hiberspring.service.impl;

import hiberspring.common.Constants;
import hiberspring.domain.dtos.importXML.EmployeeDTO;
import hiberspring.domain.dtos.importXML.RootEmployeesDTO;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Employee;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.repository.EmployeeRepository;
import hiberspring.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class EmployeeServiceImpl implements EmployeeService {

    private static final Path path = Path.of("src", "main", "resources", "files", "employees.xml");
    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    private final EmployeeCardRepository employeeCardRepository;
    private final Validator validator;
    private final ModelMapper modelMapper;

    private final Unmarshaller unmarshaller;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               BranchRepository branchRepository,
                               EmployeeCardRepository employeeCardRepository,
                               Validator validator,
                               ModelMapper modelMapper) throws JAXBException {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.employeeCardRepository = employeeCardRepository;
        this.validator = validator;
        this.modelMapper = modelMapper;

        JAXBContext context = JAXBContext.newInstance(RootEmployeesDTO.class);
        this.unmarshaller = context.createUnmarshaller();

    }


    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importEmployees() throws JAXBException {
        RootEmployeesDTO rootDTO = (RootEmployeesDTO) this.unmarshaller.unmarshal(new File(path.toAbsolutePath().toString()));

        List<String> result = new ArrayList<>();

        for (EmployeeDTO employeeDto : rootDTO.getEmployees()) {
            Optional<EmployeeCard> card = this.employeeCardRepository.findByNumber(employeeDto.getCard());
            Optional<Branch> branch = this.branchRepository.findByName(employeeDto.getBranch());

            if (card.isEmpty() || branch.isEmpty()) {
                result.add(Constants.INCORRECT_DATA_MESSAGE);
            } else if (card.get().getEmployee() != null) {
                result.add(Constants.INCORRECT_DATA_MESSAGE);
            } else {

                Set<ConstraintViolation<EmployeeDTO>> validationErrors = this.validator.validate(employeeDto);
                if (validationErrors.isEmpty()) {
                    Optional<Employee> optionalEmployee =
                            this.employeeRepository.findByFirstNameAndBranchNameAndCardNumber(employeeDto.getFirstName(), employeeDto.getBranch(), employeeDto.getCard());

                    if (optionalEmployee.isPresent()) {
                        result.add(Constants.INCORRECT_DATA_MESSAGE);
                    } else {

                        Employee employee = this.modelMapper.map(employeeDto, Employee.class);

                        employee.setCard(card.get());
                        employee.setBranch(branch.get());

                        this.employeeRepository.save(employee);

                        result.add(String.format("Successfully imported Employee %s %s.", employee.getFirstName(), employee.getLastName()));

                        card.get().setEmployee(employee);
                        this.employeeCardRepository.save(card.get());
                    }
                } else {
                    result.add(Constants.INCORRECT_DATA_MESSAGE);
                }
            }

            }
        return String.join("\n", result);
    }

    @Override
    public String exportProductiveEmployees() {
        return null;
    }
}
