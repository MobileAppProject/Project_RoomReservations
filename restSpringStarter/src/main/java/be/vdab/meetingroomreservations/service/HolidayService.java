package be.vdab.meetingroomreservations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.meetingroomreservations.dao.HolidayDAO;
import be.vdab.meetingroomreservations.model.Holiday;

import java.util.List;

@Service
public class HolidayService {

    @Autowired
    private HolidayDAO holidayDAO;

    @Transactional
    public List<Holiday> getAllHolidays() {

        List<Holiday> result = holidayDAO.getHolidays();
        
        return result;
    }
}
