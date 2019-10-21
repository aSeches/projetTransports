package controller.mapper;

import dto.VeloDTO;
import model.Velo;
import org.mapstruct.Mapper;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */
@Mapper
public interface VeloMapper extends GenericMapper<Velo, VeloDTO> {

    VeloDTO toDTO(Velo velo);

    Velo toEntity(VeloDTO veloDTO);

}
