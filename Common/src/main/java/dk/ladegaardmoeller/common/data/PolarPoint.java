package dk.ladegaardmoeller.common.data;

public record PolarPoint(float rotation, float magnitude) {
	public float getX() {
		return (float) (Math.cos(rotation) * magnitude);
	}
	public float getY() {
		return (float) (Math.sin(rotation) * magnitude);
	}
}
