package softuni.exam.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "jobs")
public class Job extends  BaseEntity {


    @Column(nullable = false)
    private String title;


    @Column(nullable = false)
    private double salary;

    @Column(nullable = false)
    private double hoursAWeek;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;


    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id")
    private Company company;

    public Job() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getHoursAWeek() {
        return hoursAWeek;
    }

    public void setHoursAWeek(double hoursAWeek) {
        this.hoursAWeek = hoursAWeek;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("Job title %s%n", this.getTitle()));
        builder.append(String.format("-Salary: %.2f$%n", this.getSalary()));
        builder.append(String.format("--Hours a week: %.2fh%n ", this.getHoursAWeek()));

        return builder.toString();
    }
}
