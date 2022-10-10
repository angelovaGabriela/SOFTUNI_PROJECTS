package softuni.exam.models.dto.importForecasts;

import softuni.exam.models.entity.enums.Day;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastImportDTO {

    @XmlElement(name = "day_of_week")
    private Day dayOfWeek;

    @XmlElement(name = "max_temperature")
    @Min(-20) @Max(60)
    private double maxTemperature;

    @XmlElement(name = "min_temperature")
    @Min(-50) @Max(50)
    private double minTemperature;
    @XmlElement
    private String sunrise;
    @XmlElement
    private String sunset;
    @XmlElement
    private CityDTO city;

    public Day getDayOfWeek() {
        return dayOfWeek;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public CityDTO getCity() {
        return city;
    }
}
