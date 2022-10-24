package softuni.exam.models.dtos.importXML.tickets;

import softuni.exam.models.dtos.importXML.tickets.TicketImportDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tickets")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootImportTicketDTO {

    @XmlElement(name = "ticket")
    private List<TicketImportDTO> tickets;

    public List<TicketImportDTO> getTickets() {
        return tickets;
    }
}
