package game.init;

import game.tile.Tile;

public class Tiles {
	public static Tile dirt;
	
	public static void init() {
		dirt = new Tile("dirt");
	}
}
