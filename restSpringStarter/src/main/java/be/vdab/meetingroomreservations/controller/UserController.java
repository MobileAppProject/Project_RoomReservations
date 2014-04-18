package be.vdab.meetingroomreservations.controller;

import be.vdab.meetingroomreservations.custom.DTO.LoginCredentialsDTO;
import be.vdab.meetingroomreservations.model.User;
import be.vdab.meetingroomreservations.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

@Path("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    
    @POST
    @Path("/loginUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(LoginCredentialsDTO loginCredDTO){
    	
    	Response response = null;

    	if(loginCredDTO.getUserName().equals("contribute") && loginCredDTO.getPassword().equals("secret")){
    		 response = Response.status(Response.Status.OK).entity("MobileApps2013").build();
    		 //Not sure if this is the way to return the value (in an entity), but no clue how else to be able to return either a string or a Response Object
    	}
    	else{
    		response = Response.status(Response.Status.PRECONDITION_FAILED).entity("wrong username and/or password").build();
    	}
    	return response;
    	
    }
}
