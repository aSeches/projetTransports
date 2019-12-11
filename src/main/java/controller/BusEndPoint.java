package controller;

import controller.mapper.BusMapper;
import dao.BusDAO;
import dto.BusDTO;
import model.Bus;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */
@Path("bus")
public class BusEndPoint {

    private BusDAO busDAO = new BusDAO();
    private BusMapper mapper = Mappers.getMapper(BusMapper.class);

    @Path("/{id}")
    @GET
    public BusDTO findById(@PathParam("{id}") long id){
        BusDTO busDTO = mapper.toDTO(busDAO.findById(id));
        return busDTO;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BusDTO> findAll(){
        List<BusDTO> busDTOS = new ArrayList<>();

        //IDE suggestion : change type of m to Object, then cast it as Metro
        for(Object b : busDAO.findAll()){
            busDTOS.add(mapper.toDTO((Bus) b));
        }
        return busDTOS;
    }

    @Path("/{id}")
    @DELETE
    public void delete(@PathParam("{id}") long id) {
        Bus bus = busDAO.findById(id);
        busDAO.delete(bus);
    }

}
