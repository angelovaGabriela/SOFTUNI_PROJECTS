import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class _06_Insert_Address {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();

        // започвам транзакция преди да извърша заявки

        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);

        String addressText = "Vitoshka 15";
        Address address = new Address();
        address.setText(addressText);
        entityManager.persist(address);

        String lastName = scanner.nextLine();

        Employee employee = entityManager
                .createQuery("SELECT e FROM Employee e" +
                                " WHERE e.lastName = :name",
                        Employee.class)
                .setParameter("name", lastName)
                .getSingleResult();

        employee.setAddress(address);

        entityManager.persist(employee);

        // изпълнявам заявките
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}

