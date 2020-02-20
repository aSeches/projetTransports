package rest;

import io.undertow.*;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * RESTful microservice, based on JAX-RS and JBoss Undertow
 *
 */
public class RestServer {

    private static final Logger logger = Logger.getLogger(RestServer.class.getName());

    public static void main( String[] args ) {

        UndertowJaxrsServer ut = new UndertowJaxrsServer();

        RestApplication ta = new RestApplication();

        ResteasyDeployment deployment = new ResteasyDeployment();

        CorsFilter filter = new CorsFilter();
        filter.setAllowedMethods("GET,POST,PUT,DELETE,OPTIONS");
        filter.setAllowedHeaders("X-Requested-With, Content-Type, Content-Length, Authorization");
        filter.getAllowedOrigins().add("*");

        deployment.setProviderFactory(new ResteasyProviderFactory());
        deployment.getProviderFactory().register(filter);
        ut.deploy(ta);

        ut.start(
                Undertow.builder()
                        .addHttpListener(8080, "localhost")

        );

        logger.info("JAX-RS based micro-service running!");
    }
}
