package controller.mapper;

import java.io.Serializable;

public interface GenericMapper<T extends Serializable, O extends Serializable> {

    O toDTO(T object);

    T toEntity(O object);
}
