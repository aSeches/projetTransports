package controller;

import controller.mapper.VeloMapper;
import dao.VeloDAO;
import dto.VeloDTO;
import model.Velo;
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
@Path("velo")
public class VeloEndPoint extends GenericEndPoint<Velo, VeloDTO>{

    private VeloDAO veloDAO;
    private VeloMapper mapper = Mappers.getMapper(VeloMapper.class);

    @Path("/{id}")
    @GET
    VeloDTO findById(@PathParam("{id}") long id){
        Velo velo = veloDAO.findById(id);
        VeloDTO veloDTO = mapper.toDTO(velo);
        return veloDTO;
    }

    @GET
    List <VeloDTO> findAll(){
        List<VeloDTO> veloDTOS = new ArrayList<>();

        for(Velo v : veloDAO.findAll()){
            veloDTOS.add(mapper.toDTO(v));
        }
        return veloDTOS;
    }

    @Path("/{id}")
    @DELETE
    void delete(@PathParam("{id}") long id){
        Velo velo = veloDAO.findById(id);
        veloDAO.delete(velo);
    }
}
