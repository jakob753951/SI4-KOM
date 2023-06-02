package dk.ladegaardmoeller.core;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setResizable(true);
		config.setTitle("Asteroids");
		config.setWindowedMode(800, 800);
		new Lwjgl3Application(new GameEngine(), config);
	}
}