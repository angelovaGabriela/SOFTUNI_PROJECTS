package softuni.exam.models.dtos.importJSON;

import javax.validation.constraints.Size;

public class ImportPicturesDTO {

    @Size(min = 3, max = 19)
    private String name;

    private String dateAndTime;

    private long car;

    public ImportPicturesDTO() {}

    public String getName() {
        return name;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public long getCar() {
        return car;
    }
}
