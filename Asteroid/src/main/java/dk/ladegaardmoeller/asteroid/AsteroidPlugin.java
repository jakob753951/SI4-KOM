package dk.ladegaardmoeller.asteroid;

import dk.ladegaardmoeller.common.data.GameData;
import dk.ladegaardmoeller.common.data.Vector2;
import dk.ladegaardmoeller.common.spi.Plugin;

import java.util.Random;

public class AsteroidPlugin implements Plugin {
	Random rng = new Random();
	
	@Override
	public void start(GameData gameData) {
		for (int i = 0; i < 5; i++) {
			gameData.addEntity(createRandomAsteroid(gameData));
		}
	}
	
	private Asteroid createRandomAsteroid(GameData gameData) {
		Vector2 position = new Vector2(rng.nextFloat(gameData.getMapSize().x()), rng.nextFloat(gameData.getMapSize().y()));
		float direction = rng.nextFloat((float) (Math.PI * 2));
		float radius = rng.nextFloat(5, 15);
		float angularVelocity = rng.nextFloat(-1, 1);
		return new Asteroid(position, direction, radius, angularVelocity);
	}
	
	@Override
	public void stop(GameData gameData) {
		for (Asteroid asteroid : gameData.getEntitiesOfType(Asteroid.class)) {
			gameData.removeEntity(asteroid);
		}
	}
}