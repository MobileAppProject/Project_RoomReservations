package be.vdab.starter.controller;

import be.rubus.shop.model.Holiday;
import be.rubus.shop.model.User;
import be.rubus.shop.service.HolidayService;
import be.rubus.shop.service.UserService;

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
