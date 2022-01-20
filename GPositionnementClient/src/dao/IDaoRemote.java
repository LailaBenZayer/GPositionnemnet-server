package dao;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface IDaoRemote<T> {

	public boolean Create(T u);
	public T findById(int id);
	List<T> findAll();
	boolean update(T u);
	boolean delete(T u);
}
