package controller.mapper;

import dto.MetroDTO;
import model.Metro;
import org.mapstruct.Mapper;

@Mapper
public interface MetroMapper extends GenericMapper<Metro, MetroDTO>{

    MetroDTO toDTO(Metro metro);

    Metro toEntity(MetroDTO metroDTO);
}
