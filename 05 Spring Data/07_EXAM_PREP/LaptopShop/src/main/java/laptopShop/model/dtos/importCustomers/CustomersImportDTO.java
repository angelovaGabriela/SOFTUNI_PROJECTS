package laptopShop.model.dtos.importCustomers;

import laptopShop.model.dtos.importShops.TownNameDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class CustomersImportDTO {

    //email, first_name, last_name, registered_on, town_id

    @Size(min = 2)
    private String firstName;
    @Size(min = 2)
    private String lastName;
    @Email
    private String email;
    private String registeredOn;
    private TownNameDTO town;

    public CustomersImportDTO() {}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }

    public TownNameDTO getTown() {
        return town;
    }
}
