package be.vdab.meetingroomreservations.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.meetingroomreservations.dao.UserDAO;
import be.vdab.meetingroomreservations.model.User;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Transactional
    public List<User> getAllUsers() {

        List<User> result = userDAO.getUsers();
        
        return result;
    }
}
