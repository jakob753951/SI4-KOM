package dk.ladegaardmoeller.common.data;

import java.util.List;

public abstract class Entity {
	private Vector2 position;
	private float rotation;
	private final float radius;
	
	public Entity(Vector2 position, float rotation, float radius) {
		this.position = position;
		this.rotation = rotation;
		this.radius = radius;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	public float getRotation() {
		return rotation;
	}
	
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
	public float getRadius() {
		return radius;
	}
	
	public abstract List<PolarPoint> getPolarPoints();
	
	public abstract void collide(GameData gameData);
}
