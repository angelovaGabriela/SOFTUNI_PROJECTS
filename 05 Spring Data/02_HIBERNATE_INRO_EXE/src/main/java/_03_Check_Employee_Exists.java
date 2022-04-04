import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class _03_Check_Employee_Exists {
    public static void main(String[] args) {


    EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("PU_Name");

    EntityManager entityManager = factory.createEntityManager();

    // започвам транзакция преди да извърша заявки
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        String[] searchFor = scanner.nextLine().split(" ");




    Long employeeCount =  entityManager
            .createQuery("SELECT COUNT(e) FROM Employee e" +
                    " WHERE e.firstName = :first_name" +
                    " AND e.lastName = :last_name",
                    Long.class)
            .setParameter("first_name", searchFor[0])
            .setParameter("last_name", searchFor[1])
            .getSingleResult();

        if (employeeCount > 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }




    // изпълнявам заявките
        entityManager.getTransaction().commit();

}
}
