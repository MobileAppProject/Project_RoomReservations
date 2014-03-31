package be.vdab.starter.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String doGreet(String name) {
        return "Hallo "+name;
    }
}
