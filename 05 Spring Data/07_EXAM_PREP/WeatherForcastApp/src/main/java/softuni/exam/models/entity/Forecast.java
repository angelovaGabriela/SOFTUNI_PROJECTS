package softuni.exam.models.entity;

import softuni.exam.models.entity.enums.Day;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "forecasts")
public class Forecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", nullable = false)
    private Day dayOfWeek;


    @Column(name = "max_temperature", nullable = false)
    private double maxTemperature;


    @Column(name = "min_temperature", nullable = false)
    private double minTemperature;

    @Column(nullable = false)
    private LocalTime sunrise;

    @Column(nullable = false)
    private LocalTime sunset;


    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public Forecast() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Day getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Day dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public LocalTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalTime sunrise) {
        this.sunrise = sunrise;
    }

    public LocalTime getSunset() {
        return sunset;
    }

    public void setSunset(LocalTime sunset) {
        this.sunset = sunset;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forecast forecast = (Forecast) o;
        return id == forecast.id && Double.compare(forecast.maxTemperature, maxTemperature) == 0 && Double.compare(forecast.minTemperature, minTemperature) == 0 && dayOfWeek == forecast.dayOfWeek && sunrise.equals(forecast.sunrise) && sunset.equals(forecast.sunset) && city.equals(forecast.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dayOfWeek, maxTemperature, minTemperature, sunrise, sunset, city);
    }

    @Override
    public String toString() {

        //"City: {cityName}:
        //-min temperature: {minTemperature}
        //--max temperature: {maxTemperature}
        //---sunrise: {sunrise}
        //----sunset: {sunset}

        StringBuilder builder = new StringBuilder();
        builder.append(String.format("City: %s:", this.getCity().getCityName())).append(System.lineSeparator());
        builder.append(String.format("-min temperature: %.2f", this.getMinTemperature())).append(System.lineSeparator());
        builder.append(String.format("--max temperature: %.2f", this.getMaxTemperature())).append(System.lineSeparator());
        builder.append(String.format("---sunrise: %s", this.sunrise)).append(System.lineSeparator());
        builder.append(String.format("----sunset: %s", this.sunset)).append(System.lineSeparator());



        return builder.toString();
    }
}
