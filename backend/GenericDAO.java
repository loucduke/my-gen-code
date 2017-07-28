package backend;

import java.util.List;

public interface GenericDAO<E> {
	
	public E find(Long id);
	
	public List<E> findAll();
	
	public List<E> findAllPaged(Integer limit, Integer page);	

	public E save(E entity);

	public E update(E entity);

	public E delete(E entity);

	public E deleteById(Long id);

	public void setClazz(Class<E> clazz);
}
