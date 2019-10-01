package controller;

import controller.mapper.MetroMapper;
import dao.MetroDAO;
import dto.MetroDTO;
import model.Metro;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Path("metro")
public class MetroEndPoint {

    private MetroDAO metroDAO;
    private MetroMapper mapper = Mappers.getMapper(MetroMapper.class);

    @GET
    List<MetroDTO> findAll(){
        List<MetroDTO> metroDTOS = new ArrayList<>();

        for(Metro m : metroDAO.findAll()){
            metroDTOS.add(mapper.toDTO(m));
        }
        return metroDTOS;
    }

}
