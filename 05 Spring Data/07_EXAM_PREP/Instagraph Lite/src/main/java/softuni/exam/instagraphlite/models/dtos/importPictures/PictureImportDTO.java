package softuni.exam.instagraphlite.models.dtos.importPictures;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PictureImportDTO {

    @NotNull
    private String path;

    @Min(500) @Max(60000)
    private double size;

    public PictureImportDTO() {}

    public String getPath() {
        return path;
    }

    public double getSize() {
        return size;
    }
}
