package dk.ladegaardmoeller.map;

import dk.ladegaardmoeller.common.data.Entity;
import dk.ladegaardmoeller.common.data.GameData;
import dk.ladegaardmoeller.common.data.Vector2;
import dk.ladegaardmoeller.common.spi.PostProcessor;

public class MapPostProcessor implements PostProcessor {
	@Override
	public void postProcess(GameData gameData) {
		for (Entity entity : gameData.getEntities()) {
			wrapEntity(entity, gameData.getMapSize());
		}
	}
	
	private void wrapEntity(Entity entity, Vector2 mapSize) {
		entity.setPosition(new Vector2(
			floorMod(entity.getPosition().x(), mapSize.x()),
			floorMod(entity.getPosition().y(), mapSize.y())
		));
	}
	
	/**
	 * This method only exists because Java doesn't
	 * have a built-in *ACTUAL* mod function that works with
	 * floats and floor-division...
	 *
	 * @param dividend the dividend for the division
	 * @param divisor the divisor for the division
	 * @return the floor-modulo result
	 */
	private float floorMod(float dividend, float divisor) {
		return (float) (dividend - Math.floor(dividend / divisor) * divisor);
	}
}
