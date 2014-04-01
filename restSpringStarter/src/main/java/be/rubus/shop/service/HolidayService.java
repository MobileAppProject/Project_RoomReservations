package be.rubus.shop.service;


import be.rubus.shop.dao.HolidayDAO;
import be.rubus.shop.dao.UserDAO;
import be.rubus.shop.model.Holiday;
import be.rubus.shop.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
