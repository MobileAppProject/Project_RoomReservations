package be.vdab.meetingroomreservations.controller;

import be.vdab.meetingroomreservations.model.MeetingRoom;
import be.vdab.meetingroomreservations.service.MeetingRoomService;
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
