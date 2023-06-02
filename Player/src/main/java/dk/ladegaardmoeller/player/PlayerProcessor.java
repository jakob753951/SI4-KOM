package dk.ladegaardmoeller.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import dk.ladegaardmoeller.common.data.GameData;
import dk.ladegaardmoeller.common.data.Vector2;
import dk.ladegaardmoeller.common.data.input.Inputs;
import dk.ladegaardmoeller.common.data.input.Turning;
import dk.ladegaardmoeller.common.spi.Processor;
import dk.ladegaardmoeller.common.spi.Spawner;
import dk.ladegaardmoeller.common.utils.SPILocator;

import java.util.List;

public class PlayerProcessor implements Processor {
	@Override
	public void process(GameData gameData) {
		for (Player player : gameData.getEntitiesOfType(Player.class)) {
			updatePlayer(gameData, player);
		}
	}
	
	private void updatePlayer(GameData gameData, Player player) {
		// movement
		procesMovement(player);
		
		// shooting
		processShooting(gameData, player);
	}
	
	private void procesMovement(Player player) {
		processInput(player);
		processRotation(player);
		processVelocity(player);
		processPosition(player);
	}
	
	private void processInput(Player player) {
		player.setInputs(getKeyboardInputs());
	}
	
	private Inputs getKeyboardInputs() {
		Inputs inputs = new Inputs();
		inputs.shoot = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
		inputs.forwards = Gdx.input.isKeyPressed(Input.Keys.W);
		
		if (Gdx.input.isKeyPressed(Input.Keys.A) == Gdx.input.isKeyPressed(Input.Keys.D)) {
			inputs.turning = null;
		} else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			inputs.turning = Turning.Right;
		} else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			inputs.turning = Turning.Left;
		}
		
		return inputs;
	}
	
	private void processRotation(Player player) {
		Inputs inputs = player.getInputs();
		if (inputs.turning == null) {
			return;
		}
		
		float turningSpeed = 0.05f;
		switch (inputs.turning) {
			case Left -> player.setRotation(player.getRotation() + turningSpeed);
			case Right -> player.setRotation(player.getRotation() - turningSpeed);
		}
	}
	
	private void processVelocity(Player player) {
		Inputs inputs = player.getInputs();
		if (!inputs.forwards) {
			return;
		}
		
		float thrustSpeed = 0.1f;
		player.setVelocity(new Vector2(
			player.getVelocity().x() + ((float) Math.cos(player.getRotation())) * thrustSpeed,
			player.getVelocity().y() + ((float) Math.sin(player.getRotation())) * thrustSpeed
		));
	}
	
	private void processPosition(Player player) {
		player.setPosition(new Vector2(
			player.getPosition().x() + player.getVelocity().x(),
			player.getPosition().y() + player.getVelocity().y()
		));
	}
	
	private void processShooting(GameData gameData, Player player) {
		Inputs inputs = player.getInputs();
		if (!inputs.shoot) {
			return;
		}
		
		List<Spawner> spawners = SPILocator.locateAll(Spawner.class);
		
		float barrelLength = 11;
		Vector2 spawnPosition = new Vector2(
			(float) (player.getPosition().x() + Math.cos(player.getRotation()) * barrelLength),
			(float) (player.getPosition().y() + Math.sin(player.getRotation()) * barrelLength)
		);
		
		spawners.forEach(spawner -> spawner.spawn(gameData, spawnPosition, player.getRotation()));
	}
}
