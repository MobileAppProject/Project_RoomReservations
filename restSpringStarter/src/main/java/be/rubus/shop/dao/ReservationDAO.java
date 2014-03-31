package be.rubus.shop.dao;

import be.rubus.shop.model.Product;
import be.rubus.shop.model.Reservation;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ReservationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    /*public List<Reservation> getActiveReservations() {

        return sessionFactory.getCurrentSession().createCriteria(Reservation.class).add(Restrictions.eq("active", "Y")).list();
    }*/
    public List<Reservation> getReservations() {

        return sessionFactory.getCurrentSession().createCriteria(Reservation.class).list();
    }

    public void updateReservation(Reservation reservation) {

        sessionFactory.getCurrentSession().update(reservation);
    }

    public void saveReservation(Reservation reservation) {

        sessionFactory.getCurrentSession().persist(reservation);
    }

}
