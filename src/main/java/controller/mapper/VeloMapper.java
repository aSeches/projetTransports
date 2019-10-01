package controller.mapper;

import dto.VeloDTO;
import model.Velo;
import org.mapstruct.Mapper;

@Mapper
public interface VeloMapper {

    VeloDTO toDTO(Velo velo);

    Velo toEntity(VeloDTO veloDTO);

}
