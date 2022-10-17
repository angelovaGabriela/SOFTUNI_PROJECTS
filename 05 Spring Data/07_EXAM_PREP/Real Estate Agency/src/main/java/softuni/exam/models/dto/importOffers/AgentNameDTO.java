package softuni.exam.models.dto.importOffers;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class AgentNameDTO {
    @Size(min = 2)
    @XmlElement
    private String name;

    public String getName() {
        return name;
    }
}
