package controller;

import controller.mapper.VeloMapper;
import dao.VeloDAO;
import dto.VeloDTO;
import model.Velo;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains CRUD methods for the Velo class
 * Mapping DTOs and concrete objects is made with org.mapstruct.factory.Mappers (in specific package src/main/java/controller/mapper/VeloMapper.class)
 *
 * For Cross-origin resource sharing (CORS) purposes,
 * return type for each request must be a Response with headers
 *
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */
@Path("velo")
public class VeloEndPoint{

    List<VeloDTO> veloDTOS = new ArrayList<>();
    private VeloDAO veloDAO = new VeloDAO();
    private VeloMapper mapper = Mappers.getMapper(VeloMapper.class);

    /**
     * Given a specific id, gets a velo
     *
     * @param id : velo's id
     * @return a velo
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
                .entity(mapper.toDTO(veloDAO.findById(id)))
                .build();
    }

    /**
     * Gets the whole table of velos contained in database
     *
     * @return a Response with all velos in database
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        List<VeloDTO> veloDTOS = new ArrayList<>();

        //IDE suggestion : change type of m to Object, then cast it as Velo
        for(Object v : veloDAO.findAll()){
            veloDTOS.add(mapper.toDTO((Velo) v));
        }

        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(veloDTOS).build();
    }

    /**
     * Given a JSON object containing velo infos, creates it in the database if it doesn't exist already
     *
     * @param v : metro JSON object
     * @return a Response with the created velo
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(VeloDTO v){
        Velo velo = mapper.toEntity(v);
        List<Velo> velos = new ArrayList<>();
        List<Long> velosIds = new ArrayList<>();
        for(Object o : veloDAO.findAll()){
            velos.add((Velo)o);
        }

        for(Velo o : velos){
            velosIds.add(o.getId());
        }

        if(!(velosIds.contains(velo.getId()))){
            veloDAO.create(velo);
            return Response.status(200).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(velo).build();
        }

        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity("Already exists").build();

    }

    /**
     * Given a JSON object containing certain velo's fields, modifies the velo if it exists in database
     *
     * @param v : velo JSON object
     * @return a Response with the modified velo
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(VeloDTO v){
        Velo velo = mapper.toEntity(v);

        for(Object o : veloDAO.findAll()){
            veloDTOS.add(mapper.toDTO((Velo) o));
        }

        if(veloDTOS.contains(v)){
            System.out.println("contient le velo : " + velo);
            veloDAO.update(velo);
        } else {
            System.out.println("PAS ce velo : " + velo);
            veloDAO.update(velo);
        }

        return Response.status(200).header("Access-Control-Allow-Origin", "http://localhost:8080/")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(velo).build();
    }

    /**
     * Given a specific id, deletes a velo
     *
     * @param id : velo's id
     * @return a Response with the deleted velo
     */
    @Path("/{id}")
    @DELETE
    public Response delete(@PathParam("id") long id){
        Velo velo = veloDAO.findById(id);
        veloDAO.delete(velo);

        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(velo).build();
    }
}
