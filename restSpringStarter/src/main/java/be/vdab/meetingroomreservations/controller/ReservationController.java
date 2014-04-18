package be.vdab.meetingroomreservations.controller;

import be.vdab.meetingroomreservations.model.MeetingRoom;
import be.vdab.meetingroomreservations.model.Reservation;
import be.vdab.meetingroomreservations.service.MeetingRoomService;
import be.vdab.meetingroomreservations.service.ReservationService;
import be.vdab.meetingroomreservations.custom.DTO.MeetingRoomIdAndDateDTO;
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
    	MeetingRoom meetingRoom = meetingRoomService.getMeetingRoomById(meetingRoomID);
        return reservationService.getCurrentReservationsForMeetingRoom(meetingRoom);
    }
    @GET
    @Path("/AllReservationsForMeetingRoomID/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reservation> getAllReservationsForMeetingRoomId(@PathParam("id") int meetingRoomID) {
    	MeetingRoom meetingRoom = meetingRoomService.getMeetingRoomById(meetingRoomID);
        return reservationService.getAllReservationsForMeetingRoom(meetingRoom);
    }
    
    @POST
    @Path("/ReservationsForMeetingRoomIDForDate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Reservation> getReservationsForMeetingRoomForDate(MeetingRoomIdAndDateDTO dto) {
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
    	reservation.setActive(true);
    	
   		if(reservationService.addReservation(reservation))
   		{
   			response = Response.status(Response.Status.CREATED).entity("Reservation has been added").build();
   		}
    	return response;
    	
    	//TODO: appropriate errors and messages still need to be returned
    }
    
    @PUT
    @Path("/updateReservation/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateReservation(@PathParam("id") int id, Reservation newReservation) {
    	Response response = Response.status(Response.Status.NOT_ACCEPTABLE).entity("Reservation has not been updated.").build();
    	Reservation oldReservation = reservationService.getReservationById(id);
    	
   		if(reservationService.updateReservation(oldReservation, newReservation))
   		{
   			response = Response.status(Response.Status.CREATED).entity("Reservation has been updated").build();
   		}
    	return response;
    }
    
    @PUT
    @Path("/deleteReservation/{id}")
    public Response deleteReservation(@PathParam("id") int id){
    	Response response = Response.status(Response.Status.OK).entity("Reservation has been deleted").build();
    	
    	reservationService.deleteReservation(reservationService.getReservationById(id));
    	return response;
    }
}
