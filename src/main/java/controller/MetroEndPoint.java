package controller;

import controller.mapper.MetroMapper;
import dao.MetroDAO;
import dto.MetroDTO;
import model.Metro;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains CRUD methods for the Metro class
 * Mapping DTOs and concrete objects is made with org.mapstruct.factory.Mappers (in specific package src/main/java/controller/mapper/MetroMapper.class)
 *
 * For Cross-origin resource sharing (CORS) purposes,
 * return type for each request must be a Response with headers
 *
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */
@Path("metro")
public class MetroEndPoint {

    List<MetroDTO> metroDTOS = new ArrayList<>();
    private MetroDAO metroDAO = new MetroDAO();
    private MetroMapper mapper = Mappers.getMapper(MetroMapper.class);

    /**
     * Given a specific id, gets a Metro
     *
     * @param id : metro's id
     * @return a metro
     */
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id){
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity(mapper.toDTO(metroDAO.findById(id)))
                .build();
    }

    /**
     * Gets the whole table of metros contained in database
     *
     * @return a Response with all metros in database
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        List<MetroDTO> metroDTOS = new ArrayList<>();

        for(Object m : metroDAO.findAll()){
            metroDTOS.add(mapper.toDTO((Metro) m));
        }

        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(metroDTOS).build();
    }

    /**
     * Given a JSON object containing metro infos, creates it in the database if it doesn't exist already
     *
     * @param m : metro JSON object
     * @return a Response with the created metro
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(MetroDTO m){
        Metro metro = mapper.toEntity(m);
        List<Metro> metros = new ArrayList<>();
        List<Long> metrosIds = new ArrayList<>();
        for(Object o : metroDAO.findAll()){
            metros.add((Metro)o);
        }

        for(Metro o : metros){
            metrosIds.add(o.getId());
        }

        if(!(metrosIds.contains(metro.getId()))){
            metroDAO.create(metro);

            return Response.status(200).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(metro).build();
        }

        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity("Already exists").build();

    }

    /**
     * Given a JSON object containing certain metro's fields, modifies the metro if it exists in database
     *
     * @param m : metro JSON object
     * @return a Response with the modified metro
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(MetroDTO m){
        Metro metro = mapper.toEntity(m);

        for(Object o : metroDAO.findAll()){
            metroDTOS.add(mapper.toDTO((Metro) o));
        }

        if(metroDTOS.contains(m)){
            System.out.println("contient le metro : " + metro);
            metroDAO.update(metro);
        } else {
            System.out.println("PAS ce metro : " + metro);
            metroDAO.update(metro);
        }

        return Response.status(200).header("Access-Control-Allow-Origin", "http://localhost:8080/")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(metro).build();
    }

    /**
     * Given a specific id, deletes a metro
     *
     * @param id : metro's id
     * @return a Response with the deleted metro
     */
    @Path("/{id}")
    @DELETE
    public Response delete(@PathParam("id")long id){
        Metro metro = metroDAO.findById(id);
        metroDAO.delete(metro);
        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(metro).build();
    }

}
