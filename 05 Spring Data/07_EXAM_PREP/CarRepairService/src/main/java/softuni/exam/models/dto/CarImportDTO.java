package softuni.exam.models.dto;

import softuni.exam.models.entity.CarEnum;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarImportDTO {

    @NotNull
    @Size(min = 2, max = 30)
    @XmlElement
    private String carMake;

    @NotNull
    @Size(min = 2, max = 30)
    @XmlElement
    private String carModel;

    @NotNull
    @Positive
    @XmlElement
    private Integer year;


    @NotNull
    @Size(min = 2, max = 30)
    @XmlElement
    private String plateNumber;

    @NotNull
    @Positive
    @XmlElement
    private Integer kilometers;

    @NotNull
    @DecimalMin(value = "1.00")
    @XmlElement
    private Double engine;

    @NotNull
    @XmlElement
    private CarEnum carType;

    public String getCarMake() {
        return carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public Integer getYear() {
        return year;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public Integer getKilometers() {
        return kilometers;
    }

    public Double getEngine() {
        return engine;
    }

    public CarEnum getCarType() {
        return carType;
    }
}


