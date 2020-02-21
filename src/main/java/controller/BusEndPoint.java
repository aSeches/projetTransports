package controller;

import controller.mapper.BusMapper;
import dao.BusDAO;
import dto.BusDTO;
import model.Bus;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains CRUD methods for the Bus class
 * Mapping DTOs and concrete objects is made with org.mapstruct.factory.Mappers (in specific package src/main/java/controller/mapper/BusMapper.class)
 *
 * For Cross-origin resource sharing (CORS) purposes,
 * return type for each request must be a Response with headers
 *
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */
@Path("bus")
public class BusEndPoint {

    List<BusDTO> busDTOS = new ArrayList<>();
    private BusDAO busDAO = new BusDAO();
    private BusMapper mapper = Mappers.getMapper(BusMapper.class);

    /**
     * Given a specific id, gets a bus
     *
     * @param id : bus' id
     * @return a bus
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
                .entity(mapper.toDTO(busDAO.findById(id)))
                .build();
    }

    /**
     * Gets the whole table of buses contained in database
     *
     * @return a Response with all buses in database
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        List<BusDTO> busDTOS = new ArrayList<>();

        for(Object b : busDAO.findAll()){
            busDTOS.add(mapper.toDTO((Bus) b));
        }

        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(busDTOS).build();
    }

    /**
     * Given a JSON object containing bus infos, creates it in the database if it doesn't exist already
     *
     * @param b : journey JSON object
     * @return a Response with the created bus
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(BusDTO b){
        Bus bus = mapper.toEntity(b);
        List<Bus> buses = new ArrayList<>();
        List<Long> busesIds = new ArrayList<>();
        for(Object o : busDAO.findAll()){
            buses.add((Bus)o);
        }

        for(Bus o : buses){
            busesIds.add(o.getId());
        }

        if(!(busesIds.contains(bus.getId()))){
            busDAO.create(bus);
            return Response.status(200).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(bus).build();
        }

        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity("Already exists").build();

    }

    /**
     * Given a JSON object containing certain bus' fields, modifies the bus if it exists in database
     *
     * @param b : velo JSON object
     * @return a Response with the modified bus
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(BusDTO b){
        Bus bus = mapper.toEntity(b);

        for(Object o : busDAO.findAll()){
            busDTOS.add(mapper.toDTO((Bus) o));
        }

        if(busDTOS.contains(b)){
            System.out.println("contient le bus : " + bus);
            busDAO.update(bus);
        } else {
            System.out.println("PAS le bus :" + bus);
            busDAO.update(bus);
        }

        return Response.status(200).header("Access-Control-Allow-Origin", "http://localhost:8080/")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(bus).build();
    }

    /**
     * Given a specific id, deletes a bus
     *
     * @param id : bus' id
     * @return a Response with the deleted bus
     */
    @Path("/{id}")
    @DELETE
    public Response delete(@PathParam("id") long id) {
        Bus bus = busDAO.findById(id);
        busDAO.delete(bus);

        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(bus).build();
    }

}
