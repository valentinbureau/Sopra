package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.zelda.Joueur;

import util.Context;

public class DAOJoueur implements IDAOJoueur{

	public DAOJoueur() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Joueur findById(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Joueur joueur = em.find(Joueur.class, id);
		em.close();
		return joueur;
	}

	@Override
	public List<Joueur> findAll() {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();

		Query query = em.createQuery("from Joueur j ", Joueur.class); 
		//getSingleResult si un seul element
		return  query.getResultList(); 
	}

	@Override
	public Joueur save(Joueur joueur) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();		
		joueur=em.merge(joueur);

		em.getTransaction().commit();
		em.close();
		return joueur;	
	}

	@Override
	public void delete(Joueur joueur) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();

		joueur=em.merge(joueur);
		em.remove(joueur);

		em.getTransaction().commit();
		em.close();

	}
	
	@Override
	public Joueur findByLogin(String log) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();

		Query query = em.createQuery("from Joueur j where j.login = :login", Joueur.class); 
		query.setParameter("login", log);
		//getSingleResult si un seul element
		return  (Joueur) query.getSingleResult(); 
	}


	@Override
	public Joueur checkConnect(String login, String password) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query query = em.createQuery("from Joueur j where j.login = :login and j.password = :password", Joueur.class);
		query.setParameter("login", login);
		query.setParameter("password", password);
		//query.getSingleResult();
		//System.out.println("CLasse : "+query.getSingleResult().getClass().getName().toString());
		//query.getSingleResult();
		//String type = (String) query.getParameterValue("typeJoueur");
//		String findPwd = (String) query.getParameterValue("c.password");
//		System.out.println("Pwd:" + findPwd);
		return (Joueur) query.getSingleResult();
	}
	

}
