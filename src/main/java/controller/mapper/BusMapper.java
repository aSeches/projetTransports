package controller.mapper;

import dto.BusDTO;
import model.Bus;
import org.mapstruct.Mapper;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */
@Mapper
public interface BusMapper extends GenericMapper<Bus, BusDTO> {

    BusDTO toDTO(Bus bus);

    Bus toEntity(BusDTO busDTO);

}
