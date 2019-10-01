package controller.mapper;

import dto.BusDTO;
import model.Bus;
import org.mapstruct.Mapper;

@Mapper
public interface BusMapper extends GenericMapper<Bus, BusDTO> {

    BusDTO toDTO(Bus bus);

    Bus toEntity(BusDTO busDTO);

}
