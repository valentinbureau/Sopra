package menu;

import java.util.Iterator;
import java.util.Scanner;

import com.zelda.*;

public class Menu {

	public static String saisieString (String message)
	{
		Scanner sc = new Scanner (System.in);
		System.out.println(message);
		return sc.nextLine();
	}

	public static boolean saisieBool (String message)
	{
		Scanner sc = new Scanner (System.in);
		System.out.println(message);
		return sc.nextBoolean();
	}

	public static double saisieDouble (String message)
	{
		Scanner sc = new Scanner (System.in);
		System.out.println(message);
		return sc.nextDouble();
	}

	public static int saisieInt (String message)
	{
		Scanner sc = new Scanner (System.in);
		System.out.println(message);
		return sc.nextInt();
	}

	static CompteUtilisateur userConnected ;
	static Partie part= new Partie();

	public static void main(String[] args) {

		menuPrincipal();

	}
	public static void menuPrincipal()
	{
		System.out.println("Bienvenue dans The Legend of Sopra !");
		System.out.println("Vous souhaitez :");
		int choix;
		if (CompteUtilisateur.getListUtilisateur().isEmpty())
		{
			System.out.println("1 - Créer un nouveau compte");
			System.out.println("2 - Quitter");
			choix = saisieInt("");
			switch (choix)
			{
			case (1) :
				creationCompte();
			break;
			case (2) :
				System.exit(0);
			break;
			default :
				menuPrincipal();
			}
		}
		else
		{
			System.out.println("1 - Se connecter");
			System.out.println("2 - Créer un nouveau compte");
			System.out.println("3 - Quitter");
			choix = saisieInt("");
			switch (choix)
			{
			case (1) :
				seConnecter();
			break;
			case (2) :
				creationCompte();
			break;
			case (3) :
				System.exit(0);
			break;
			default :
				menuPrincipal();
			}
		}
	}

	private static void seConnecter() {
		System.out.println("CONNEXION");
		String login = saisieString("Saisir le login : ");
		String searchLog=null;
		System.out.println(searchLog);
		boolean essai=false;
		String pwd;
		Iterator<String>it=CompteUtilisateur.getListUtilisateur().keySet().iterator();
		while(it.hasNext())
		{
			String key=it.next();
			if (key.equals(login))
			{
				essai=true;
				userConnected=CompteUtilisateur.getListUtilisateur().get(key);
			}
			else
			{
				essai=false;
			}
		}
		if (essai==false)
		{
			System.out.println("Login Inconnu");
			seConnecter();
		}
		do
		{
			pwd = saisieString("Saisir le mot de passe");
			if (!(pwd.equals(userConnected.getPassword())))
			{
				System.out.println("Mot de passe incorrect");
			}
		}while(!(pwd.equals(userConnected.getPassword())));
		menuJoueur();

	}

	private static String nouveauMotDePasse()
	{
		String pwd1;
		String pwd2;
		do
		{
			pwd1 = saisieString ("Saisir le mot de passe");
			pwd2 = saisieString ("Confirmation du mot de passe");
			if (!(pwd1.equals(pwd2)))
			{
				System.out.println("Pas les memes mots de passe");
			}
		}while (!(pwd1.equals(pwd2)));
		return pwd1;
	}

	private static void creationCompte() {
		System.out.println("CREATION D'UN COMPTE");
		String login = saisieString("Saisir le login");
		String pwd=nouveauMotDePasse();
		String mail = saisieString("Saisir l'adresse mail");
		CompteUtilisateur newC= new CompteUtilisateur(login, mail, pwd);
		userConnected=newC;
		menuJoueur();
	}

	private static void choixAvatar() {
		System.out.println("CREATION DE L'AVATAR");
		System.out.println("Saisir l'Avatar voulu");
		System.out.println("1 - Vert");
		System.out.println("2 - Bleu");
		System.out.println("3 - Jaune");
		System.out.println("4 - Orange");
		int choix = saisieInt("");
		userConnected.setIdAvatar(choix);
		System.out.println("Saisir l'Arme voulue");
		System.out.println("1 - Epee");
		System.out.println("2 - Massue");
		choix = saisieInt("");
		userConnected.setIdArme(choix);
		//		for (CompteUtilisateur c : CompteUtilisateur.getListUtilisateur())
		//		{
		//			System.out.println(c);
		//		}
		menuJoueur();
	}

	private static void choixMap() {
		System.out.println("CHOIX DE LA MAP");
		System.out.println("Choisir la map : ");
		System.out.println("1 - Mer");
		System.out.println("2 - Campgagne");
		System.out.println("3 - Mine");
		System.out.println("4 - Montagne");
		int choix = saisieInt("");
		part = new Partie (userConnected, choix);
		menuJoueur();
	}

	private static void verifAvantPartie() {
		System.out.println("RECAP DE LA PARTIE QUI VA COMMENCER");
		System.out.println("Avatar : "+part.getUser().getIdAvatar()+" - Arme : "+part.getUser().getIdArme()+" - Map : "+part.getIdMap());
		String saisie=saisieString("Ca vous convient ? (y/n)");
		if (saisie.equals("y"))
		{
			commencerPartie();
		}
		else
		{
			menuJoueur();
		}
	}

	private static void menuJoueur() {
		System.out.println("MENU DU JOUEUR");
		System.out.println("Data du joueur : " + userConnected);
		System.out.println("Dernière partie jouee : Map - " + part.getIdMap() + " - Avatar : "+userConnected.getIdAvatar()+" - Arme : "+userConnected.getIdArme());
		System.out.println("Que voulez-vous faire ?");
		System.out.println("1 - Modifier map");
		System.out.println("2 - Modifier Avatar");
		System.out.println("3 - Mettre à jour le profil");
		System.out.println("4 - Commencer Partie");
		System.out.println("5 - Se deconnecter");
		int choix = saisieInt("");
		switch (choix)
		{
		case (1):choixMap();break;
		case (2) : choixAvatar();break;
		case (3) : updateProfil();break;
		case (4) : verifAvantPartie();break;
		case (5) : menuPrincipal();break;
		}

	}

	private static void updateProfil() {
		System.out.println("MAJ DU PROFIL");
		String choix ="";
		choix=saisieString("Modifier le login ?(y/n)");
		boolean sim=false;
		if (choix.equals("y"))
		{
			do
			{
				choix=saisieString("Saisir le nouveau login");
				Iterator<String>it=CompteUtilisateur.getListUtilisateur().keySet().iterator();
				while(it.hasNext())
				{
					String key=it.next();
					if (key.equals(choix))
					{
						sim=true;
						System.out.println("Login déja utilise");
					}
					else
					{
						sim = false;
					}
				}
			}while (sim==true);
			userConnected.setLogin(choix);
		}
		choix=saisieString("Modifier le mot de passe ?(y/n)");
		if (choix.equals("y"))
		{
			userConnected.setPassword(nouveauMotDePasse());
		}
		choix=saisieString("Modifier l'adresse mail ?(y/n)");
		if (choix.equals("y"))
		{
			userConnected.setMail(saisieString("Saisir la nouvelle adresse mail"));
		}
		menuJoueur();
	}

	private static void commencerPartie() {
		System.out.println("LET'S GO !");
		System.out.println("Partie finie");
		menuJoueur();
	}
}
