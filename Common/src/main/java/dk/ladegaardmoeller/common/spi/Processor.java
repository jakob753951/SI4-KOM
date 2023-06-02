package dk.ladegaardmoeller.common.spi;

import dk.ladegaardmoeller.common.data.GameData;

public interface Processor {
	void process(GameData gameData);
}
