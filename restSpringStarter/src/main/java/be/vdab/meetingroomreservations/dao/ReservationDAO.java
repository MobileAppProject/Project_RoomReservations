package be.vdab.meetingroomreservations.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import be.vdab.meetingroomreservations.model.MeetingRoom;
import be.vdab.meetingroomreservations.model.Reservation;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


@Repository
public class ReservationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Reservation> getReservations() {

    	List<Reservation> result = sessionFactory.getCurrentSession().createCriteria(Reservation.class).list();
    	
    	result.size();
    	
        return result;
    }
    public List<Reservation> getCurrentReservationsForMeetingRoom(MeetingRoom mr){
    	List<Reservation> result = sessionFactory.getCurrentSession().createCriteria(Reservation.class)
    			.add(Restrictions.eq("meetingRoom", mr))
    			.add(Restrictions.gt("beginDate", new Date()))
    			.add(Restrictions.eq("active", true))
    			.list();
    	
    	result.size();
    	
        return result;
    }
    
    public List<Reservation> getAllReservationsForMeetingRoom(MeetingRoom mr){
    	List<Reservation> result = sessionFactory.getCurrentSession().createCriteria(Reservation.class)
    			.add(Restrictions.eq("meetingRoom", mr))
    			.add(Restrictions.eq("active", true))
    			.list();
    	
    	result.size();
    	
        return result;
    }
    
   
    public List<Reservation> getReservationsForMeetingRoomForDate(MeetingRoom mr, Date date){
    	List<Reservation> result;
    	
    	Query query = sessionFactory.getCurrentSession().createQuery("from Reservation where meetingRoom = :meetingRoom and beginDate between :date1 and :date2 and active = 'true'");
    	query.setEntity("meetingRoom", mr);
    	
    	Calendar cal = new GregorianCalendar();
    	cal.setTime(date);
    	query.setDate("date1", cal.getTime());
    	cal.add(Calendar.DAY_OF_YEAR, 1);
    	query.setDate("date2", cal.getTime());
    	result = query.list();
    	result.size();
    	
        return result;
    }

    //TODO: move to MeetingRoomDAO
    public MeetingRoom getMeetingRoomById(int id) {

    	Query query = sessionFactory.getCurrentSession().createQuery("from MeetingRoom where meetingRoom_id = :nbr");
        query.setInteger("nbr", id);
        MeetingRoom result = (MeetingRoom) query.uniqueResult();
        return result;
    }

    public void updateReservation(Reservation reservation) {

    	sessionFactory.getCurrentSession().clear(); //necessary, otherwise hibernate still has the object with this ID present in the session, and it creates a conflict preventing the update;
        sessionFactory.getCurrentSession().update(reservation);
    }
    public void deleteReservation(Reservation reservation) {
    	sessionFactory.getCurrentSession().update(reservation);
    }
    
    

    public void saveReservation(Reservation reservation) {

        sessionFactory.getCurrentSession().save(reservation);
        //used save instead of persist
    }
	public Reservation getReservationById(int id) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Reservation where reservationId = :nbr");
        query.setInteger("nbr", id);
        Reservation result = (Reservation) query.uniqueResult();
        return result;
	}

}
