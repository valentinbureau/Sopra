package dao;

import java.util.List;

import com.zelda.Joueur;

public interface IDAO <T,K> {
	
	public T findById(K id);
	public List<T> findAll();
	public T save(T object);
	public void delete(T object);
	Joueur findByLogin(String log);
	Joueur checkConnect(String login, String password);
	
}
