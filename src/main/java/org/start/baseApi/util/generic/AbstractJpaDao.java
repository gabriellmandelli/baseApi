package org.start.baseApi.util.generic;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class AbstractJpaDao < T extends Serializable> {

    private Class<T> entityClass;

    @PersistenceContext
    EntityManager entityManager;

    public final void setEntity( Class<T> entitySet){
        this.entityClass = entitySet;
    }

    public T findOne(UUID id){
        return entityManager.find(entityClass, id);
    }
    public List< T > findAll(){
        return entityManager.createQuery( "from " + entityClass.getName() )
                .getResultList();
    }

    public T create( T entity ){
        entityManager.persist( entity );
        return entity;
    }

    public T update( T entity ){
        return entityManager.merge( entity );
    }

    public void delete( T entity ){
        entityManager.remove( entity );
    }
    public void deleteById( UUID entityId ){
        T entity = findOne( entityId );
        delete( entity );
    }
}
