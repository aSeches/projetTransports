package rest;

import controller.*;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<>();


        // SWAGGER endpoints
        resources.add(OpenApiResource.class);

        //Your own resources. (endpoints)
        resources.add(CitizenEndPoint.class);
        resources.add(BusEndPoint.class);
        resources.add(MetroEndPoint.class);
        resources.add(VeloEndPoint.class);
        resources.add(JourneyEndPoint.class);
        return resources;
    }
}
