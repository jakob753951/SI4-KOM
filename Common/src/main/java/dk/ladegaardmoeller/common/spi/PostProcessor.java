package dk.ladegaardmoeller.common.spi;

import dk.ladegaardmoeller.common.data.GameData;

public interface PostProcessor
{
	void postProcess(GameData gameData);
}
