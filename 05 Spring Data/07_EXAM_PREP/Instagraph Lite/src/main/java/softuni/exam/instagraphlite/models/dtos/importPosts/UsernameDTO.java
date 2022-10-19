package softuni.exam.instagraphlite.models.dtos.importPosts;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class UsernameDTO {

    @XmlElement
    @Size(min = 2, max = 18)
    @NotNull
    private String username;

    public String getUsername() {
        return username;
    }
}
