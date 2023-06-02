package dk.ladegaardmoeller.enemy;

import dk.ladegaardmoeller.common.data.GameData;
import dk.ladegaardmoeller.common.data.Vector2;
import dk.ladegaardmoeller.common.data.input.Inputs;
import dk.ladegaardmoeller.common.data.input.Turning;
import dk.ladegaardmoeller.common.spi.Processor;
import dk.ladegaardmoeller.common.spi.Spawner;
import dk.ladegaardmoeller.common.utils.SPILocator;

import java.util.List;
import java.util.Random;

public class EnemyProcessor implements Processor {
	Random rng = new Random();
	
	@Override
	public void process(GameData gameData) {
		for (Enemy enemy : gameData.getEntitiesOfType(Enemy.class)) {
			updateEnemy(gameData, enemy);
		}
	}
	
	private void updateEnemy(GameData gameData, Enemy enemy) {
		// movement
		procesMovement(enemy);
		
		// shooting
		processShooting(gameData, enemy);
	}
	
	private void procesMovement(Enemy enemy) {
		processInput(enemy);
		processRotation(enemy);
		processVelocity(enemy);
		processPosition(enemy);
	}
	
	private void processInput(Enemy enemy) {
		enemy.setInputs(getRandomInputs());
	}
	
	private Inputs getRandomInputs() {
		Inputs inputs = new Inputs();
		inputs.shoot = rng.nextInt(100) < 2;
		inputs.forwards = rng.nextInt(100) < 10;
		if (rng.nextInt(100) < 50) {
			inputs.turning = null;
		} else {
			if (rng.nextBoolean()) {
				inputs.turning = Turning.Left;
			} else {
				inputs.turning = Turning.Right;
			}
		}
		return inputs;
	}
	
	private void processRotation(Enemy enemy) {
		Inputs inputs = enemy.getInputs();
		if (inputs.turning == null) {
			return;
		}
		
		float turningSpeed = 0.05f;
		switch (inputs.turning) {
			case Left -> enemy.setRotation(enemy.getRotation() + turningSpeed);
			case Right -> enemy.setRotation(enemy.getRotation() - turningSpeed);
		}
	}
	
	private void processVelocity(Enemy enemy) {
		Inputs inputs = enemy.getInputs();
		if (!inputs.forwards) {
			return;
		}
		
		float thrustSpeed = 0.1f;
		enemy.setVelocity(new Vector2(
			enemy.getVelocity().x() + ((float) Math.cos(enemy.getRotation())) * thrustSpeed,
			enemy.getVelocity().y() + ((float) Math.sin(enemy.getRotation())) * thrustSpeed
		));
	}
	
	private void processPosition(Enemy enemy) {
		enemy.setPosition(new Vector2(
			enemy.getPosition().x() + enemy.getVelocity().x(),
			enemy.getPosition().y() + enemy.getVelocity().y()
		));
	}
	
	private void processShooting(GameData gameData, Enemy enemy) {
		Inputs inputs = enemy.getInputs();
		if (!inputs.shoot) {
			return;
		}
		
		List<Spawner> spawners = SPILocator.locateAll(Spawner.class);
		
		float barrelLength = 11;
		Vector2 spawnPosition = new Vector2(
			(float) (enemy.getPosition().x() + Math.cos(enemy.getRotation()) * barrelLength),
			(float) (enemy.getPosition().y() + Math.sin(enemy.getRotation()) * barrelLength)
		);
		
		spawners.forEach(spawner -> spawner.spawn(gameData, spawnPosition, enemy.getRotation()));
	}
}
