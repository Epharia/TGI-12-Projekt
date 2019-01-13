package game.init;

import game.tile.Tile;
import game.tile.TileAir;
import game.tile.TileGrass;
import game.tile.TileGrass2;

/* 
 * |========================================|
 * |this Class is preinitializing every Tile|
 * |========================================|
 */

public class Tiles {
	public static Tile air;
	public static Tile dirt;
	public static Tile grass;
	public static Tile grass_2;
	
	public static void init() {
		air = new TileAir();
		dirt = new Tile("dirt");
		grass = new TileGrass();
		grass_2 = new TileGrass2();
	}
	
	public static Tile getTileFromID(int id){
		switch(id) {
		case 00: return air;
		case 11: return dirt;
		case 12: return grass;
		case 13: return grass_2;
		default: return air;
		}
	}
}
