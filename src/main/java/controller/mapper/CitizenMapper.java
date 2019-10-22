package controller.mapper;

import dto.CitizenDTO;
import model.Citizen;
import org.mapstruct.Mapper;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */
@Mapper
public interface CitizenMapper{

    CitizenDTO toDTO(Citizen citizen);

    Citizen toEntity(CitizenDTO citizenDTO);

}
