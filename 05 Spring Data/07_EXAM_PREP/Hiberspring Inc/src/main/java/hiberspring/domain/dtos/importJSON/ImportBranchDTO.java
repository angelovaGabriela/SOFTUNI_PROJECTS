package hiberspring.domain.dtos.importJSON;

import javax.validation.constraints.NotNull;

public class ImportBranchDTO {

    @NotNull
    private String name;

    @NotNull
    private String town;

    public ImportBranchDTO() {}

    public String getName() {
        return name;
    }

    public String getTown() {
        return town;
    }
}
