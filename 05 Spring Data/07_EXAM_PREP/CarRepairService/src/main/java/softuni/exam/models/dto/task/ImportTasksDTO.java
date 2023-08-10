package softuni.exam.models.dto.task;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tasks")
public class ImportTasksDTO {

    @XmlElement(name = "task")
    private List<TaskImportDTO> tasks;

    public List<TaskImportDTO> getTasks() {
        return tasks;
    }
}
