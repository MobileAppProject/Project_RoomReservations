package be.vdab.meetingroomreservations.controller;

import be.vdab.meetingroomreservations.model.Holiday;
import be.vdab.meetingroomreservations.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("holidays")
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Holiday> getAllHolidays() {
        return holidayService.getAllHolidays();
    }
}
