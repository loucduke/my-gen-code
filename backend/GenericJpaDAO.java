package backend;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericJpaDAO<E> implements GenericDAO<E>{
	
	@PersistenceContext
	private EntityManager em;
	
	private Class<E> clazz;
	
	public GenericJpaDAO() {}
	
	public E find(Long id){
		return em.find(this.clazz, id);
	}
	
	public List<E> findAll(){
		return em.createQuery( "from " + clazz.getName() , this.clazz).getResultList();
	}
	
	public List<E> findAllPaged(Integer limit, Integer page){
		Query q = em.createQuery( "from " + clazz.getName() , this.clazz);
		q.setMaxResults(limit);
		q.setFirstResult(limit * (page - 1));
		return q.getResultList();
	}
	

	public E save( E entity ){
		em.persist( entity );
		return entity;
	}

	public E update( E entity ){
		return em.merge( entity );
	}

	public E delete( E entity ){
		em.remove( entity );
		return entity;
	}

	public E deleteById( Long id ){
		E entity = find( id );
		return delete( entity );
	}

	@Override
	public void setClazz(Class<E> clazz) {
		this.clazz = clazz;
	}	
	
}
