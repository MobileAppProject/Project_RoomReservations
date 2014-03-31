package be.vdab.starter.controller;

import be.vdab.starter.AbstractRestTest;
import be.vdab.starter.service.HelloService;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.omg.CORBA.Any;

import javax.ws.rs.core.Application;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class TestControllerTest extends AbstractRestTest {

    @Mock
    private HelloService helloServiceMock;

    @Override
    protected Application configure() {
        TestController controller = new TestController();

        MockitoAnnotations.initMocks(this);
        injectMocks(controller, helloServiceMock);

        ResourceConfig config = new ResourceConfig();
        config.register(controller);
        // To disable Spring integration
        config.property(CommonProperties.METAINF_SERVICES_LOOKUP_DISABLE, Boolean.TRUE);

        return config;
    }

    @Test
    public void testUpdatePerson() {
        when(helloServiceMock.doGreet(anyString())).thenReturn("JUnit is the best");

        String hello = target("hello/rudy").request().get(String.class);
        assertEquals("JUnit is the best", hello);
    }
}
