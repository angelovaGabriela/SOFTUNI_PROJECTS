package hiberspring.repository;

import hiberspring.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
 //  Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);


  Optional<Employee> findByFirstNameAndBranchNameAndCardNumber(String firstName, String branch, String card);

  @Query("SELECT e FROM Employee AS e " +
          "JOIN Branch AS b ON e.branch.id = b.id " +
          "WHERE SIZE(b.products) > 0 " +
          "ORDER BY e.firstName ASC, e.lastName ASC, length(e.position) DESC ")
  List<Employee> findByProductsCount();
}
