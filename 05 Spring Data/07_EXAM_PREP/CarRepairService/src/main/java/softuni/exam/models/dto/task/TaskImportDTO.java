package softuni.exam.models.dto.task;



import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class TaskImportDTO {

    @NotNull
    @XmlElement
    private String date;
    @NotNull
    @Positive
    @XmlElement
    private BigDecimal price;

    @NotNull
    @XmlElement
    private CarDTO cars;
    @NotNull
    @XmlElement
    private MechanicDTO mechanic;

    @NotNull
    @XmlElement
    private PartDTO parts;


    public String getDate() {
        return date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CarDTO getCars() {
        return cars;
    }

    public MechanicDTO getMechanic() {
        return mechanic;
    }

    public PartDTO getParts() {
        return parts;
    }
}
