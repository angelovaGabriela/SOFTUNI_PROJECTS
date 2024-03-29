package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.entity.enums.Day;

import java.util.List;
import java.util.Optional;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {



   Optional <Forecast> findAllByCityAndDayOfWeek(City city, Day dayOfWeek);

    List<Forecast> findByDayOfWeekAndCityPopulationLessThanOrderByMaxTemperatureDescIdAsc(Day sunday, int maxPopulation);
}
