package softuni.exam.models.dtos.importXML;




import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;


@XmlAccessorType(XmlAccessType.FIELD)
public class ImportOfferDTO {

    @Size(min = 5)
    @XmlElement
    private String description;

    @Positive
    @XmlElement
    private BigDecimal price;


    @XmlElement(name = "added-on")
    @NotNull
    private String addedOn;

    @XmlElement(name = "has-gold-status")
    private boolean hasGoldStatus;
    @XmlElement
    private CarIdDTO car;

    @XmlElement
    private SellerIdDTO seller;

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public boolean isHasGoldStatus() {
        return hasGoldStatus;
    }

    public CarIdDTO getCar() {
        return car;
    }

    public SellerIdDTO getSeller() {
        return seller;
    }
}
