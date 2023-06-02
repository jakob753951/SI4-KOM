package dk.ladegaardmoeller.player;

import dk.ladegaardmoeller.common.data.GameData;
import dk.ladegaardmoeller.common.data.Vector2;
import dk.ladegaardmoeller.common.spi.Plugin;

public class PlayerPlugin implements Plugin {
	@Override
	public void start(GameData gameData) {
		gameData.addEntity(new Player(new Vector2(400, 200), (float) (Math.PI / 2)));
	}
	
	@Override
	public void stop(GameData gameData) {
		for (Player player : gameData.getEntitiesOfType(Player.class)) {
			gameData.removeEntity(player);
		}
	}
}
