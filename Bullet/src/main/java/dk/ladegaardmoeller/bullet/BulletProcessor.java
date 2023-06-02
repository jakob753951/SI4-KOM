package dk.ladegaardmoeller.bullet;

import dk.ladegaardmoeller.common.data.GameData;
import dk.ladegaardmoeller.common.data.Vector2;
import dk.ladegaardmoeller.common.spi.Processor;

public class BulletProcessor implements Processor {
	@Override
	public void process(GameData gameData) {
		for (Bullet bullet : gameData.getEntitiesOfType(Bullet.class)) {
			processPosition(bullet);
		}
	}
	
	private void processPosition(Bullet bullet) {
		bullet.setPosition(new Vector2(bullet.getPosition().x() + bullet.getVelocity().x(), bullet.getPosition().y() + bullet.getVelocity().y()));
	}
}
