package menu;

import com.badlogic.gdx.Game;

public class Orchestrator extends Game{
	
	private MenuPrincipal menuPrincipal;
	private MenuCreationCompte menuCreation;
	private MenuInfosJoueur menuInfos;
	private MenuConnexion menuConnexion;
	private MenuModifAvatar menuAvatar;
	private MenuModifMap menuMap;
	private MenuModifProfil menuProfil;
	private MenuRecapPartie menuPartie;
	
	public final static int PRINCIPAL = 0;
	public final static int CREATION = 1;
	public final static int INFOS = 2;
	public final static int CONNEXION = 3;
	public final static int AVATAR = 4;
	public final static int MAP = 5;
	public final static int PROFIL = 6;
	public final static int PARTIE = 7;
	
	

	@Override
	public void create() {
		menuPrincipal = new MenuPrincipal(this);
		setScreen(menuPrincipal);

	}

	public void changeScreen (int screen)
	{
		switch (screen) {
		case PRINCIPAL :
			if (menuPrincipal == null)
			{
				menuPrincipal = new MenuPrincipal(this);
			}
			this.setScreen(menuPrincipal);
			break;
		
		case CREATION :
			if (menuCreation == null)
			{
				menuCreation = new MenuCreationCompte(this);
			}
			this.setScreen(menuCreation);
			break;
		
		case INFOS :
			if (menuInfos == null)
			{
				menuInfos = new MenuInfosJoueur(this);
			}
			this.setScreen(menuInfos);
			break;
			
		case CONNEXION : 
			if (menuConnexion == null)
			{
				menuConnexion = new MenuConnexion(this);
			}
			this.setScreen(menuConnexion);
			break;
			
		case AVATAR : 
			if (menuAvatar == null)
			{
				menuAvatar = new MenuModifAvatar(this);
			}
			this.setScreen(menuAvatar);
			break;
			
		case MAP : 
			if (menuMap == null)
			{
				menuMap = new MenuModifMap(this);
			}
			this.setScreen(menuMap);
			break;
			
		case PROFIL : 
			if (menuProfil == null)
			{
				menuProfil = new MenuModifProfil(this);
			}
			this.setScreen(menuProfil);
			break;
			
		case PARTIE : 
			if (menuPartie == null)
			{
				menuPartie = new MenuRecapPartie(this);
			}
			this.setScreen(menuPartie);
			break;
		}
	}

}
