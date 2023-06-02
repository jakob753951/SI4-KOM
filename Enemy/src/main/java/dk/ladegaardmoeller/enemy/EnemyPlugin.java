package dk.ladegaardmoeller.enemy;

import dk.ladegaardmoeller.common.data.GameData;
import dk.ladegaardmoeller.common.data.Vector2;
import dk.ladegaardmoeller.common.spi.Plugin;

public class EnemyPlugin implements Plugin {
	@Override
	public void start(GameData gameData) {
		gameData.addEntity(new Enemy(new Vector2(200, 600), 0));
		gameData.addEntity(new Enemy(new Vector2(600, 600), 0));
	}
	
	@Override
	public void stop(GameData gameData) {
		for (Enemy enemy : gameData.getEntitiesOfType(Enemy.class)) {
			gameData.removeEntity(enemy);
		}
	}
}