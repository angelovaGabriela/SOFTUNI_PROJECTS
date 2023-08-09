package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.task.ImportTasksDTO;
import softuni.exam.repository.TaskRepository;
import softuni.exam.service.TaskService;

import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TaskServiceImpl implements TaskService {
    private static final Path TASKS_FILE_PATH = Path.of("src", "main", "resources", "files", "xml", "tasks.xml");;

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private final Unmarshaller unmarshaller;
    private final Validator validator;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository,
                           ModelMapper modelMapper,
                           Validator validator) throws JAXBException {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;


        JAXBContext context = JAXBContext.newInstance(ImportTasksDTO.class);
        this.unmarshaller = context.createUnmarshaller();

    }

    @Override
    public boolean areImported() {
        return this.taskRepository.count() > 0;
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
