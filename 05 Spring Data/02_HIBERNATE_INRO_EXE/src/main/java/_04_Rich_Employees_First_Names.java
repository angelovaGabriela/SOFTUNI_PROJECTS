import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class _04_Rich_Employees_First_Names {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();

        // започвам транзакция преди да извърша заявки
        entityManager.getTransaction().begin();

         List<String> resultList = entityManager
                .createQuery("SELECT e.firstName FROM Employee e" +
                                " WHERE e.salary > 50000" ,
                        String.class)
                  .getResultList();

       String result = String.join("\n", resultList);
        System.out.println(result);



        // изпълнявам заявките
        entityManager.getTransaction().commit();

    }
    }


