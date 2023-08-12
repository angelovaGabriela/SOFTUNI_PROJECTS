package softuni.exam.models.dto;



import softuni.exam.models.dto.CarDTO;
import softuni.exam.models.dto.MechanicDTO;
import softuni.exam.models.dto.PartDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "task")
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
