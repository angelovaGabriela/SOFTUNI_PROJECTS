package softuni.exam.models.dtos.importXML.tickets;

import softuni.exam.models.dtos.importXML.tickets.FromTownNameDTO;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class TicketImportDTO {


    @XmlElement(name = "serial-number")
    @Size(min = 2)
    private String serialNumber;

    @XmlElement
    @Positive
    private BigDecimal price;
    @XmlElement(name = "take-off")
    private String takeoff;

    @XmlElement(name = "from-town")
    private FromTownNameDTO fromTown;

    @XmlElement(name = "to-town")
    private ToTownNameDTO toTown;

    @XmlElement
    private PassengerEmailDTO passenger;

    @XmlElement
    private PlaneNumberDTO plane;

    public String getSerialNumber() {
        return serialNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getTakeoff() {
        return takeoff;
    }

    public FromTownNameDTO getFromTown() {
        return fromTown;
    }

    public ToTownNameDTO getToTown() {
        return toTown;
    }

    public PassengerEmailDTO getPassenger() {
        return passenger;
    }

    public PlaneNumberDTO getPlane() {
        return plane;
    }
}
