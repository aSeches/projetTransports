package controller.mapper;

import dto.JourneyDTO;
import model.Journey;
import org.mapstruct.Mapper;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */
@Mapper
public interface JourneyMapper extends GenericMapper<Journey, JourneyDTO> {

    JourneyDTO toDTO(Journey journey);

    Journey toEntity(JourneyDTO journeyDTO);
}
