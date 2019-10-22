package controller;

import controller.mapper.JourneyMapper;
import dao.JourneyDAO;
import dto.JourneyDTO;
import model.Journey;
import org.hibernate.annotations.Cascade;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */
@Path("journey")
public class JourneyEndPoint{

    private JourneyDAO journeyDAO;
    private JourneyMapper mapper = Mappers.getMapper(JourneyMapper.class);

    @Path("/{id}")
    @GET
    public JourneyDTO findById(@PathParam("{id}")long id){
        JourneyDTO journeyDTO = mapper.toDTO((Journey) journeyDAO.findById(id));
        return journeyDTO;
    }

    @GET
    public List<JourneyDTO> findAll(){
        List<JourneyDTO> journeyDTOS = new ArrayList<>();

        //IDE suggestion : change type of m to Object, then cast it as Journey
        for(Object j : journeyDAO.findAll()){
            journeyDTOS.add(mapper.toDTO((Journey) j));
        }
        return journeyDTOS;
    }

    @Path("/{id}")
    @DELETE
    public void delete(long id){
        Journey journey = (Journey) journeyDAO.findById(id);
        journeyDAO.delete(journey);
    }
}
