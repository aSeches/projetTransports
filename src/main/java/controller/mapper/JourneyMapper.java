package controller.mapper;

import dto.JourneyDTO;
import model.Journey;
import org.mapstruct.Mapper;

@Mapper
public interface JourneyMapper extends GenericMapper<Journey, JourneyDTO> {

    JourneyDTO toDTO(Journey journey);

    Journey toEntity(JourneyDTO journeyDTO);
}
