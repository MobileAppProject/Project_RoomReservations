package be.rubus.shop.service;


import be.rubus.shop.dao.UserDAO;

import be.rubus.shop.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
