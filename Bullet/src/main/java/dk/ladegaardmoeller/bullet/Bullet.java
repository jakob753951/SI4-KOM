package dk.ladegaardmoeller.bullet;

import dk.ladegaardmoeller.common.data.Entity;
import dk.ladegaardmoeller.common.data.GameData;
import dk.ladegaardmoeller.common.data.PolarPoint;
import dk.ladegaardmoeller.common.data.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Bullet extends Entity {
	private static final float bulletSpeed = 2;
	private static final float bulletRadius = 5;
	private final Vector2 velocity = new Vector2((float) Math.cos(bulletSpeed), (float) Math.sin(bulletSpeed));
	
	public Bullet(Vector2 position, float rotation) {
		super(position, rotation, bulletRadius);
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
	
	@Override
	public List<PolarPoint> getPolarPoints() {
		List<PolarPoint> points = new ArrayList<>();
		
		points.add(new PolarPoint(0, 5));
		points.add(new PolarPoint((float) Math.PI, 5));
		
		return points;
	}
	
	@Override
	public void collide(GameData gameData) {
		gameData.removeEntity(this);
	}
}
