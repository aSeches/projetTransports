package controller;

import controller.mapper.JourneyMapper;
import dao.JourneyDAO;
import dto.JourneyDTO;
import model.Journey;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;

@Path("journey")
public class JourneyEndPoint extends GenericEndPoint<Journey,JourneyDTO>{

    private JourneyDAO journeyDAO;
    private JourneyMapper mapper = Mappers.getMapper(JourneyMapper.class);

    @Path("/{id}")
    @GET
    JourneyDTO findById(@PathParam("{id}")long id){
        Journey journey = journeyDAO.findById(id);
        JourneyDTO journeyDTO = mapper.toDTO(journey);
        return journeyDTO;
    }

    @GET
    List<JourneyDTO> findAll(){
        List<JourneyDTO> journeyDTOS = new ArrayList<>();

        for(Journey j : journeyDAO.findAll()){
            journeyDTOS.add(mapper.toDTO(j));
        }
        return journeyDTOS;
    }

    @Path("/{id}")
    @DELETE
    void delete(long id){
        Journey journey = journeyDAO.findById(id);
        journeyDAO.delete(journey);
    }
}
