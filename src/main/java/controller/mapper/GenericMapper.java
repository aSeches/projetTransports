package controller.mapper;

import java.io.Serializable;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */
public interface GenericMapper<T extends Serializable, O extends Serializable> {

    O toDTO(T object);

    T toEntity(O object);
}
