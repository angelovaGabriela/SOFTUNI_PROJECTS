package com.example.xml_processing_exercise.productshop.entities.users;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportSellersWithCountsDTO {

    @XmlElement
    private int usersCount;

    @XmlElementWrapper()
    @XmlElement(name = "user")
    private List<ExportUserWithSoldCountDTO> users;

    public ExportSellersWithCountsDTO() {}

    public ExportSellersWithCountsDTO(
            List<ExportUserWithSoldCountDTO> users) {
        this.users = users;

        this.usersCount = this.users.size();

    }

    public void setUsersCount(int usersCount) {
       this.usersCount = usersCount;
    }

    public void setUsers(List<ExportUserWithSoldCountDTO> users) {
        this.users = users;
    }
}
