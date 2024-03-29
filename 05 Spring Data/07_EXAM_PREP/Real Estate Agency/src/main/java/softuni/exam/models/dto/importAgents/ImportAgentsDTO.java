package softuni.exam.models.dto.importAgents;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class ImportAgentsDTO {
    @Size(min = 2)
    private String firstName;
    @Size(min = 2)
    private String lastName;
    private String town;
    @Email
    private String email;

    public ImportAgentsDTO() {}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTown() {
        return town;
    }

    public String getEmail() {
        return email;
    }
}
