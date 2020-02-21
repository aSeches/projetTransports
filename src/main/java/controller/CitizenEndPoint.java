package controller;

import controller.mapper.CitizenMapper;
import dao.CitizenDAO;
import dto.CitizenDTO;
import model.Citizen;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */
@Path("citizen")
public class CitizenEndPoint{

    private List<CitizenDTO> citizenDTOS = new ArrayList<>();
    private CitizenDAO citizenDAO = new CitizenDAO();
    private CitizenMapper mapper = Mappers.getMapper(CitizenMapper.class);

    private Client client = ClientBuilder.newClient();
    private WebTarget target = client.target("http://localhost:8080/citizen");


    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id){
        CitizenDTO citizen= mapper.toDTO(citizenDAO.findById(id));
        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(citizen).build();
    }

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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(CitizenDTO c){
        Citizen citizen = mapper.toEntity(c);

        for(Object o : citizenDAO.findAll()){
            citizenDTOS.add(mapper.toDTO((Citizen) o));
        }
        if(!(citizenDTOS.contains(c))){
            citizenDAO.create(citizen);
        }
        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(citizen).build();
    }

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
        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(citizen).build();
    }

    @Path("/{id}")
    @DELETE
    public Response delete(@PathParam("id") long id){
        Citizen citizen = citizenDAO.findById(id);
        citizenDAO.delete(citizen);
        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity(id).build();
    }

}
