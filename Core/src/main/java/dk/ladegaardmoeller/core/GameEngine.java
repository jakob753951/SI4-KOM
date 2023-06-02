package dk.ladegaardmoeller.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.ladegaardmoeller.common.data.Entity;
import dk.ladegaardmoeller.common.data.GameData;
import dk.ladegaardmoeller.common.data.PolarPoint;
import dk.ladegaardmoeller.common.spi.Plugin;
import dk.ladegaardmoeller.common.spi.PostProcessor;
import dk.ladegaardmoeller.common.spi.Processor;
import dk.ladegaardmoeller.common.utils.SPILocator;

import java.util.List;

public class GameEngine extends ApplicationAdapter {
	private GameData gameData;
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	
	@Override
	public void create() {
		gameData = new GameData();
		List<Plugin> plugins = SPILocator.locateAll(Plugin.class);
		for (Plugin plugin : plugins) {
			plugin.start(gameData);
		}
		
		camera = new OrthographicCamera();
		camera.update();
		camera.setToOrtho(false, 100, 100);
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shapeRenderer = new ShapeRenderer();
		
		List<Processor> processors = SPILocator.locateAll(Processor.class);
		for (Processor processor : processors) {
			processor.process(gameData);
		}
		
		this.camera.update();
		
		for (Entity entity : gameData.getEntities()) {
			draw(entity);
		}
		
		List<PostProcessor> postProcessors = SPILocator.locateAll(PostProcessor.class);
		for (PostProcessor postProcessor : postProcessors) {
			postProcessor.postProcess(gameData);
		}
	}
	
	private void draw(Entity entity) {
		List<PolarPoint> polarPoints = entity.getPolarPoints();
		float[] floatPoints = new float[polarPoints.size() * 2 + 2];
		for (int i = 0; i < polarPoints.size(); i++) {
			PolarPoint polarPoint = new PolarPoint(
				polarPoints.get(i).rotation() + entity.getRotation(),
				polarPoints.get(i).magnitude()
			);
			floatPoints[i * 2] = polarPoint.getX() + entity.getPosition().x();
			floatPoints[i * 2 + 1] = polarPoint.getY() + entity.getPosition().y();
		}
		
		
		PolarPoint polarPoint = new PolarPoint(
			polarPoints.get(0).rotation() + entity.getRotation(),
			polarPoints.get(0).magnitude()
		);
		floatPoints[polarPoints.size() * 2] = polarPoint.getX() + entity.getPosition().x();
		floatPoints[polarPoints.size() * 2 + 1] = polarPoint.getY() + entity.getPosition().y();
		
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.polyline(floatPoints);
		shapeRenderer.end();
	}
}
