package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.task.ImportTasksDTO;
import softuni.exam.models.dto.task.TaskImportDTO;
import softuni.exam.models.entity.*;
import softuni.exam.repository.CarsRepository;
import softuni.exam.repository.MechanicsRepository;
import softuni.exam.repository.PartsRepository;
import softuni.exam.repository.TasksRepository;
import softuni.exam.service.TasksService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TasksServiceImpl implements TasksService {
    private static final String TASKS_FILE_PATH = "src/main/resources/files/xml/tasks.xml";

    private final TasksRepository tasksRepository;
    private final MechanicsRepository mechanicsRepository;
    private final CarsRepository carsRepository;
    private final PartsRepository partsRepository;
    private final ModelMapper modelMapper;
    private final Unmarshaller unmarshaller;
    private final Validator validator;

    @Autowired
    public TasksServiceImpl(TasksRepository tasksRepository,
                            MechanicsRepository mechanicsRepository,
                            CarsRepository carsRepository,
                            PartsRepository partsRepository, ModelMapper modelMapper,
                            Validator validator) throws JAXBException {
        this.tasksRepository = tasksRepository;
        this.mechanicsRepository = mechanicsRepository;
        this.carsRepository = carsRepository;
        this.partsRepository = partsRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;


        JAXBContext context = JAXBContext.newInstance(ImportTasksDTO.class);
        this.unmarshaller = context.createUnmarshaller();

    }

    @Override
    public boolean areImported() {
        return this.tasksRepository.count() > 0;
    }

    @Override
    public String readTasksFileContent() throws IOException {
        return Files.readString(Path.of(TASKS_FILE_PATH));
    }

    @Override
    public String importTasks() throws IOException, JAXBException {

        ImportTasksDTO importTasksDTO = (ImportTasksDTO) this.unmarshaller.unmarshal(new FileReader(TASKS_FILE_PATH));

        List<String> result = new ArrayList<>();

        for (TaskImportDTO taskImportDTO : importTasksDTO.getTasks())  {
            Set<ConstraintViolation<TaskImportDTO>> validationErrors = this.validator.validate(taskImportDTO);

            if (validationErrors.isEmpty()) {
                Optional<Task> optionalTask = this.tasksRepository.findTaskByMechanicFirstName(taskImportDTO.getMechanic().getFirstName());
                if (optionalTask.isPresent()) {
                    result.add("Invalid task");
                } else {
                    Task task = this.modelMapper.map(taskImportDTO, Task.class);
                    Optional<Car> car = this.carsRepository.findById(taskImportDTO.getCars().getId());
                    Optional<Part> part = this.partsRepository.findById(taskImportDTO.getParts().getId());
                    Optional<Mechanic> mechanic = this.mechanicsRepository.findByFirstName(taskImportDTO.getMechanic().getFirstName());


                    if (car.isEmpty() || part.isEmpty() || mechanic.isEmpty()) {
                        result.add("Invalid task");
                        continue;
                    }

                    task.setCars(car.get());
                    task.setParts(part.get());
                    task.setMechanic(mechanic.get());

                    this.tasksRepository.save(task);

                    String message = String.format("Successfully imported task %.2f", task.getPrice() );
                    result.add(message);

                }

            } else {
                result.add("Invalid task");
            }
        }
        return String.join("\n", result);
    }

    @Override
    public String getCoupeCarTasksOrderByPrice() {


        List<Task> tasks = this.tasksRepository.findAllByCarsCarTypeOrderByPriceDesc(CarEnum.coupe);
        return tasks.stream()
                .map(Task::toString)
                .collect(Collectors.joining("\n"));
    }
}
