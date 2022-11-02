package softuni.exam.domain.dtos.importXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "teams")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootTeamsImportDTO {

    @XmlElement(name = "team")
    private List<TeamImportDTO> teams;

    public List<TeamImportDTO> getTeams() {
        return teams;
    }
}
