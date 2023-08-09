package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.task.ImportTasksDTO;
import softuni.exam.repository.TasksRepository;
import softuni.exam.service.TasksService;

import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TasksServiceImpl implements TasksService {
    private static final Path TASKS_FILE_PATH = Path.of("src", "main", "resources", "files", "xml", "tasks.xml");;

    private final TasksRepository tasksRepository;
    private final ModelMapper modelMapper;
    private final Unmarshaller unmarshaller;
    private final Validator validator;

    @Autowired
    public TasksServiceImpl(TasksRepository tasksRepository,
                            ModelMapper modelMapper,
                            Validator validator) throws JAXBException {
        this.tasksRepository = tasksRepository;
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
        return Files.readString(TASKS_FILE_PATH);
    }

    @Override
    public String importTasks() throws IOException, JAXBException {
        return null;
    }

    @Override
    public String getCoupeCarTasksOrderByPrice() {
        return null;
    }
}
