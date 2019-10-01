package controller;

import controller.mapper.JourneyMapper;
import dao.JourneyDAO;
import dto.JourneyDTO;
import model.Journey;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Path("journey")
public class JourneyEndPoint {

    private JourneyDAO journeyDAO;
    private JourneyMapper mapper = Mappers.getMapper(JourneyMapper.class);

    @GET
    List<JourneyDTO> findAll(){
        List<JourneyDTO> journeyDTOS = new ArrayList<>();

        for(Journey j : journeyDAO.findAll()){
            journeyDTOS.add(mapper.toDTO(j));
        }
        return journeyDTOS;
    }
}
