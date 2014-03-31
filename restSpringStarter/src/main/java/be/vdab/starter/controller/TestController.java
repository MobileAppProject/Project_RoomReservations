package be.vdab.starter.controller;

import be.vdab.starter.model.Person;
import be.vdab.starter.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/hello")
public class TestController {

    @Autowired
    private HelloService helloService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld() {
        return "Hello World";
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayGreeting(@PathParam("name") String  name) {
        return helloService.doGreet(name);
    }

    @GET
    @Path("/person")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPerson() {
        Person result = new Person();
        result.setName("Rudy");
        result.setAge(43);
        return result;
    }

    @PUT
    @Path("/person/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(@PathParam("id") String id, Person person) {
        System.out.println(person);
        if (person.getAge() < 18) {
            return Response.status(Response.Status.PRECONDITION_FAILED).entity("Person must be over 18 years").build();
        }
        return Response.created(URI.create("person/"+id)).build();
    }
}
