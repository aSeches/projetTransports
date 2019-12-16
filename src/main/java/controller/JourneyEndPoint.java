package controller;

import controller.mapper.JourneyMapper;
import dao.JourneyDAO;
import dto.CitizenDTO;
import dto.JourneyDTO;
import model.Journey;
import org.hibernate.annotations.Cascade;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */
@Path("journey")
public class JourneyEndPoint{

    private JourneyDAO journeyDAO = new JourneyDAO();
    private JourneyMapper mapper = Mappers.getMapper(JourneyMapper.class);

    @Path("/{id}")
    @GET
    public JourneyDTO findById(@PathParam("{id}")long id){
        JourneyDTO journeyDTO = mapper.toDTO(journeyDAO.findById(id));
        return journeyDTO;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<JourneyDTO> findAll(){
        List<JourneyDTO> journeyDTOS = new ArrayList<>();

        //IDE suggestion : change type of m to Object, then cast it as Journey
        for(Object j : journeyDAO.findAll()){
            journeyDTOS.add(mapper.toDTO((Journey) j));
        }
        return journeyDTOS;
    }

    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public void create(JourneyDTO j){
        List<JourneyDTO> journeyDTOS = new ArrayList<>();
        //TODO : complete body method for updating a journey and adding it to the list
    }

    @Path("/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void update(JourneyDTO j){
        // TODO : complete body for updating a journey
    }

    @Path("/{id}")
    @DELETE
    public void delete(long id){
        Journey journey = journeyDAO.findById(id);
        journeyDAO.delete(journey);
    }
}
