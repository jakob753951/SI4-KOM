import dk.ladegaardmoeller.common.spi.Plugin;
import dk.ladegaardmoeller.common.spi.Processor;
import dk.ladegaardmoeller.player.PlayerPlugin;
import dk.ladegaardmoeller.player.PlayerProcessor;

module Player {
	requires Common;
	requires com.badlogic.gdx;
	
	provides Plugin with PlayerPlugin;
	provides Processor with PlayerProcessor;
}