package softuni.exam.models.dto.importApartments;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "apartments")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootImportApartmentsDTO {

    @XmlElement(name = "apartment")
    private List<ImportApartmentDTO> apartments;

    public List<ImportApartmentDTO> getApartments() {
        return apartments;
    }
}
