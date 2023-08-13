package softuni.exam.models.dto;

import softuni.exam.models.entity.Genre;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ImportBooksDTO {

    @NotNull
    @Size(min = 3, max = 40)
    private String author;
    @NotNull
    private Boolean available;
    @NotNull
    @Size(min = 5)
    private String description;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @NotNull
    @Size(min = 3, max = 40)
    private String title;
    @NotNull
    @Positive
    private Double rating;

    public ImportBooksDTO() {}

    public String getAuthor() {
        return author;
    }

    public Boolean getAvailable() {
        return available;
    }

    public String getDescription() {
        return description;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public Double getRating() {
        return rating;
    }
}
