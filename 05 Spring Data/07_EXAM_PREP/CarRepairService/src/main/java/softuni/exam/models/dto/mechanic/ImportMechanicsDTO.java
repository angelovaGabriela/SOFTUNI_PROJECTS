package softuni.exam.models.dto.mechanic;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class ImportMechanicsDTO {

    @Email
    private String email;

    @Size(min = 2)
    private String firstName;

    @Size(min = 2)
    private String lastName;

    @Size(min = 2)
    private String phone;

    public ImportMechanicsDTO() {}

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }
}
