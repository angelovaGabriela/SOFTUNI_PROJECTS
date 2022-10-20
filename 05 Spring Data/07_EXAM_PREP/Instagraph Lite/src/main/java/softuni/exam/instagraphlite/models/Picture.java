package softuni.exam.instagraphlite.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "pictures")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String path;


    @Column(nullable = false)
    private Double size;


    @OneToMany(mappedBy = "picture")
    private Set<Post> posts;


    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Picture() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    @Override
    public String toString() {
     return   String.format("%.2f – %s", this.getSize(), this.getPath());
    }


    @Override
    public boolean equals(Object obj) {
        // If the object is compared with itself then return true
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Picture picture)) {
            return false;
        }


        return picture.getPath().equals(this.getPath());
    }

    @Override
    public int hashCode() {
        return this.getPath().hashCode();
    }

}
