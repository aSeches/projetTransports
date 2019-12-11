package controller;

import controller.mapper.VeloMapper;
import dao.VeloDAO;
import dto.VeloDTO;
import model.Velo;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */
@Path("velo")
public class VeloEndPoint{

    private VeloDAO veloDAO = new VeloDAO();
    private VeloMapper mapper = Mappers.getMapper(VeloMapper.class);

    @Path("/{id}")
    @GET
    public VeloDTO findById(@PathParam("{id}") long id){
        VeloDTO veloDTO = mapper.toDTO(veloDAO.findById(id));
        return veloDTO;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List <VeloDTO> findAll(){
        List<VeloDTO> veloDTOS = new ArrayList<>();

        //IDE suggestion : change type of m to Object, then cast it as Velo
        for(Object v : veloDAO.findAll()){
            veloDTOS.add(mapper.toDTO((Velo) v));
        }
        return veloDTOS;
    }

    @Path("/{id}")
    @DELETE
    public void delete(@PathParam("{id}") long id){
        Velo velo = veloDAO.findById(id);
        veloDAO.delete(velo);
    }
}
