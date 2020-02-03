package controller;

import controller.mapper.MetroMapper;
import dao.MetroDAO;
import dto.CitizenDTO;
import dto.MetroDTO;
import model.Citizen;
import model.Metro;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */
@Path("metro")
public class MetroEndPoint {

    List<MetroDTO> metroDTOS = new ArrayList<>();
    private MetroDAO metroDAO = new MetroDAO();
    private MetroMapper mapper = Mappers.getMapper(MetroMapper.class);

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public MetroDTO findById(@PathParam("id") long id){
        MetroDTO metroDTO = mapper.toDTO(metroDAO.findById(id));
        return metroDTO;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MetroDTO> findAll(){
        List<MetroDTO> metroDTOS = new ArrayList<>();

        for(Object m : metroDAO.findAll()){
            metroDTOS.add(mapper.toDTO((Metro) m));
        }
        return metroDTOS;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void create(MetroDTO m){
        Metro metro = mapper.toEntity(m);

        for(Object o : metroDAO.findAll()){
            metroDTOS.add(mapper.toDTO((Metro) o));
        }
        if(!(metroDTOS.contains(metro))){
            metroDAO.create(metro);
        }

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void update(MetroDTO m){
        Metro metro = mapper.toEntity(m);

        for(Object o : metroDAO.findAll()){
            metroDTOS.add(mapper.toDTO((Metro) o));
        }

        if(metroDTOS.contains(m)){
            metroDAO.update(metro);
        } else {
            metroDAO.update(metro);
        }
    }

    @Path("/{id}")
    @DELETE
    public void delete(@PathParam("id")long id){
        Metro metro = metroDAO.findById(id);
        metroDAO.delete(metro);
    }

}
