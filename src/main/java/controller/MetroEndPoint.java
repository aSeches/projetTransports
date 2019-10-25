package controller;

import controller.mapper.MetroMapper;
import dao.MetroDAO;
import dto.MetroDTO;
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

    private MetroDAO metroDAO = new MetroDAO();
    private MetroMapper mapper = Mappers.getMapper(MetroMapper.class);

    @Path("/{id}")
    @GET
    public MetroDTO findById(@PathParam("{id}") long id){
        MetroDTO metroDTO = mapper.toDTO((Metro) metroDAO.findById(id));
        return metroDTO;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MetroDTO> findAll(){
        List<MetroDTO> metroDTOS = new ArrayList<>();

        //IDE suggestion : change type of m to Object, then cast it as Metro
        for(Object m : metroDAO.findAll()){
            metroDTOS.add(mapper.toDTO((Metro) m));
        }
        return metroDTOS;
    }

    @Path("/{id}")
    @DELETE
    public void delete(@PathParam("{id}")long id){
        Metro metro = (Metro) metroDAO.findById(id);
        metroDAO.delete(metro);
    }

}
