package be.rubus.shop.service;


import be.rubus.shop.dao.ReservationDAO;
import be.rubus.shop.model.MeetingRoom;
import be.rubus.shop.model.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    public List<Reservation> getReservationsForMeetingRoomForDate(MeetingRoom mr, String date){
    	List<Reservation> result = reservationDAO.getReservationsForMeetingRoomForDate(mr, date);
        
        for(Reservation reservation : result){
        	reservation.getMeetingRoom();
        }// this fixes a weird error
        return result;
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

    @Transactional
    public void updateProduct(Product someProduct, Product someNewProduct) {
        if (someNewProduct.getDescription().length() > 0 || someNewProduct.getPrice() > 0.0) {

            transferData(someProduct, someNewProduct);
            synchronized (SynchronizingFlag.PRODUCT) {
                someProduct.setActive("N");
                productDAO.updateProduct(someProduct);
                productDAO.saveProduct(someNewProduct);
            }
        } else {
            throw new ProductNotChangedException("error.product.nochange");
        }
    }

    private void transferData(Product someProduct, Product someNewProduct) {
        if (someNewProduct.getDescription().length() == 0) {
            someNewProduct.setDescription(someProduct.getDescription());
        }
        if (someNewProduct.getPrice() == 0.0) {
            someNewProduct.setPrice(someProduct.getPrice());
        }
        someNewProduct.setMaxOrderQuantity(someProduct.getMaxOrderQuantity());
    }*/
}
