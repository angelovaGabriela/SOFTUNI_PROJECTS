package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.domain.entities.Player;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {


    List<Player> findByTeamNameOrderByIdAsc(String teamName);

    Optional<Player> findByFirstNameAndLastName(String firstName, String lastName);

    Set<Player> findPlayerByTeamNameOrderByIdAsc(String teamName);

    Set<Player> findBySalaryGreaterThanOrderBySalaryDesc(BigDecimal salary);
}
