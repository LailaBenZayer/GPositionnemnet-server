package dao;

import java.util.List;

import javax.ejb.Local;


@Local
public interface IDaoLocal<T> {

	public boolean Create(T u);
	public T findById(int id);
	List<T> findAll();
	boolean update(T u);
	boolean delete(T u);
}
