package softuni.exam.models.dto.importOffers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentIdDTO {

    @XmlElement
    private long id;

    public long getId() {
        return id;
    }
}
