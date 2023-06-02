import dk.ladegaardmoeller.common.spi.Plugin;
import dk.ladegaardmoeller.common.spi.Processor;
import dk.ladegaardmoeller.enemy.EnemyPlugin;
import dk.ladegaardmoeller.enemy.EnemyProcessor;

module Enemy {
	requires Common;
	
	provides Plugin with EnemyPlugin;
	provides Processor with EnemyProcessor;
}