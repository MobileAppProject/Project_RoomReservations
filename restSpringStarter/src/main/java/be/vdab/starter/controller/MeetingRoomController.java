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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

@Path("meetingrooms")
public class MeetingRoomController {

    @Autowired
    private MeetingRoomService meetingRoomService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MeetingRoom> getAllmMeetingRooms() {
        return meetingRoomService.getAllMeetingRooms();
    }
}
