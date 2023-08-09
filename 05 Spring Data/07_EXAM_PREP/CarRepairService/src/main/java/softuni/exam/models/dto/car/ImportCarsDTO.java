package softuni.exam.models.dto.car;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportCarsDTO {


@XmlElement(name = "cars")
private List<CarImportDTO> cars;


    public List<CarImportDTO> getCars() {
        return cars;
    }

}
