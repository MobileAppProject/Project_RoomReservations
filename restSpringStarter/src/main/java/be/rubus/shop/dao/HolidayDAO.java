package be.rubus.shop.dao;

import be.rubus.shop.model.Holiday;
import be.rubus.shop.model.User;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class HolidayDAO {

    @Autowired
    private SessionFactory sessionFactory;
    public List<Holiday> getHolidays() {

        return sessionFactory.getCurrentSession().createCriteria(Holiday.class).list();
    }

    public void updateHoliday(Holiday holiday) {

        sessionFactory.getCurrentSession().update(holiday);
    }

    public void saveHoliday(Holiday holiday) {

        sessionFactory.getCurrentSession().persist(holiday);
    }

}
