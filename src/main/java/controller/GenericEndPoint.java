package controller;

import controller.mapper.GenericMapper;
import dao.DAO;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Amaury SECHES, Student of Master's degree in Computer Science, ISTIC (Rennes, FRANCE)
 */
public abstract class GenericEndPoint <T extends Serializable, O extends Serializable> {

    private DAO<T> DAObject;
    private GenericMapper<T, O> mapper = Mappers.getMapper(GenericMapper.class);

    @Path("/{id}")
    @GET
    O findById(long id){
        T object = DAObject.findById(id);
        O DTObject = mapper.toDTO(object);
        return DTObject;
    }

    @GET
    List<O> findAll(){
        List<O> dtobjectsList = new ArrayList<>();

        for(T object : DAObject.findAll()){
            dtobjectsList.add(mapper.toDTO(object));
        }

        return dtobjectsList;
    }

    //TODO : ajouter les méthodes PUT et POST pour créer des entités en base.

    @Path("/{id}")
    @DELETE
    void delete(@PathParam("{id}") long id){
        T object = DAObject.findById(id);
        DAObject.delete(object);
    }
}
