package softuni.exam.models.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "published_on", nullable = false)
    private LocalDate publishedOn;



    @ManyToOne(optional = false)
    private Apartment apartment;

    @ManyToOne(optional = false)
    private Agent agent;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Offer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(LocalDate publishedOn) {
        this.publishedOn = publishedOn;
    }
    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offer)) return false;
        Offer offer = (Offer) o;
        return getId() == offer.getId() && getPrice().equals(offer.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPrice());
    }
//•	price – positive number.
    //•	published on – a date in the "dd/MM/yyyy" format.
    //•	Constraint: The offers table has a relation with the apartments table.
    //•	Constraint: The offers table has a relation with the agents table.
}
