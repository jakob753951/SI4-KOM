package dk.ladegaardmoeller.asteroid;

import dk.ladegaardmoeller.common.data.GameData;
import dk.ladegaardmoeller.common.data.Vector2;
import dk.ladegaardmoeller.common.spi.Processor;

public class AsteroidProcessor implements Processor {
	
	@Override
	public void process(GameData gameData) {
		for (Asteroid asteroid : gameData.getEntitiesOfType(Asteroid.class)) {
			processRotation(asteroid);
			processPosition(asteroid);
		}
	}
	
	private void processRotation(Asteroid asteroid) {
		asteroid.setRotation(asteroid.getRotation() + asteroid.getAngularVelocity());
	}
	
	private void processPosition(Asteroid asteroid) {
		asteroid.setPosition(new Vector2(asteroid.getPosition().x() + asteroid.getVelocity().x(), asteroid.getPosition().y() + asteroid.getVelocity().y()));
	}
}
