package hiberspring.domain.dtos.importJSON;

import javax.validation.constraints.NotNull;

public class EmployeeCardDTO {

    @NotNull
    private String number;

    public EmployeeCardDTO() {}

    public String getNumber() {
        return number;
    }
}
