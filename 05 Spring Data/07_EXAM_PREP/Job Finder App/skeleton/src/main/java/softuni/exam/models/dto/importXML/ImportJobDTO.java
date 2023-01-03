package softuni.exam.models.dto.importXML;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportJobDTO {

    @Size(min = 2, max = 40)
    @XmlElement(name = "jobTitle")
    @NotNull
    private String title;

    @Min(10)
    @XmlElement
    @NotNull
    private double hoursAWeek;

    @Min(300)
    @XmlElement
    @NotNull
    private double salary;

    @Size(min = 5)
    @XmlElement
    @NotNull
    private String description;

    @XmlElement(name = "companyId")
    @NotNull
    private long company;

    public String getTitle() {
        return title;
    }

    public double getHoursAWeek() {
        return hoursAWeek;
    }

    public double getSalary() {
        return salary;
    }

    public String getDescription() {
        return description;
    }

    public long getCompany() {
        return company;
    }
}
