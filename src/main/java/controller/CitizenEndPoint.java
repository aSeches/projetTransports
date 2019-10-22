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
        Citizen citizen = citizenDAO.findById(id);
        CitizenDTO citizenDTO = mapper.toDTO(citizen);
        return citizenDTO;
    }

    @GET
    public List<CitizenDTO> findAll(){
        List<CitizenDTO> citizenDTOS = new ArrayList<>();

        for(Citizen c : citizenDAO.findAll()){
            citizenDTOS.add(mapper.toDTO(c));
        }
        return citizenDTOS;
    }


    @Path("/{id}")
    @DELETE
    public void delete(@PathParam("{id}") long id){
        Citizen citizen = citizenDAO.findById(id);
        citizenDAO.delete(citizen);
    }

}
