package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import dao.DAOJoueur;
import dao.IDAOJoueur;

public class Context {

	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("legendsopra");
	private static Context _instance=null;
	private IDAOJoueur daoJoueur = new DAOJoueur();
	
	private Context() 
	{}
	
	
	public static Context getInstance() 
	{
		if(_instance==null) {_instance=new Context();}
		return _instance;
	}
	
	
	public EntityManagerFactory getEmf() {
		return emf;
	}


	public void closeEmf() 
	{
		emf.close();
	}


	public IDAOJoueur getDaoJoueur() {
		return daoJoueur;
	}


	public void setDaoJoueur(IDAOJoueur daoJoueur) {
		this.daoJoueur = daoJoueur;
	}
	
	

	
}
