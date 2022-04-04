import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class _07_Addresses_Employee_Count {
    public static void main(String[] args) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();

        // започвам транзакция преди да извърша заявки
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);

         entityManager
                .createQuery("FROM Address a" +
                                " ORDER BY a.employees.size DESC",
                        Address.class)
                 .setMaxResults(10)
                .getResultStream()
                .forEach(System.out::println);




        // изпълнявам заявките
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}

