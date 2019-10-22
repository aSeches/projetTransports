package controller;

import controller.mapper.BusMapper;
import dao.BusDAO;
import dto.BusDTO;
import model.Bus;
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
@Path("bus")
public class BusEndPoint {

    private BusDAO busDAO;
    private BusMapper mapper = Mappers.getMapper(BusMapper.class);

    @Path("/{id}")
    @GET
    public BusDTO findById(@PathParam("{id}") long id){
        Bus bus = busDAO.findById(id);
        BusDTO busDTO = mapper.toDTO(bus);
        return busDTO;
    }

    @GET
    public List<BusDTO> findAll(){
        List<BusDTO> busDTOS = new ArrayList<>();

        for(Bus b : busDAO.findAll()){
            busDTOS.add(mapper.toDTO(b));
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
