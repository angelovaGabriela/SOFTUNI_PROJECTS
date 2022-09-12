package com.example.xml_processing_exercise.productshop.entities.users;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersImportDTO {
    @XmlElement(name = "user")
    private List<UserImportDTo> users;

    public UsersImportDTO() {
    }

    public List<UserImportDTo> getUsers() {
        return users;
    }
}
