package game.tile;

public class TilePlatform extends Tile {
	public TilePlatform() {
		super("platform");
		this.AABB.setBounds(0, 0, 1, 0.4375);
	}
}
