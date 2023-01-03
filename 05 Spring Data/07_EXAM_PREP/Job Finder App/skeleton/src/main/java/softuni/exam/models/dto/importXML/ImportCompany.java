package softuni.exam.models.dto.importXML;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportCompany {
    @NotNull
    @Size(min = 2, max = 40)

    @XmlElement(name = "companyName")
    private String name;

    @NotNull
    @XmlElement
    private String dateEstablished;

    @Size(min = 2, max = 30)
    @XmlElement
    @NotNull
    private String website;

    @XmlElement(name = "countryId")
    @NotNull
    private long country;

    public String getName() {
        return name;
    }

    public String getDateEstablished() {
        return dateEstablished;
    }

    public String getWebsite() {
        return website;
    }

    public long getCountry() {
        return country;
    }
}
