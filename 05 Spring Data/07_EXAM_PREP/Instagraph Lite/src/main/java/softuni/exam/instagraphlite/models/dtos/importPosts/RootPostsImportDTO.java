package softuni.exam.instagraphlite.models.dtos.importPosts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "posts")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootPostsImportDTO {
    @XmlElement(name = "post")
    private List<PostImport> posts;

    public List<PostImport> getPosts() {
        return posts;
    }
}
