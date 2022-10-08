package softuni.exam.models.entity.importCountries;

import javax.validation.constraints.Size;

public class ImportCountriesDTO {

    @Size(min = 2, max = 60)
    private String countryName;

    @Size(min = 2, max = 20)
    private String currency;

    public ImportCountriesDTO() {}

    public String getCountryName() {
        return countryName;
    }

    public String getCurrency() {
        return currency;
    }
}
