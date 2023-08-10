package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.CarEnum;
import softuni.exam.models.entity.Task;


import java.util.List;
import java.util.Optional;

@Repository
public interface TasksRepository extends JpaRepository<Task, Long> {

    Optional<Task> findTaskByMechanicFirstName(String firstName);

    List<Task> findAllByCarsCarTypeOrderByPriceDesc(CarEnum coupe);
}
