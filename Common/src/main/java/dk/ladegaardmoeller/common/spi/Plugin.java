package dk.ladegaardmoeller.common.spi;

import dk.ladegaardmoeller.common.data.GameData;

public interface Plugin {
	void start(GameData gameData);
	void stop(GameData gameData);
}
