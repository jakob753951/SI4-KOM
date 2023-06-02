import dk.ladegaardmoeller.bullet.BulletPlugin;
import dk.ladegaardmoeller.bullet.BulletProcessor;
import dk.ladegaardmoeller.bullet.BulletSpawner;
import dk.ladegaardmoeller.common.spi.Plugin;
import dk.ladegaardmoeller.common.spi.Processor;
import dk.ladegaardmoeller.common.spi.Spawner;

module Bullet {
	requires Common;
	
	provides Plugin with BulletPlugin;
	provides Processor with BulletProcessor;
	provides Spawner with BulletSpawner;
}