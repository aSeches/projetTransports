package controller;

import controller.mapper.CitizenMapper;
import dao.CitizenDAO;
import dto.CitizenDTO;
import model.Citizen;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Path("citizen")
public class CitizenEndPoint {

    private CitizenDAO citizenDAO;
    private CitizenMapper mapper = Mappers.getMapper(CitizenMapper.class);

    @GET
    CitizenDTO findById(long id){
        CitizenDTO citizenDTO = new CitizenDTO();
        List<CitizenDTO> citizenDTOS = new ArrayList<>();

        return citizenDTO;
    }

    @GET
    List<CitizenDTO> findAll(){
        List<CitizenDTO> citizenDTOS = new ArrayList<>();

        for(Citizen c : citizenDAO.findAll()){
            citizenDTOS.add(mapper.toDTO(c));
        }
        return citizenDTOS;
    }


}
