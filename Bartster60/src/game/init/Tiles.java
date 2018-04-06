package game.init;

import game.tile.Tile;
import game.tile.TileAir;

public class Tiles {
	public static Tile dirt;
	public static Tile air;
	
	public static void init() {
		air = new TileAir();
		dirt = new Tile("dirt");
	}
}
