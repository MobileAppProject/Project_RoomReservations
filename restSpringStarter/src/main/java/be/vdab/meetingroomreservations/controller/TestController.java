package be.vdab.meetingroomreservations.controller;

import be.vdab.meetingroomreservations.model.Reservation;
import be.vdab.meetingroomreservations.service.ReservationService;
import be.vdab.starter.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/hello")
public class TestController {

    @Autowired
    private HelloService helloService;
    
    @Autowired
    private ReservationService reservationService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld() {
        return "Hello World";
    }

    @GET
    @Path("/reservations")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reservation> getReservations() {
        return reservationService.getAllReservations();
    }
}
