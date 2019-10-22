package controller.mapper;

import dto.VeloDTO;
import model.Velo;
import org.mapstruct.Mapper;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */
@Mapper
public interface VeloMapper{

    VeloDTO toDTO(Velo velo);

    Velo toEntity(VeloDTO veloDTO);

}
