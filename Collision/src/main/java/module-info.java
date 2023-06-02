import dk.ladegaardmoeller.collision.CollisionPostProcessor;
import dk.ladegaardmoeller.common.spi.PostProcessor;

module Collision {
	requires Common;
	
	provides PostProcessor with CollisionPostProcessor;
}