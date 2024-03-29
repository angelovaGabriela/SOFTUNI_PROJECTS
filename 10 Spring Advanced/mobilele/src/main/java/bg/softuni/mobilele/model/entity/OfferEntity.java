package bg.softuni.mobilele.model.entity;

import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.model.enums.TransmissionEnum;
import com.sun.istack.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;


@Entity
@Table(name = "offers")
public class OfferEntity {

@Id
@GeneratedValue(generator = "UUID")
@GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
)
@Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EngineEnum engine;

    private String imageUrl;

    @Column(nullable = false)
    private Integer mileage;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransmissionEnum transmission;

    @Column(nullable = false)
    private Integer year;

    @ManyToOne
    private ModelEntity model;

    @ManyToOne
    private UserEntity seller;
    // много оферти могат да отидат при един user


    public UUID getId() {
        return id;
    }

    public OfferEntity setId(UUID id) {
        this.id = id;
        return this;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public void setEngine(EngineEnum engine) {
        this.engine = engine;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public ModelEntity getModel() {
        return model;
    }

    public void setModel(ModelEntity model) {
        this.model = model;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public void setSeller(UserEntity seller) {
        this.seller = seller;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "OfferEntity{" +
                "id=" + id +
                ", engine=" + engine +
                ", imageUrl='" + imageUrl + '\'' +
                ", mileAge=" + mileage +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", transmission=" + transmission +
                ", year=" + year +
                ", model=" + model +
                ", seller=" + seller +
                '}';
    }
}
