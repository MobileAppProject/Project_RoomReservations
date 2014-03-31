package be.rubus.shop.dao;

import be.rubus.shop.model.MeetingRoom;
import be.rubus.shop.model.Product;
import be.rubus.shop.model.Reservation;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MeetingRoomDAO {

    @Autowired
    private SessionFactory sessionFactory;

    /*public List<Reservation> getActiveReservations() {

        return sessionFactory.getCurrentSession().createCriteria(Reservation.class).add(Restrictions.eq("active", "Y")).list();
    }*/
    public List<MeetingRoom> getMeetingRooms() {

    	List<MeetingRoom> result= sessionFactory.getCurrentSession().createCriteria(MeetingRoom.class).list();
    			
    	result.size();	// ??? -  might fix weird problems	
    	
        return result;
    }

    public void updateMeetingRoom(MeetingRoom meetingRoom) {

        sessionFactory.getCurrentSession().update(meetingRoom);
    }

    public void saveMeetingRoom(MeetingRoom meetingRoom) {

        sessionFactory.getCurrentSession().persist(meetingRoom);
    }

}
