package dk.ladegaardmoeller.collision;

import dk.ladegaardmoeller.common.data.Entity;
import dk.ladegaardmoeller.common.data.GameData;
import dk.ladegaardmoeller.common.data.Vector2;
import dk.ladegaardmoeller.common.spi.PostProcessor;

import java.util.List;
import java.util.Set;

public class CollisionPostProcessor implements PostProcessor {
	@Override
	public void postProcess(GameData gameData) {
		Set<Entity> entitySet = gameData.getEntities();
		List<Entity> entities = entitySet.stream().toList();
		
		for (int i = 0; i < entities.size() - 1; i++) {
			for (int j = i + 1; j < entities.size(); j++) {
				Entity a = entities.get(i);
				Entity b = entities.get(j);
				
				if (collides(a, b)) {
					a.collide(gameData);
					b.collide(gameData);
				}
			}
		}
	}
	
	private boolean collides(Entity a, Entity b) {
		float centerDistance = distance(a.getPosition(), b.getPosition());
		float distanceAtEdge = centerDistance - a.getRadius() - b.getRadius();
		return distanceAtEdge < 0;
	}
	
	private float distance(Vector2 a, Vector2 b) {
		float xDiff = Math.abs(a.x() - b.x());
		float yDiff = Math.abs(a.y() - b.y());
		return (float) Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
	}
}