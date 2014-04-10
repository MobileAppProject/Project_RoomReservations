package be.rubus.shop.service;


import be.rubus.shop.dao.ReservationDAO;
import be.rubus.shop.model.MeetingRoom;
import be.rubus.shop.model.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationDAO reservationDAO;

    @Transactional
    public List<Reservation> getAllReservations() {

        List<Reservation> result = reservationDAO.getReservations();
        
        for(Reservation reservation : result){
        	reservation.getMeetingRoom();
        }// this fixes a weird error
        
        
        return result;
    }
    
    @Transactional
    public List<Reservation> getCurrentReservationsForMeetingRoom(MeetingRoom mr){
    	List<Reservation> result = reservationDAO.getCurrentReservationsForMeetingRoom(mr);
        
        for(Reservation reservation : result){
        	reservation.getMeetingRoom();
        }// this fixes a weird error
        return result;
        
    }
    
    @Transactional
    public List<Reservation> getReservationsForMeetingRoomForDate(MeetingRoom mr, Date date){
    	List<Reservation> result = reservationDAO.getReservationsForMeetingRoomForDate(mr, date);
        
        for(Reservation reservation : result){
        	reservation.getMeetingRoom();
        }// this fixes a weird error
        return result;
    }
    
    @Transactional
    public boolean addReservation(Reservation reservation){
    	boolean addSucceeded = false;
    	if(isValidReservation(reservation, -1))
    	{
    		reservationDAO.saveReservation(reservation);
    		addSucceeded = true;
    	}
    	else
    	{
    		System.out.println("reservation was not valid");
    	}
    	
    	return addSucceeded;
    }

  /*  private boolean productNotSelected(Order someOrder, Product someProduct) {
        boolean result = true;
        Iterator<OrderLine> iterator = someOrder.getOrderlines().iterator();
        while (iterator.hasNext() && result) {
            OrderLine orderLine = iterator.next();
            result = !someProduct.equals(orderLine.getProduct());
        }
        return result;
    }

    @Transactional
    public List<Product> getActiveProducts() {
        return productDAO.getActiveProducts();
    }
*/
//    @Transactional
//    public void updateProduct(Product someProduct, Product someNewProduct) {
//        if (someNewProduct.getDescription().length() > 0 || someNewProduct.getPrice() > 0.0) {
//
//            transferData(someProduct, someNewProduct);
//            synchronized (SynchronizingFlag.PRODUCT) {
//                someProduct.setActive("N");
//                productDAO.updateProduct(someProduct);
//                productDAO.saveProduct(someNewProduct);
//            }
//        } else {
//            throw new ProductNotChangedException("error.product.nochange");
//        }
//    }

   /* private void transferData(Product someProduct, Product someNewProduct) {
        if (someNewProduct.getDescription().length() == 0) {
            someNewProduct.setDescription(someProduct.getDescription());
        }
        if (someNewProduct.getPrice() == 0.0) {
            someNewProduct.setPrice(someProduct.getPrice());
        }
        someNewProduct.setMaxOrderQuantity(someProduct.getMaxOrderQuantity());
    }*/
    
    /**
     * 
     * @param reservation
     * @param idForUpdate: only for update, enter -1 when this method is used for adding
     * @return
     */
    private boolean isValidReservation(Reservation reservation, int idForUpdate) {
    	
    	
    	//checken of dit een correcte datum is in de DB; Geen overlappende datum
		List<Reservation> reservations = getReservationsForMeetingRoomForDate(reservation.getMeetingRoom(), getDayForReservation(reservation));
		
		
		
		//check if the reservation date is not in the past
		if(reservation.getBeginDate().before(new Date(System.currentTimeMillis())))
		{
		
			return false;
		}
		
		//check if reservation beginDate is before endDate
		if(reservation.getBeginDate().after(reservation.getEndDate()))
		{
			return false;
		}
		
		//check if reservation happens on the same day, does not encompass multiple days
		Calendar beginCalendar = new GregorianCalendar();
		beginCalendar.setTime(reservation.getBeginDate());
		
		Calendar eindCalendar = new GregorianCalendar();
		eindCalendar.setTime(reservation.getEndDate());
		
		
		if(beginCalendar.get(Calendar.DAY_OF_YEAR) != eindCalendar.get(Calendar.DAY_OF_YEAR)  || beginCalendar.get(Calendar.YEAR) != eindCalendar.get(Calendar.YEAR)){
			return false;
		}
		
		
		
		Date beginDate = reservation.getBeginDate();
		Date endDate = reservation.getEndDate();
		for(Reservation res : reservations){
			//Check if meetingRoom overlaps
			if(reservation.getMeetingRoom().equals(res.getMeetingRoom())){
				if(res.getReservationId() != idForUpdate){
					//Checks if the date of the given reservation is during an existing reservation.
					if(((beginDate.after(res.getBeginDate()) || (beginDate.equals(res.getBeginDate())))  && ( beginDate.before(res.getEndDate()) || beginDate.equals(res.getEndDate()))) 
							|| ((endDate.after( res.getBeginDate()) || endDate.equals(res.getBeginDate())) && ( endDate.before(res.getEndDate()) || endDate.equals(res.getEndDate())))){
						return false;
					}
				}
			}
			
		}
		
		
		return true;
	}
    
    private Date getDayForReservation(Reservation reservation)
    {
    	Date date = reservation.getBeginDate();
    	
    	Calendar calendar = new GregorianCalendar();
    	calendar.setTime(date);
    	calendar.set(Calendar.HOUR_OF_DAY, 0);
    	calendar.set(Calendar.MINUTE, 0);
    	calendar.set(Calendar.SECOND, 0);
    	calendar.set(Calendar.MILLISECOND, 0);
    	
    	return calendar.getTime();
    	
    }
    @Transactional
    public boolean updateReservation(Reservation oldReservation, Reservation newReservation) {
    	boolean updateSucceeded = false;
    	if(isValidReservation(newReservation,oldReservation.getReservationId()))
    	{
    		transferData(oldReservation, newReservation);
    		reservationDAO.updateReservation(oldReservation);
    		updateSucceeded = true;
    	}
    	else
    	{
    		//TODO: throw exception
    	}
    	
    	return updateSucceeded;
    }
    
    
    public Reservation transferData(Reservation r1, Reservation r2){
    	r1.setBeginDate(r2.getBeginDate());
    	r1.setEndDate(r2.getEndDate());
    	r1.setDescription(r2.getDescription());
    	r1.setMeetingRoom(r2.getMeetingRoom());
    	r1.setPersonName(r2.getPersonName());
    	return r1;
    }

    @Transactional
	public Reservation getReservationById(int id) {
		Reservation result = reservationDAO.getReservationById(id);
		return result;
	}
    
    @Transactional
    public boolean deleteReservation(Reservation reservation){
    	reservation.setActive(false);
    	reservationDAO.deleteReservation(reservation);
    	return true;
    }
    
}
