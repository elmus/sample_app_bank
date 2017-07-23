package com.el.service;
import java.util.List;
import java.util.Optional;

/**
 * 
 * @author Mustapha
 *
 */
public interface GenericService<T> {
	public T create(T model);
	public void delete(T model);
	public T update(T model);
	Optional<T> findOne(Long id);
    List<T> findAll();	
}
