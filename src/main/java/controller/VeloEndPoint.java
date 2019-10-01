package controller;

import controller.mapper.VeloMapper;
import dao.VeloDAO;
import dto.VeloDTO;
import model.Velo;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Path("velo")
public class VeloEndPoint {

    private VeloDAO veloDAO;
    private VeloMapper mapper = Mappers.getMapper(VeloMapper.class);

    @GET
    List <VeloDTO> findAll(){
        List<VeloDTO> veloDTOS = new ArrayList<>();

        for(Velo v : veloDAO.findAll()){
            veloDTOS.add(mapper.toDTO(v));
        }
        return veloDTOS;
    }
}
