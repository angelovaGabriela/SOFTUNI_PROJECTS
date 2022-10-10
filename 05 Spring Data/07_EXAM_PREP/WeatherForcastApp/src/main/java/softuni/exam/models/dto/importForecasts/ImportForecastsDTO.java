package softuni.exam.models.dto.importForecasts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "forecasts")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportForecastsDTO {

    @XmlElement(name = "forecast")
    private List<ForecastImportDTO> forecasts;

    public List<ForecastImportDTO> getForecasts() {
        return forecasts;
    }
}
