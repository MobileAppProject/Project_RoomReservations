package be.vdab.meetingroomreservations.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import be.vdab.meetingroomreservations.model.User;

import java.util.List;


@Repository
public class UserDAO {

    @Autowired
    private SessionFactory sessionFactory;
    public List<User> getUsers() {

        return sessionFactory.getCurrentSession().createCriteria(User.class).list();
    }

    public void updateUser(User user) {

        sessionFactory.getCurrentSession().update(user);
    }

    public void saveUser(User user) {

        sessionFactory.getCurrentSession().persist(user);
    }

}
