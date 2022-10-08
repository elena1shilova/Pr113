package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    //Session session = Util.getSessionFactory().getCurrentSession();
    //Transaction tr = null;
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() { //создать таблицу
       // tr =

        String mysql = "CREATE TABLE IF NOT EXISTS `tableuser`.`users` (\n" +
                "  `ID` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `NAME` VARCHAR(45) NOT NULL,\n" +
                "  `LASTNAME` VARCHAR(45) NOT NULL,\n" +
                "  `AGE` INT NOT NULL,\n" +
                "        PRIMARY KEY (`ID`, `NAME`, `LASTNAME`, `AGE`))\n" +
                "        ENGINE = InnoDB\n" +
                "        DEFAULT CHARACTER SET = utf8";
        //Query query = session.createSQLQuery(mysql).addEntity(User.class);
        //query.executeUpdate();
        //tr.commit();
        try (Session session = Util.getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            Query query = session.createSQLQuery(mysql).addEntity(User.class);
            query.executeUpdate();
            session.getTransaction();
                    //.commit();
            System.out.println("Успех создания");
        } catch (Throwable e) {
            System.out.println("Таблица уже существует");
            /*try {
                session.getTransaction().rollback();
                //session.close();
            } catch (Exception ew) {
                e.printStackTrace();
            }*/

        }
    }

    @Override
    public void dropUsersTable() { //удалить таблицу

        String mysql = "DROP TABLE IF EXISTS users";
        //Query query = session.createSQLQuery(mysql).addEntity(User.class);
        //session.getTransaction().commit();
        try (Session session = Util.getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            Query query = session.createSQLQuery(mysql).addEntity(User.class);
            query.executeUpdate();
            //session.getTransaction();
                    //.commit();
            System.out.println("Успех удаления");
        } catch (Throwable e) {
            System.out.println("Таблица не существует");
           /* try {
                session.getTransaction().rollback();
                //session.close();
            } catch (Exception ignore) {
                e.printStackTrace();
            }*/

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) { //сохранить пользователя
        try (Session session = Util.getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.println("Успех добавления");
        } catch (Throwable e) {
            System.out.println("Ошибка добавления");
            Util.getSessionFactory().close();
        }
        /*Transaction transaction = null;
        Session session = null;
        User userss = new User(name, lastName, age);
        try  {
            session = Util.getSessionFactory().openSession();
            // start a transaction
            transaction = session.beginTransaction();

            // save the student object
            session.save(userss);
            transaction.commit();
        } catch (Throwable e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }*/
    }

    @Override
    public void removeUserById(long id) { //уд по ид

    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            return (List<User>)session.createSQLQuery("SELECT * FROM users").addEntity(User.class).list();
        }
        //return null;
    } //список всех адресов

    @Override
    public void cleanUsersTable() { //чистая т ю

    }
}
