package controller.mapper;

import dto.JourneyDTO;
import model.Journey;
import org.mapstruct.Mapper;

@Mapper
public interface JourneyMapper {

    JourneyDTO toDTO(Journey journey);

    Journey toEntity(JourneyDTO journeyDTO);
}
