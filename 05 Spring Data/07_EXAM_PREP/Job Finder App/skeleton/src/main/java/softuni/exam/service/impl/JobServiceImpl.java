package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.importXML.ImportJobDTO;
import softuni.exam.models.dto.importXML.RootImportJobDTO;
import softuni.exam.models.entity.Company;
import softuni.exam.models.entity.Job;
import softuni.exam.repository.CompanyRepository;
import softuni.exam.repository.JobRepository;
import softuni.exam.service.JobService;

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
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    private final static Path path = Path.of("src", "main", "resources", "files", "xml", "jobs.xml");

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Unmarshaller unmarshaller;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository,
                          CompanyRepository companyRepository,
                          ModelMapper modelMapper, Validator validator) throws JAXBException {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;

        JAXBContext context = JAXBContext.newInstance(RootImportJobDTO.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.jobRepository.count() > 0;
    }

    @Override
    public String readJobsFileContent() throws IOException {
        return Files.readString(path) ;
    }

    @Override
    public String importJobs() throws IOException, JAXBException {

        RootImportJobDTO root = (RootImportJobDTO) this.unmarshaller
                .unmarshal(new File(path.toAbsolutePath().toString()));

        List<String> result = new ArrayList<>();

        for (ImportJobDTO jobDTO : root.getJobs()) {
            Set<ConstraintViolation<ImportJobDTO>> validationErrors =
                    this.validator.validate(jobDTO);

            if (validationErrors.isEmpty()) {
                Optional<Job> jobByCompanyId = this.jobRepository.findJobByCompanyId(jobDTO.getCompany());
                if (jobByCompanyId.isPresent()) {
                    result.add("Invalid job");
                } else {
                    Job job = this.modelMapper.map(jobDTO, Job.class);
                    Optional<Company> company = this.companyRepository.findById(jobDTO.getCompany());

                    job.setCompany(company.get());
                    this.jobRepository.save(job);

                    result.add(String.format("Successfully imported %s", job.getTitle()));
                }
            } else {
                result.add("Invalid job");
            }
        }
        return String.join("\n", result);
    }

    @Override
    public String getBestJobs() {
       double salary = 5000.00;
       double hoursAWeek = 30.00;

        List<Job> bestJobs = this.jobRepository
                .findJobBySalaryGreaterThanEqualAndHoursAWeekLessThanEqualOrderBySalaryDesc(salary, hoursAWeek);


        return bestJobs.stream().map(Job::toString).collect(Collectors.joining("\n"));
    }
}
