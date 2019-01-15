package game.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import game.entity.Entity;
import game.entity.aggressive.EntityDog;
import game.entity.aggressive.EntityFlameSpawner;
import game.entity.misc.EntityGoal;
import game.init.Tiles;
import game.tile.Tile;
import game.tile.tileentity.TileEntityFire;
import game.world.World;

public class WorldLoader {
	
	public static World load(String mapName) {
    	Gson gson = new Gson();
    	
		try {
            FileInputStream input = new FileInputStream("src/resource/assets/maps/"+ mapName +".json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(input)); 
            JsonObject json = gson.fromJson(reader, JsonObject.class);                    
            
            String name = json.get("mapName").getAsString();
            int mapWidth = json.get("width").getAsInt();
            int mapHeight = json.get("height").getAsInt();
            int spawnX = json.get("spawnX").getAsInt();
            int spawnY = json.get("spawnY").getAsInt();
            int g = json.get("gravitation").getAsInt();
            
            World tempWorld = new World(name, mapWidth, mapHeight, spawnX, spawnY, g);
            
            JsonArray mapJson = json.getAsJsonArray("map");
            Tile[][] map = new Tile[mapWidth][mapHeight];
            
            {
            	int i=0;
            	for (int y=0; y<mapHeight; y++) {
            	 	for (int x=0; x<mapWidth; x++) {
            		 	map[x][y] = Tiles.getTileFromID(mapJson.get(i).getAsInt());
            		 	if (i<mapJson.size()-1) {
            			 	i++;
            		 	}
            	 	}
            	}
            }
            
            tempWorld.setMap(map);
            
            tempWorld.setEntities(loadEntities(mapName));
            
            try {
				input.close();
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
            return tempWorld;
		 } catch (FileNotFoundException e) { 
	            e.printStackTrace();
	            System.exit(1);
	     }
		return null; 
    }
	
	public static ArrayList<Entity> loadEntities(String mapName) {
		Gson gson = new Gson();
		ArrayList<Entity> entities = new ArrayList<Entity>();
		
		try {
			FileInputStream input = new FileInputStream("src/resource/assets/maps/"+ mapName +".json"); 
	        BufferedReader reader = new BufferedReader(new InputStreamReader(input)); 
	        JsonObject json = gson.fromJson(reader, JsonObject.class);                    
	        
			JsonArray jsonEntities = json.get("entities").getAsJsonArray();
			for (int i=0; i<jsonEntities.size(); i++) {
	        	JsonObject jsonObj = jsonEntities.get(i).getAsJsonObject();
	        	Entity entity = null;
	        	
	        	switch(jsonObj.get("type").getAsString()) {
	        	case "dog": entity = new EntityDog(jsonObj.get("posX").getAsInt(), jsonObj.get("posY").getAsInt());
	        	break;
	        	case "fire": entity = new TileEntityFire(jsonObj.get("posX").getAsInt(), jsonObj.get("posY").getAsInt());
        		break;
	        	case "flame_spawner": entity = new EntityFlameSpawner(jsonObj.get("posX").getAsInt(), jsonObj.get("posY").getAsInt());
        		break;
	        	case "goal": entity = new EntityGoal(jsonObj.get("posX").getAsInt(), jsonObj.get("posY").getAsInt());
        		break;
        		default:
        			System.out.println("Can't load entity '" + jsonObj.get("type").getAsString() + "'");
        			System.exit(1);
	        	}
	        	
	        	entities.add(entity);
			}
	            try {
					input.close();
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	            return entities;
			 } catch (FileNotFoundException e) { 
		            e.printStackTrace();
		            System.exit(1);
		     }
		return null;
	}
}
