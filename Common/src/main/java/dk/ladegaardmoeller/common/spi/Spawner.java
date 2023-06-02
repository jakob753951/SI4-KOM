package dk.ladegaardmoeller.common.spi;

import dk.ladegaardmoeller.common.data.GameData;
import dk.ladegaardmoeller.common.data.Vector2;

public interface Spawner {
	void spawn(GameData gameData, Vector2 position, float rotation);
}
