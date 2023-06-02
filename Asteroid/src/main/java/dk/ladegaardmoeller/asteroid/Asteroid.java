package dk.ladegaardmoeller.asteroid;

import dk.ladegaardmoeller.common.data.Entity;
import dk.ladegaardmoeller.common.data.GameData;
import dk.ladegaardmoeller.common.data.PolarPoint;
import dk.ladegaardmoeller.common.data.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Asteroid extends Entity {
	private final Vector2 velocity = new Vector2(0, 0);
	private final float angularVelocity;
	Random rng = new Random();
	
	public Asteroid(Vector2 position, float rotation, float radius, float angularVelocity) {
		super(position, rotation, radius);
		this.angularVelocity = angularVelocity;
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
	
	public float getAngularVelocity() {
		return angularVelocity;
	}
	
	@Override
	public List<PolarPoint> getPolarPoints() {
		List<PolarPoint> points = new ArrayList<>();
		
		int pointCount = 8;
		for (int i = 0; i < pointCount; i++) {
			points.add(new PolarPoint(
				(float) (i*((Math.PI*2)/8)),
				rng.nextFloat(getRadius()-2, getRadius()+2)
			));
		}
		return points;
	}
	
	@Override
	public void collide(GameData gameData) {
		if (this.getRadius() < 3) {
			gameData.removeEntity(this);
			return;
		}
		float newRadius = this.getRadius() / 2;
		gameData.addEntity(new Asteroid(new Vector2(this.getPosition().x() - newRadius, this.getPosition().y()), 0, newRadius, this.angularVelocity - 0.05f));
		gameData.addEntity(new Asteroid(new Vector2(this.getPosition().x() + newRadius, this.getPosition().y()), (float) Math.PI, newRadius, this.angularVelocity + 0.05f));
		gameData.removeEntity(this);
	}
}
