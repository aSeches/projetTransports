package controller;

import controller.mapper.BusMapper;
import dao.BusDAO;
import dto.BusDTO;
import model.Bus;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Path("bus")
public class BusEndPoint {

    private BusDAO busDAO;
    private BusMapper mapper = Mappers.getMapper(BusMapper.class);

    @GET
    List<BusDTO> findAll(){
        List<BusDTO> busDTOS = new ArrayList<>();

        for(Bus b : busDAO.findAll()){
            busDTOS.add(mapper.toDTO(b));
        }
        return busDTOS;
    }

}
