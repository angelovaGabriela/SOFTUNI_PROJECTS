package softuni.exam.models.dtos.importJSON;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ImportCarsDTO {
    //    "make": "BMW",
    //    "model": "750",
    //    "kilometers": 166235,
    //    "registeredOn": "04/04/2016"
    @Size(min = 3, max = 19)
    private String make;

    @Size(min = 3, max = 19)
    private String model;

    @Positive
    private int kilometers;

    private String registeredOn;

    public ImportCarsDTO() {}

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getKilometers() {
        return kilometers;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }
}
