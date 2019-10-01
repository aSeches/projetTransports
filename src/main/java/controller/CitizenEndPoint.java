package controller;

import controller.mapper.CitizenMapper;
import dao.CitizenDAO;
import dto.CitizenDTO;
import model.Citizen;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;

@Path("citizen")
public class CitizenEndPoint extends GenericEndPoint<Citizen, CitizenDTO> {

    private CitizenDAO citizenDAO;
    private CitizenMapper mapper = Mappers.getMapper(CitizenMapper.class);

    @Path("/{id}")
    @GET
    CitizenDTO findById(@PathParam("{id}") long id){
        Citizen citizen = citizenDAO.findById(id);
        CitizenDTO citizenDTO = mapper.toDTO(citizen);
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


    @Path("/{id}")
    @DELETE
    void delete(@PathParam("{id}") long id){
        Citizen citizen = citizenDAO.findById(id);
        citizenDAO.delete(citizen);
    }

}
