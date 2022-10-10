package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.entity.enums.Day;

import java.util.Optional;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {


    Optional<Forecast> findByDayOfWeekAndCityId(Day dayOfWeek, long id);
}
