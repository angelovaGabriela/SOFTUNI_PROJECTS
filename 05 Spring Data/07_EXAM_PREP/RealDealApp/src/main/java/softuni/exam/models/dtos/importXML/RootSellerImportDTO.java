package softuni.exam.models.dtos.importXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sellers")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootSellerImportDTO {

    @XmlElement(name = "seller")
    private List<ImportSellerDTO> sellers;

    public List<ImportSellerDTO> getSellers() {
        return sellers;
    }
}
