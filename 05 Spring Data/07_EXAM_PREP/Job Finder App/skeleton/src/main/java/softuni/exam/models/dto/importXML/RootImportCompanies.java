package softuni.exam.models.dto.importXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "companies")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootImportCompanies {

    @XmlElement(name = "company")
    private List<ImportCompany> companies;

    public List<ImportCompany> getCompanies() {
        return companies;
    }
}
