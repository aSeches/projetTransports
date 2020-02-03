package controller;

import controller.mapper.BusMapper;
import dao.BusDAO;
import dto.BusDTO;
import dto.CitizenDTO;
import model.Bus;
import model.Citizen;
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

    List<BusDTO> busDTOS = new ArrayList<>();
    private BusDAO busDAO = new BusDAO();
    private BusMapper mapper = Mappers.getMapper(BusMapper.class);

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public BusDTO findById(@PathParam("id") long id){
        return mapper.toDTO(busDAO.findById(id));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BusDTO> findAll(){
        List<BusDTO> busDTOS = new ArrayList<>();

        for(Object b : busDAO.findAll()){
            busDTOS.add(mapper.toDTO((Bus) b));
        }
        return busDTOS;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void create(BusDTO b){
        Bus bus = mapper.toEntity(b);

        for(Object o : busDAO.findAll()){
            busDTOS.add(mapper.toDTO((Bus) o));
        }
        if(!(busDTOS.contains(bus))){
            busDAO.create(bus);
        }

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void update(BusDTO b){
        Bus bus = mapper.toEntity(b);

        for(Object o : busDAO.findAll()){
            busDTOS.add(mapper.toDTO((Bus) o));
        }

        if(busDTOS.contains(b)){
            busDAO.update(bus);
        } else {
            busDAO.update(bus);
        }
    }

    @Path("/{id}")
    @DELETE
    public void delete(@PathParam("id") long id) {
        Bus bus = busDAO.findById(id);
        busDAO.delete(bus);
    }

}
