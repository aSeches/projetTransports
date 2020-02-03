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
    public CitizenDTO findById(@PathParam("id") long id){
        return mapper.toDTO(citizenDAO.findById(id));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CitizenDTO> findAll(){
        List<CitizenDTO> citizenDTOS = new ArrayList<>();

        for(Object c : citizenDAO.findAll()){
            System.out.println(c);
            citizenDTOS.add(mapper.toDTO((Citizen) c));
        }
        return citizenDTOS;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void create(CitizenDTO c){
        Citizen citizen = mapper.toEntity(c);

        for(Object o : citizenDAO.findAll()){
            citizenDTOS.add(mapper.toDTO((Citizen) o));
        }
        if(!(citizenDTOS.contains(citizen))){
            citizenDAO.create(citizen);
        }

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void update(CitizenDTO c){
        Citizen citizen = mapper.toEntity(c);

        for(Object cit : citizenDAO.findAll()){
            citizenDTOS.add(mapper.toDTO((Citizen) cit));
        }

        if(citizenDTOS.contains(c)){
            citizenDAO.update(citizen);
        } else {
            citizenDAO.update(citizen);
        }
    }

    @Path("/{id}")
    @DELETE
    public void delete(@PathParam("id") long id){
        Citizen citizen = citizenDAO.findById(id);
        citizenDAO.delete(citizen);
    }

}
