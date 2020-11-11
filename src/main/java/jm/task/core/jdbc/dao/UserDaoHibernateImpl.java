package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Statement statement = Util.getMyConnection().createStatement()) {
            statement.execute("CREATE TABLE `mydbtest`.`users` (\n" +
                    "  `id` INT AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastname` VARCHAR(45) NOT NULL,\n" +
                    "  `age` INT(3) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;");
        } catch (SQLSyntaxErrorException e) {

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement statement = Util.getMyConnection().createStatement()) {
            statement.execute("DROP TABLE users");
        } catch (SQLSyntaxErrorException e) {

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        User user = new User(name, lastName, age);
        try {
            Session session = Util.getSessionFactory().openSession();
            // start the transaction
            transaction = session.beginTransaction();

            // save student object

            session.save(user);

            // commit transction
            transaction.commit();
            session.clear();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try {
            Session session = Util.getSessionFactory().openSession();
            // start the transaction
            transaction = session.beginTransaction();

            User user = (User) session.get(User.class, id);
            session.delete(user);

            // commit transction
            transaction.commit();
            session.clear();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> allUsers = new ArrayList<User>();
        try {
            Session session = Util.getSessionFactory().openSession();
            // start the transaction
            transaction = session.beginTransaction();

            allUsers = session.createQuery("From User").list();

            // commit transction
            transaction.commit();
            session.clear();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return allUsers;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try {
            Session session = Util.getSessionFactory().openSession();
            // start the transaction
            transaction = session.beginTransaction();

            session.createQuery("delete from User").executeUpdate();

            // commit transction
            transaction.commit();
            session.clear();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
