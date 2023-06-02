import dk.ladegaardmoeller.asteroid.AsteroidPlugin;
import dk.ladegaardmoeller.asteroid.AsteroidProcessor;
import dk.ladegaardmoeller.common.spi.Plugin;
import dk.ladegaardmoeller.common.spi.Processor;

module Asteroid {
	requires Common;
	
	provides Plugin with AsteroidPlugin;
	provides Processor with AsteroidProcessor;
}