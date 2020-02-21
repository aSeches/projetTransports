package controller;

import controller.mapper.CitizenMapper;
import dao.CitizenDAO;
import dto.CitizenDTO;
import model.Citizen;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains CRUD methods for the Citizen class
 * Mapping DTOs and concrete objects is made with org.mapstruct.factory.Mappers (in specific package src/main/java/controller/mapper/CitizenMapper.class)
 *
 * For Cross-origin resource sharing (CORS) purposes,
 * return type for each request must be a Response with headers
 *
 * @author Amaury SECHES and Alex SIHARATH, students of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */
@Path("citizen")
public class CitizenEndPoint{

    private List<CitizenDTO> citizenDTOS = new ArrayList<>();
    private CitizenDAO citizenDAO = new CitizenDAO();
    private CitizenMapper mapper = Mappers.getMapper(CitizenMapper.class);

    /**
     * Given a specific id, gets a Citizen
     *
     * @param id : citizen's id
     * @return a citizen
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
                .entity(mapper.toDTO(citizenDAO.findById(id)))
                .build();
    }

    /**
     * Gets the whole table of citizens contained in database
     *
     * @return a Response with all citizens in database
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        List<CitizenDTO> citizenDTOS = new ArrayList<>();

        for(Object c : citizenDAO.findAll()){
            System.out.println(c);
            citizenDTOS.add(mapper.toDTO((Citizen) c));
        }

        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(citizenDTOS).build();
    }

    /**
     * Given a JSON object containing citizen infos, creates it in the database if it doesn't exist already
     *
     * @param c : citizen JSON object
     * @return a Response with the created citizen
     */
    @POST
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(CitizenDTO c){
        Citizen citizen = mapper.toEntity(c);
        List<Citizen> citizens = new ArrayList<>();
        List<String> citizensNames = new ArrayList<>();
        for(Object o : citizenDAO.findAll()){
            citizens.add((Citizen)o);
        }

        for(Citizen o : citizens){
            citizensNames.add(o.getName());
        }

        if(!citizensNames.contains(citizen.getName())) {
            citizenDAO.create(citizen);

            return Response.status(200).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(citizen).build();
        }

        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity("Already exists").build();
    }

    /**
     * Given a JSON object containing certain citizen's fields, modifies the citizen if it exists in database
     *
     * @param c : citizen JSON object
     * @return a Response with the modified citizen
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(CitizenDTO c){
        Citizen citizen = mapper.toEntity(c);

        for(Object cit : citizenDAO.findAll()){
            citizenDTOS.add(mapper.toDTO((Citizen) cit));
        }
        if(c.getName().equals(citizenDTOS.get((int) c.getId()).getName()) ){
            System.out.println("contient le citoyen : " + citizen);
            citizenDAO.update(citizen);
        } else {
            System.out.println("PAS ce citoyen : " + citizen);
            citizenDAO.update(citizen);
        }
        return Response.status(200).header("Access-Control-Allow-Origin", "http://localhost:8080/")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(citizen).build();
    }

    /**
     * Given a specific id, deletes a citizen
     *
     * @param id : citizen's id
     * @return a Response with the deleted Citizen
     */
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @DELETE
    public Response delete(@PathParam("id") long id){
        Citizen citizen = citizenDAO.findById(id);
        citizenDAO.delete(citizen);
        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(citizen).build();
    }

}
