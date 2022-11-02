package softuni.exam.domain.dtos.importXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "pictures")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootPicturesImportDTO {

    @XmlElement(name = "picture")
    private List<PictureImportDTO> pictures;

    public List<PictureImportDTO> getPictures() {
        return pictures;
    }
}
