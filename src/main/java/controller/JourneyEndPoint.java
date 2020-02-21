package controller;

import controller.mapper.JourneyMapper;
import dao.JourneyDAO;
import dto.BusDTO;
import dto.JourneyDTO;
import model.Bus;
import model.Journey;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains CRUD methods for the Journey class
 * Mapping DTOs and concrete objects is made with org.mapstruct.factory.Mappers (in specific package src/main/java/controller/mapper/JourneyMapper.class)
 *
 * For Cross-origin resource sharing (CORS) purposes,
 * return type for each request must be a Response with headers
 *
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */
@Path("journey")
public class JourneyEndPoint{

    List<JourneyDTO> journeyDTOS = new ArrayList<>();
    private JourneyDAO journeyDAO = new JourneyDAO();
    private JourneyMapper mapper = Mappers.getMapper(JourneyMapper.class);

    /**
     * Given a specific id, gets a journey
     *
     * @param id : journey's id
     * @return a journey
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
                .entity(mapper.toDTO(journeyDAO.findById(id)))
                .build();
    }

    /**
     * Gets the whole table of journey contained in database
     *
     * @return a Response with all journey in database
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        List<JourneyDTO> journeyDTOS = new ArrayList<>();

        for(Object j : journeyDAO.findAll()){
            journeyDTOS.add(mapper.toDTO((Journey) j));
        }

        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(journeyDTOS).build();
    }

    /**
     * Given a JSON object containing journey infos, creates it in the database if it doesn't exist already
     *
     * @param j : journey JSON object
     * @return a Response with the created journey
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(JourneyDTO j){
        Journey journey = mapper.toEntity(j);
        List<Journey> journeys = new ArrayList<>();
        List<Long> journeysIds = new ArrayList<>();
        for(Object o : journeyDAO.findAll()){
            journeys.add((Journey)o);
        }

        for(Journey o : journeys){
            journeysIds.add(o.getId());
        }

        if(!(journeysIds.contains(journey.getId()))){
            journeyDAO.create(journey);
            return Response.status(200).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(journey).build();
        }

        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity("Already exists").build();

    }

    /**
     * Given a JSON object containing certain journey's fields, modifies the journey if it exists in database
     *
     * @param j : journey JSON object
     * @return a Response with the modified journey
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(JourneyDTO j){
        Journey journey = mapper.toEntity(j);

        for(Object o : journeyDAO.findAll()){
            journeyDTOS.add(mapper.toDTO((Journey) o));
        }

        if(journeyDTOS.contains(j)){
            System.out.println("contient le journey : " + journey);
            journeyDAO.update(journey);
        } else {
            System.out.println("PAS le journey :" + journey);
            journeyDAO.update(journey);
        }

        return Response.status(200).header("Access-Control-Allow-Origin", "http://localhost:8080/")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(journey).build();
    }

    /**
     * Given a specific id, deletes a journey
     *
     * @param id : journey's id
     * @return a Response with the deleted journey
     */
    @Path("/{id}")
    @DELETE
    public Response delete(@PathParam("id") long id) {
        Journey journey = journeyDAO.findById(id);
        journeyDAO.delete(journey);

        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(journey).build();
    }
}
