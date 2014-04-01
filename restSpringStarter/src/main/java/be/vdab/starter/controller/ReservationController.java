package be.vdab.starter.controller;

import be.rubus.shop.model.MeetingRoom;
import be.rubus.shop.model.Product;
import be.rubus.shop.model.Reservation;
import be.rubus.shop.service.MeetingRoomService;
import be.rubus.shop.service.ProductService;
import be.rubus.shop.service.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

@Path("reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private MeetingRoomService meetingRoomService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }
    
    @GET
    @Path("/CurrentReservationsForMeetingRoomID/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reservation> getReservations(@PathParam("id") int meetingRoomID) {
    	//meetingRoom = meetingRoomService.getAllMeetingRooms().get(0);
    	MeetingRoom meetingRoom = meetingRoomService.getMeetingRoomById(meetingRoomID);
        return reservationService.getCurrentReservationsForMeetingRoom(meetingRoom);
    }
    
    @GET
    @Path("/ReservationsForMeetingRoomIDForDate/{id}/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reservation> getReservationsForMeetingRoomForDate(@PathParam("id") int meetingRoomID, @PathParam("date") String date) {
    	//meetingRoom = meetingRoomService.getAllMeetingRooms().get(0);
    	MeetingRoom meetingRoom = meetingRoomService.getMeetingRoomById(meetingRoomID);
    	
        return reservationService.getReservationsForMeetingRoomForDate(meetingRoom, date);
    }
}
