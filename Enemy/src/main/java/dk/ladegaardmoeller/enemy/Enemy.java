package dk.ladegaardmoeller.enemy;

import dk.ladegaardmoeller.common.data.Entity;
import dk.ladegaardmoeller.common.data.GameData;
import dk.ladegaardmoeller.common.data.PolarPoint;
import dk.ladegaardmoeller.common.data.Vector2;
import dk.ladegaardmoeller.common.data.input.Inputs;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends Entity {
	
	private static final float defaultRadius = 5;
	private Vector2 velocity = new Vector2(0, 0);
	private Inputs inputs = new Inputs();
	
	public Enemy(Vector2 position, float rotation) {
		super(position, rotation, defaultRadius);
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
	
	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}
	
	public Inputs getInputs() {
		return inputs;
	}
	
	public void setInputs(Inputs inputs) {
		this.inputs = inputs;
	}
	
	@Override
	public List<PolarPoint> getPolarPoints() {
		List<PolarPoint> points = new ArrayList<>();
		
		points.add(new PolarPoint(0, 5));
		points.add(new PolarPoint((float) (Math.PI - Math.PI / 4), 5));
		points.add(new PolarPoint((float) Math.PI, 2));
		points.add(new PolarPoint((float) (Math.PI + Math.PI / 4), 5));
		
		return points;
	}
	
	@Override
	public void collide(GameData gameData) {
		gameData.removeEntity(this);
	}
}
