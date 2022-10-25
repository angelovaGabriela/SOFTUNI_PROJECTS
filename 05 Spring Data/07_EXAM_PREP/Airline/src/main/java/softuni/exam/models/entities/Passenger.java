package softuni.exam.models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "passengers")
public class Passenger extends BaseEntity {


    @Column(name = "first_name", nullable = false)
    private String firstName;


    @Column(name = "last_name", nullable = false)
    private String lastName;

    private int age;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "passenger", fetch = FetchType.EAGER)
    private Set<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "town_id")
    private Town town;

    public Passenger() {}

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        //"Passenger {firstName}  {lastName}
        //
        //Email - {email}
        //
        //Phone - {phoneNumber}
        //
        //Number of tickets - {number of tickets}

        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Passenger %s %s", this.getFirstName(), this.getLastName())).append(System.lineSeparator());
        builder.append(String.format("Email - %s", this.getEmail())).append(System.lineSeparator());
        builder.append(String.format("Phone - %s", this.getPhoneNumber())).append(System.lineSeparator());
        builder.append(String.format("Number of tickets - %d", this.getTickets().size())).append(System.lineSeparator());


        return builder.toString();
    }
}
