import dk.ladegaardmoeller.common.spi.PostProcessor;
import dk.ladegaardmoeller.map.MapPostProcessor;

module Map {
	requires Common;
	
	provides PostProcessor with MapPostProcessor;
}