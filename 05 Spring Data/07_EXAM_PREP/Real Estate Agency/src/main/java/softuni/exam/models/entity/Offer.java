package softuni.exam.models.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

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

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    public Offer() {}

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

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    @Override
    public String toString() {


        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Agent %s %s with offer № %d:",
                this.getAgent().getFirstName(),
                this.agent.getLastName(), this.getId()))
                .append(System.lineSeparator());

        builder.append(String.format("-Apartment area: %.2f",
                this.getApartment().getArea()))
                .append(System.lineSeparator());

        builder.append(String.format("--Town: %s",
                this.getApartment().getTown().getTownName()))
                .append(System.lineSeparator());

        builder.append(String.format("---Price: %.2f$",
                this.getPrice())).append(System.lineSeparator());



        return builder.toString();
    }
}
