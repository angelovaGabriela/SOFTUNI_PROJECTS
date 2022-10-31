package softuni.exam.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {

    private BigDecimal price;


    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "has_gold_status")
    private boolean hasGoldStatus;

    @Column(name = "added_on")
    private String addedOn;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "offers_pictures",
            joinColumns = @JoinColumn(name = "offer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id", referencedColumnName = "id"))
    private Set<Picture> pictures = new HashSet<>();

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHasGoldStatus() {
        return hasGoldStatus;
    }

    public void setHasGoldStatus(boolean hasGoldStatus) {
        this.hasGoldStatus = hasGoldStatus;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }


    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return hasGoldStatus == offer.hasGoldStatus && Objects.equals(price, offer.price) && description.equals(offer.description) && addedOn.equals(offer.addedOn) && Objects.equals(car, offer.car) && Objects.equals(seller, offer.seller) && Objects.equals(pictures, offer.pictures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, description, hasGoldStatus, addedOn, car, seller, pictures);
    }
}
