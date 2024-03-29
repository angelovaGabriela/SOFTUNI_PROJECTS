package softuni.exam.models.dtos.importXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "offers")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootOffersImportDTO {

    @XmlElement(name = "offer")
    private List<ImportOfferDTO> offers;

    public List<ImportOfferDTO> getOffers() {
        return offers;
    }
}
