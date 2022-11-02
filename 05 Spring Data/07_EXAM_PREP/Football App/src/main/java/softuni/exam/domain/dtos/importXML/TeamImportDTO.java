package softuni.exam.domain.dtos.importXML;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class TeamImportDTO {


    @XmlElement
    @Size(min = 3, max = 20)
    private String name;

    @XmlElement
    private PictureUrlDTO picture;

    public String getName() {
        return name;
    }

    public PictureUrlDTO getPicture() {
        return picture;
    }
}
