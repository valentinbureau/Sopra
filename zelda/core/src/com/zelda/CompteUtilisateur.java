package com.zelda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompteUtilisateur {
	
	static Map<String, CompteUtilisateur> listUtilisateur = new HashMap();
	private String login; //Unique
	private String mail; //Unique
	private String password;
	private int idAvatar;
	private int idArme;
	
	public CompteUtilisateur() {listUtilisateur.put(this.login, new CompteUtilisateur());
	}
	
	

	public CompteUtilisateur(String login, String mail, String password, int idAvatar, int idArme) {
		this.login = login;
		this.mail = mail;
		this.password = password;
		this.idAvatar = idAvatar;
		this.idArme = idArme;
		listUtilisateur.put(this.login, this);
	}

	public CompteUtilisateur(String login, String mail, String password) {
		this.login = login;
		this.mail = mail;
		this.password = password;
		listUtilisateur.put(this.login, this);
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static Map<String, CompteUtilisateur> getListUtilisateur() {
		return listUtilisateur;
	}



	public static void setListUtilisateur(Map<String, CompteUtilisateur> listUtilisateur) {
		CompteUtilisateur.listUtilisateur = listUtilisateur;
	}



	public int getIdAvatar() {
		return idAvatar;
	}



	public void setIdAvatar(int idAvatar) {
		this.idAvatar = idAvatar;
	}



	public int getIdArme() {
		return idArme;
	}



	public void setIdArme(int idArme) {
		this.idArme = idArme;
	}



	@Override
	public String toString() {
		return "CompteUtilisateur [login=" + login + ", mail=" + mail + ", password=" + password + ", idAvatar="
				+ idAvatar + ", idArme=" + idArme + "]";
	}
	
}
