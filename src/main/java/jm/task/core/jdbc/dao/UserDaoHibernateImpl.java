package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() { //создать таблицу
        String mysql = "CREATE TABLE IF NOT EXISTS `tableuser`.`users` (\n" +
                "  `ID` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `NAME` VARCHAR(45) NOT NULL,\n" +
                "  `LASTNAME` VARCHAR(45) NOT NULL,\n" +
                "  `AGE` INT NOT NULL,\n" +
                "        PRIMARY KEY (`ID`, `NAME`, `LASTNAME`, `AGE`))\n" +
                "        ENGINE = InnoDB\n" +
                "        DEFAULT CHARACTER SET = utf8";
        try (Session session = Util.getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            Query query = session.createSQLQuery(mysql).addEntity(User.class);
            query.executeUpdate();
            session.getTransaction();
            System.out.println("Успех создания");
        } catch (Throwable e) {
            System.out.println("Таблица уже существует");
        }
    }

    @Override
    public void dropUsersTable() { //удалить таблицу
        String mysql = "DROP TABLE IF EXISTS users";
        try (Session session = Util.getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            Query query = session.createSQLQuery(mysql).addEntity(User.class);
            query.executeUpdate();
            System.out.println("Успех удаления");
        } catch (Throwable e) {
            System.out.println("Таблица не существует");
           }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) { //сохранить пользователя
        try (Session session = Util.getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            //session.getTransaction().commit();
            System.out.println("Успех добавления");
        } catch (Throwable e) {
            System.out.println("Ошибка добавления");
            Util.getSessionFactory().close();
        }
    }

    @Override
    public void removeUserById(long id) { //уд по ид
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            User emp = session.get(User.class, id);
            session.delete(emp);
            session.getTransaction().commit();
            System.out.println("Успех удаления");
        } catch (Throwable e) {
            System.out.println("Ошибка удаления");
            Util.getSessionFactory().close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            return (List<User>)session.createSQLQuery("SELECT * FROM users").addEntity(User.class).list();
        }
    } //список всех адресов

    @Override
    public void cleanUsersTable() { //чистая т ю
        String mysql = "TRUNCATE TABLE users";
        try (Session session = Util.getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            Query query = session.createSQLQuery(mysql).addEntity(User.class);
            query.executeUpdate();
            System.out.println("Успех очищения");
        } catch (Throwable e) {
            System.out.println("Ошибка очищения");
        }
    }
}
