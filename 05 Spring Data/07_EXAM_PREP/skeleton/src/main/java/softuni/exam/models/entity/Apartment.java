package softuni.exam.models.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "apartments")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "apartment_type", nullable = false)
    private ApartmentType apartmentType;

    @Column(nullable = false)
    private double area;

    @ManyToOne(optional = false)
    private Town town;

    @OneToMany(targetEntity = Offer.class, mappedBy = "apartment")
    private Set<Offer> offerSet;

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Set<Offer> getOfferSet() {
        return offerSet;
    }

    public void setOfferSet(Set<Offer> offerSet) {
        this.offerSet = offerSet;
    }

    public Apartment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
    //•	area – more than or equal to 40.00.
    //•	Constraint: The apartment table has а relation with the towns table.
}
