package softuni.exam.models.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "agents")
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false, unique = true)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;


    @Column(nullable = false, unique = true)
    private String email;


    @ManyToOne(optional = false)
    private Town town;

    @OneToMany(targetEntity = Offer.class, mappedBy = "agent")
    private Set<Offer> offerSet;

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Agent() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //TODO: VALIDATIONS IN THE DTOs
    //•	first name – where their character length value higher than or equal to 2.
    //•	last name – accepts char sequences as values where their character length value higher than or equal to 2.
    //•	email – an email – (must contains ‘@’ and ‘.’ – dot).
    //•	Constraint: The agents table has а relation with the towns table.
}
