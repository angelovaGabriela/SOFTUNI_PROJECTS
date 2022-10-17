package softuni.exam.models.dto.importOffers;

import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportOfferDTO {

    @XmlElement
    @Positive
    private BigDecimal price;

    @XmlElement
    private AgentNameDTO agent;

    @XmlElement
    private ApartmentIdDTO apartment;

    @XmlElement
    private String publishedOn;

    public BigDecimal getPrice() {
        return price;
    }

    public AgentNameDTO getAgent() {
        return agent;
    }

    public ApartmentIdDTO getApartment() {
        return apartment;
    }

    public String getPublishedOn() {
        return publishedOn;
    }
}
