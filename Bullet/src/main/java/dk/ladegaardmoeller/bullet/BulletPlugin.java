package dk.ladegaardmoeller.bullet;

import dk.ladegaardmoeller.common.data.GameData;
import dk.ladegaardmoeller.common.spi.Plugin;

public class BulletPlugin implements Plugin {
	@Override
	public void start(GameData gameData) {
	
	}
	
	@Override
	public void stop(GameData gameData) {
		for (Bullet bullet : gameData.getEntitiesOfType(Bullet.class)) {
			gameData.removeEntity(bullet);
		}
	}
}