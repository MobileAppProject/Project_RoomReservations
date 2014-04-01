package be.vdab.starter.controller;

import be.rubus.shop.model.MeetingRoom;
import be.rubus.shop.model.Product;
import be.rubus.shop.model.Reservation;
import be.rubus.shop.service.MeetingRoomService;
import be.rubus.shop.service.ProductService;
import be.rubus.shop.service.ReservationService;
import be.vdab.custom.DTO.MeetingRoomIdAndDateDTO;

import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Date;
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
    
    @POST
    @Path("/ReservationsForMeetingRoomIDForDate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Reservation> getReservationsForMeetingRoomForDate(MeetingRoomIdAndDateDTO dto) {
    	//meetingRoom = meetingRoomService.getAllMeetingRooms().get(0);
    	
    	int meetingRoomID = dto.getMeetingRoomID();
    	Date date = dto.getDate();
    	
    	MeetingRoom meetingRoom = meetingRoomService.getMeetingRoomById(meetingRoomID);
    	
        return reservationService.getReservationsForMeetingRoomForDate(meetingRoom, date);
    }
    
    @POST
    @Path("/addReservation")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addReservation(Reservation reservation){
    	System.out.println(reservation.toString());
    	Response response = Response.status(Response.Status.NOT_ACCEPTABLE).entity("Reservation has not been added.").build();
    	
   		if(reservationService.addReservation(reservation))
   		{
   			response = Response.status(Response.Status.CREATED).entity("Reservation has been added").build();
   		}
    	return response;
    	
    	//TODO: appropriate errors and messages still need to be returned
    }

	
    
}
