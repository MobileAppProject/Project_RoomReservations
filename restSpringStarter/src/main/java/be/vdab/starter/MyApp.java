package be.vdab.starter;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class MyApp extends ResourceConfig {

    /**
     * Register JAX-RS application components.
     */
    public MyApp() {
        register(RequestContextFilter.class);
        register(JacksonFeature.class);
        
        System.out.println("blaargh");

    }
}
