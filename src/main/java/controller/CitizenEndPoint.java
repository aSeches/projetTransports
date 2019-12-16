package controller;

import controller.mapper.CitizenMapper;
import dao.CitizenDAO;
import dto.CitizenDTO;
import model.Citizen;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */
@Path("citizen")
public class CitizenEndPoint{

    private CitizenDAO citizenDAO = new CitizenDAO();
    private CitizenMapper mapper = Mappers.getMapper(CitizenMapper.class);

    @Path("/{id}")
    @GET
    public CitizenDTO findById(@PathParam("{id}") long id){
        CitizenDTO citizenDTO = mapper.toDTO(citizenDAO.findById(id));
        return citizenDTO;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CitizenDTO> findAll(){
        List<CitizenDTO> citizenDTOS = new ArrayList<>();

        //IDE suggestion : change type of m to Object, then cast it as Metro
        for(Object c : citizenDAO.findAll()){
            citizenDTOS.add(mapper.toDTO((Citizen) c));
        }
        return citizenDTOS;
    }

    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public void create(CitizenDTO c){
        List<CitizenDTO> citizenDTOS = new ArrayList<>();
    //TODO : complete body method for updating a citizen and adding it to the list
    }

    @Path("/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void update(CitizenDTO c){
        // TODO : complete body for updating a citizen
    }

    @Path("/{id}")
    @DELETE
    public void delete(@PathParam("{id}") long id){
        Citizen citizen = citizenDAO.findById(id);
        citizenDAO.delete(citizen);
    }

}
