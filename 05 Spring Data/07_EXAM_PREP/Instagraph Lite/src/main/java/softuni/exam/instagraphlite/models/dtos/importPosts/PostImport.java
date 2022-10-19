package softuni.exam.instagraphlite.models.dtos.importPosts;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PostImport {

    @XmlElement
    @Size(min = 21)
    @NotNull
    private String caption;

    @XmlElement
    @NotNull
    private UsernameDTO user;

    @XmlElement
    @NotNull
    private PictureDTO picture;

    public String getCaption() {
        return caption;
    }

    public UsernameDTO getUser() {
        return user;
    }

    public PictureDTO getPicture() {
        return picture;
    }
}
