package softuni.exam.domain.dtos.importXML;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PictureImportDTO {

    @XmlElement
    @NotNull
    private String url;

    public String getUrl() {
        return url;
    }
}
