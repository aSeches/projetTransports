package controller.mapper;

import dto.CitizenDTO;
import model.Citizen;
import org.mapstruct.Mapper;

@Mapper
public interface CitizenMapper extends GenericMapper<Citizen, CitizenDTO>{

    CitizenDTO toDTO(Citizen citizen);

    Citizen toEntity(CitizenDTO citizenDTO);

}
