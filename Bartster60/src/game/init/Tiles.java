package game.init;

import game.tile.Tile;
import game.tile.TileAir;

/* 
 * |========================================|
 * |this Class is preinitializing every Tile|
 * |========================================|
 */

public class Tiles {
	public static Tile air;
	public static Tile dirt;
	public static Tile grass;
	public static Tile grass_top_0;
	public static Tile grass_top_1;
	public static Tile grass_top_2;
	public static Tile grass_right;
	public static Tile grass_right_corner;
	public static Tile grass_right_corner_inner;
	public static Tile grass_left;
	public static Tile grass_left_corner;
	public static Tile grass_left_corner_inner;
	
	public static void init() {
		air = new TileAir();
		dirt = new Tile("dirt");
		grass = new Tile("grass");
		grass_top_0 = new Tile("grass_top_0", false);
		grass_top_1 = new Tile("grass_top_1", false);
		grass_top_2 = new Tile("grass_top_2", false);
		grass_right = new Tile("grass_right");
		grass_right_corner = new Tile("grass_right_corner");
		grass_right_corner_inner = new Tile("grass_right_corner_inner");
		grass_left = new Tile("grass_left");
		grass_left_corner = new Tile("grass_left_corner");
		grass_left_corner_inner = new Tile("grass_left_corner_inner");
	}
	
	public static Tile getTileFromID(int id){
		switch(id) {
		case 00: return air;
		case 11: return dirt;
		case 12: return grass;
		case 13: return (Math.random()<0.7) ? grass_top_0 : grass_top_1;		
		case 14: return grass_right;
		case 15: return grass_right_corner;
		case 16: return grass_right_corner_inner;
		case 17: return grass_left;
		case 18: return grass_left_corner;
		case 19: return grass_left_corner_inner;
		default: return air;
		}
	}
}
