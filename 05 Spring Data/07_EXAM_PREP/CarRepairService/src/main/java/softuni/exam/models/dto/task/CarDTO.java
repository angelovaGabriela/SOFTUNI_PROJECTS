package softuni.exam.models.dto.task;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CarDTO {
    @XmlElement
    private long id;

    public long getId() {
        return id;
    }
}
