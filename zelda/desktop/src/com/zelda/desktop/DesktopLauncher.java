package com.zelda.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zelda.TheLegendOfSopra;

import menu.MenuPrincipal;
import menu.Orchestrator;

public class DesktopLauncher {
	
	static LwjglApplication app;
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//new LwjglApplication(new TheLegendOfSopra(), config);
		app = new LwjglApplication(new Orchestrator(), config);
	}
	
	public static void stopApp()
	{
		app.exit();
	}
}
