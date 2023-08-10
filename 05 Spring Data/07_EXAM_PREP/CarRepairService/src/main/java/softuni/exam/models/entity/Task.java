package softuni.exam.models.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {

    @Column(nullable = false)
    private BigDecimal price;

    //yyyy-MM-dd HH:mm:ss" format
    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne
    private Part parts;
    @ManyToOne
    private Mechanic mechanic;

    @ManyToOne
    private Car cars;

    public Task() {}

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Part getParts() {
        return parts;
    }

    public void setParts(Part parts) {
        this.parts = parts;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public Car getCars() {
        return cars;
    }

    public void setCars(Car cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        String FORMAT = "Car %s %s with %dkm\n" +
                "-Mechanic: %s %s - task №%d:\n" +
                "--Engine: %.2f\n" +
                "---Price: %.2f\n";
        return String.format(FORMAT,
                this.getCars().getCarMake(),
                this.getCars().getCarModel(),
                this.getCars().getKilometers(),
                this.getMechanic().getFirstName(),
                this.getMechanic().getLastName(),
                this.getId(),
                this.getCars().getEngine(),
                this.getPrice());
    }
}
