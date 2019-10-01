package controller.mapper;

import dto.VeloDTO;
import model.Velo;
import org.mapstruct.Mapper;

@Mapper
public interface VeloMapper extends GenericMapper<Velo, VeloDTO> {

    VeloDTO toDTO(Velo velo);

    Velo toEntity(VeloDTO veloDTO);

}
