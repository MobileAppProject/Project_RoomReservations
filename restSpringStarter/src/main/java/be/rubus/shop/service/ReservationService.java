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
    	if(isValidReservation(reservation))
    	{
    		reservationDAO.saveReservation(reservation);
    		addSucceeded = true;
    	}
    	else
    	{
    		//TODO: throw exception
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
    
    private boolean isValidReservation(Reservation reservation) {
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
		
		
		Date beginDate = reservation.getBeginDate();
		Date endDate = reservation.getEndDate();
		Boolean result = true;
		for(Reservation res : reservations)
		{
			//Checks if the date of the given reservation is during an existing reservation.
			if(((beginDate.after(res.getBeginDate()) || (beginDate.equals(res.getBeginDate())))  && ( beginDate.before(res.getEndDate()) || beginDate.equals(res.getEndDate()))) 
					|| ((endDate.after( res.getBeginDate()) || endDate.equals(res.getBeginDate())) && ( endDate.before(res.getEndDate()) || endDate.equals(res.getEndDate()))))
			{
				result = false;
				break;
			}
		}
		
		
		return result;
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
}
