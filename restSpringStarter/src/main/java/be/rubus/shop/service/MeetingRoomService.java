package be.rubus.shop.service;

import be.rubus.shop.dao.MeetingRoomDAO;
import be.rubus.shop.dao.ProductDAO;
import be.rubus.shop.dao.ReservationDAO;
import be.rubus.shop.exception.ProductNotChangedException;
import be.rubus.shop.model.MeetingRoom;
import be.rubus.shop.model.Order;
import be.rubus.shop.model.OrderLine;
import be.rubus.shop.model.Product;
import be.rubus.shop.model.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class MeetingRoomService {

    @Autowired
    private MeetingRoomDAO meetingRoomDAO;

    @Transactional
    public List<MeetingRoom> getAllMeetingRooms(){

        List<MeetingRoom> result = meetingRoomDAO.getMeetingRooms();
        
        for(MeetingRoom mr : result){
        	mr.getReservations().size();
        } // this fixes a weird error
        
        
        
        return result;
    }
    
    @Transactional
    public MeetingRoom getMeetingRoomById(int id){
    	MeetingRoom mr = meetingRoomDAO.getMeetingRoomById(id);
    	return mr;
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
