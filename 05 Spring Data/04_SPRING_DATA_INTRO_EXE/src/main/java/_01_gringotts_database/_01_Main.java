package _01_gringotts_database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static javax.persistence.Persistence.createEntityManagerFactory;

public class _01_Main {

    public static void main(String[] args) {
        EntityManagerFactory factory =
                createEntityManagerFactory("CodeFirstEx");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();





        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

