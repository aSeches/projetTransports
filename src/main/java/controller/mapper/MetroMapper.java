package controller.mapper;

import dto.MetroDTO;
import model.Metro;
import org.mapstruct.Mapper;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */
@Mapper
public interface MetroMapper extends GenericMapper<Metro, MetroDTO>{

    MetroDTO toDTO(Metro metro);

    Metro toEntity(MetroDTO metroDTO);
}
