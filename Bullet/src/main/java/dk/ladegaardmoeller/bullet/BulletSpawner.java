package dk.ladegaardmoeller.bullet;

import dk.ladegaardmoeller.common.data.GameData;
import dk.ladegaardmoeller.common.data.Vector2;
import dk.ladegaardmoeller.common.spi.Spawner;

public class BulletSpawner implements Spawner {
	@Override
	public void spawn(GameData gameData, Vector2 position, float rotation) {
		gameData.addEntity(new Bullet(position, rotation));
	}
}
