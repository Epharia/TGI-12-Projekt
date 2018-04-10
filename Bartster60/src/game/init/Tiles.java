package game.init;

import game.tile.Tile;
import game.tile.TileAir;

public class Tiles {
	public static Tile air;
	public static Tile dirt;
	public static Tile grass;
	public static Tile grass_2;
	
	public static void init() {
		air = new TileAir();
		dirt = new Tile("dirt");
		grass = new Tile("grass");
		grass_2 = new Tile("grass_2");
	}
}
