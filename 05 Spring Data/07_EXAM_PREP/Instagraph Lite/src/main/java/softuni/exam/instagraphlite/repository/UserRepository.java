package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.instagraphlite.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);


    @Query("SELECT u from User u ORDER BY size(u.posts) DESC , u.id ")
    List<User> getAllUsersOrderedByCountOfPostsThenById();

    User findUserByUsername(String name);
}
