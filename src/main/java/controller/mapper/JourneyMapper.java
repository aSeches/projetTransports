package controller.mapper;

import dto.JourneyDTO;
import model.Journey;
import org.mapstruct.Mapper;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */
@Mapper
public interface JourneyMapper{

    JourneyDTO toDTO(Journey journey);

    Journey toEntity(JourneyDTO journeyDTO);
}
