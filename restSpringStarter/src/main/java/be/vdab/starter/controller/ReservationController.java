package be.vdab.starter.controller;

import be.rubus.shop.model.Product;
import be.rubus.shop.model.Reservation;
import be.rubus.shop.service.ProductService;
import be.rubus.shop.service.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

@Path("reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }
}
