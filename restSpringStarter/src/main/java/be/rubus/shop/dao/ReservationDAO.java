package be.rubus.shop.dao;

import be.rubus.shop.model.MeetingRoom;
import be.rubus.shop.model.Product;
import be.rubus.shop.model.Reservation;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


@Repository
public class ReservationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    /*public List<Reservation> getActiveReservations() {

        return sessionFactory.getCurrentSession().createCriteria(Reservation.class).add(Restrictions.eq("active", "Y")).list();
    }*/
    public List<Reservation> getReservations() {

    	List<Reservation> result = sessionFactory.getCurrentSession().createCriteria(Reservation.class).list();
    	
    	result.size();
    	
        return result;
    }
    public List<Reservation> getCurrentReservationsForMeetingRoom(MeetingRoom mr){
    	List<Reservation> result = sessionFactory.getCurrentSession().createCriteria(Reservation.class)
    			.add(Restrictions.eq("meetingRoom", mr))
    			.add(Restrictions.gt("endDate", new Date()))
    			.list();
    	
    	result.size();
    	
        return result;
    }
    
    public List<Reservation> getReservationsForMeetingRoomForDate(MeetingRoom mr, Date date){
    	List<Reservation> result;
    	
    	Query query = sessionFactory.getCurrentSession().createQuery("from Reservation where meetingRoom = :meetingRoom and beginDate between :date1 and :date2 ");
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
//	/**
//	 * @param dateString
//	 * @return
//	 * @throws ParseException
//	 */
//	public Calendar makeCalendarFromString(String dateString) {
//		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
//    	Date date = null;
//		try {
//			date = formatter.parse(dateString);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//    	Calendar myCal = new GregorianCalendar();
//    	myCal.setTime(date);
//		return myCal;
//	}
    
    public MeetingRoom getMeetingRoomById(int id) {

    	Query query = sessionFactory.getCurrentSession().createQuery("from MeetingRoom where meetingRoom_id = :nbr");
        query.setInteger("nbr", id);
        MeetingRoom result = (MeetingRoom) query.uniqueResult();
        return result;
    }

    public void updateReservation(Reservation reservation) {

        sessionFactory.getCurrentSession().update(reservation);
    }

    public void saveReservation(Reservation reservation) {

        sessionFactory.getCurrentSession().save(reservation);
        //used save instead of persist
    }

}
