package org.example;

import org.example.domain.Booking;
import org.example.domain.Hotel;
import org.example.domain.Room;
import org.example.domain.User;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class Main {
    public static void main(String[] args) {
        // 1. Charger la configuration
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        // 2. Charger les entités
        configuration.addAnnotatedClass(Booking.class);
        configuration.addAnnotatedClass(Hotel.class);
        configuration.addAnnotatedClass(Room.class);
        configuration.addAnnotatedClass(User.class);

        // 3. Créer un EntityManager
        EntityManagerFactory factory = configuration.buildSessionFactory();
        EntityManager em = factory.createEntityManager();

        // Quelques opérations CRUD
        User maxime = new User();
        maxime.setEmail("maxime.top@gmail.com");

        em.getTransaction().begin();
        em.persist(maxime);
        em.getTransaction().commit();

        User annie = new User();
        annie.setEmail("annie.versaire@gmail.com");

        em.getTransaction().begin();
        em.persist(annie);
        em.getTransaction().commit();

        // em.getTransaction().begin();
        // em.remove(maxime);
        // em.getTransaction().commit();

        // User uu = em.find(User.class, 2);
        // System.out.println(uu.getEmail());

        List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        for (User u : users) {
            System.out.println(u);
        }
    }
}