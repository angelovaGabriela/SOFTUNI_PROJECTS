package softuni.exam.models.dtos.importXML.plane;

import softuni.exam.models.dtos.importXML.plane.PlaneImportDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "planes")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootImportPlaneDTO {

    @XmlElement(name = "plane")
    private List<PlaneImportDTO> planes;

    public List<PlaneImportDTO> getPlanes() {
        return planes;
    }
}
