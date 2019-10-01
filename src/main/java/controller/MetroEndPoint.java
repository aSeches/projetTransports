package controller;

import controller.mapper.MetroMapper;
import dao.MetroDAO;
import dto.MetroDTO;
import model.Metro;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;

@Path("metro")
public class MetroEndPoint extends GenericEndPoint<Metro, MetroDTO> {

    private MetroDAO metroDAO;
    private MetroMapper mapper = Mappers.getMapper(MetroMapper.class);

    @Path("/{id}")
    @GET
    MetroDTO findById(@PathParam("{id}") long id){
        Metro metro = metroDAO.findById(id);
        MetroDTO metroDTO = mapper.toDTO(metro);
        return metroDTO;
    }

    @GET
    List<MetroDTO> findAll(){
        List<MetroDTO> metroDTOS = new ArrayList<>();

        for(Metro m : metroDAO.findAll()){
            metroDTOS.add(mapper.toDTO(m));
        }
        return metroDTOS;
    }

    @Path("/{id}")
    @DELETE
    void delete(@PathParam("{id}")long id){
        Metro metro = metroDAO.findById(id);
        metroDAO.delete(metro);
    }

}
