package laptopShop.model.dtos.importTowns;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class TownImportDTO {

    @XmlElement
    @Size(min = 2)
    private String name;
    @XmlElement
    @Positive
    private int population;
    @XmlElement(name = "travel-guide")
    @Size(min = 10)
    private String travelGuide;

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public String getTravelGuide() {
        return travelGuide;
    }
}
