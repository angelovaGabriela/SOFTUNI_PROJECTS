package softuni.exam.instagraphlite.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;


    @ManyToOne
    @JoinColumn(name = "profile_picture_id", nullable = false)
    private Picture profilePicture;

    @OneToMany
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }


    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public User() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Picture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Picture profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("User: %s", this.getUsername())).append(System.lineSeparator());
        builder.append(String.format("Post count: %d", this.getPosts().size()));

        return builder.toString();
    }
}
