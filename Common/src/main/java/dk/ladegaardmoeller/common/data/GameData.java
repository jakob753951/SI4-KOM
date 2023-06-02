package dk.ladegaardmoeller.common.data;

import java.util.HashSet;
import java.util.Set;

public class GameData {
	private final Set<Entity> entities = new HashSet<>();
	private final Vector2 mapSize = new Vector2(800, 800);
	
	public Set<Entity> getEntities() {
		return entities;
	}
	
	public <T> Set<T> getEntitiesOfType(Class<T> targetType) {
		Set<T> entitiesOfType = new HashSet<>();
		for (Entity entity : getEntities()) {
			if (targetType.isInstance(entity)) {
				entitiesOfType.add(targetType.cast(entity));
			}
		}
		return entitiesOfType;
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}
	
	public Vector2 getMapSize() {
		return mapSize;
	}
}
