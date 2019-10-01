package controller.mapper;

import dto.BusDTO;
import model.Bus;
import org.mapstruct.Mapper;

@Mapper
public interface BusMapper {

    BusDTO toDTO(Bus bus);

    Bus toEntity(BusDTO busDTO);

}
