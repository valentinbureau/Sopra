package com.zelda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Joueur implements Serializable{
	
	//static Map<String, Joueur> listUtilisateur = new HashMap();
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String login; //Unique
	private String mail; //Unique
	private String password;
	
	@Embedded
	@AttributeOverrides({
        @AttributeOverride(name="posX",
        column=@Column(name="posX_link")),
        @AttributeOverride(name="posY",
        column=@Column(name="posY_link")),
        @AttributeOverride(name="vie",
        column=@Column(name="vie_link")),
	})
	@Column(nullable=true)
	private Link link=new Link();
	
	public Joueur() {}

	public Joueur(String login, String mail, String password, Link link) {
		this.login = login;
		this.mail = mail;
		this.password = password;
		this.link = link;
	}

	public Joueur(String login, String mail, String password) {
		this.login = login;
		this.mail = mail;
		this.password = password;
		this.link= new Link();
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

	public Link getAvatar() {
		return link;
	}
	
	public void setAvatar(Link link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "Joueur [login=" + login + ", mail=" + mail + ", password=" + password + ", linkX = " + link.getPosX() + " - linkY =  " + link.getPosY() + " - linkVie = " + link.getVie() + "]";
	}

	
}
