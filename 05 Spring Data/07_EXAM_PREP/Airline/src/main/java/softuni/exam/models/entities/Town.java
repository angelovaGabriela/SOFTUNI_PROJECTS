package softuni.exam.models.entities;

import softuni.exam.models.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "towns")
public class Town extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;


    private int population;

    @Column(columnDefinition = "text")
    private String guide;

    public Town() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }
}
