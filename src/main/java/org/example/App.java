package org.example;

import org.example.config.Config;
import org.example.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class App {
    public static void main(String[] args) {
       // saveUser(new User("Klara", "Azimova", 29));
        //System.out.println(getById(1L));
        //Config.getSessionFactory();
       // System.out.println(update(1L, "Datka", "Mamatjanova", 20));
       // DeleteUser(2L);
        deleteAllUser();
    }

    public static void saveUser(User user) {

        try {
            Session session = Config.getSessionFactory().openSession();
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    public static User getById(Long id) {
        try {
            Session session = Config.getSessionFactory().openSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.getTransaction().commit();
            session.close();
            return user;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static List<User> getAllUsers(){
        try{
            Session session = Config.getSessionFactory().openSession();
            session.beginTransaction();
            List<User> users = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
            session.close();
            return users;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static User update(Long id, String name, String lastName, int age) {
        try{
            Session session = Config.getSessionFactory().openSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            user.setId(id);
            user.setFirstName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.getTransaction().commit();
            session.close();
            return user;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static void DeleteUser(Long id){
        try{
            Session session = Config.getSessionFactory().openSession();
            session.beginTransaction();
           User user = session.get(User.class, id);
            session.remove(user);
            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void deleteAllUser(){
        try{
            Session session = Config.getSessionFactory().openSession();
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
            session.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
