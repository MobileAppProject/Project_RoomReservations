package be.vdab.meetingroomreservations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import be.vdab.meetingroomreservations.dao.MeetingRoomDAO;
import be.vdab.meetingroomreservations.model.MeetingRoom;
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
}
