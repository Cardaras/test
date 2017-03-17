package game.entities.statics;

import game.Handler;
import game.entities.Entity;
import game.entities.EntityManager;
import game.utils.Utils;

public class Terrain {
	
	public static int TILE_WIDTH,// = Game.width / 16 / 2,
					TILE_HEIGHT;// = Game.height / 9 / 2;
	
	private Handler handler;

	
	public Terrain(EntityManager entityManager, Handler handler, String terrainPath){
		String file = Utils.loadFileAsString(terrainPath);
		String[] tokens = file.split("\\s+");//splits every number it reads
		this.handler = handler;
		int width = Utils.parseInt(tokens[0]);
		int height = Utils.parseInt(tokens[1]);
		
		TILE_WIDTH = handler.getWidth() / 16 / 2;
		TILE_HEIGHT = handler.getHeight() / 9 / 2;
		
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				Entity e = getEntity(Utils.parseInt(tokens[(x + y * width) + 2]), x, y);
				if(e != null)
				entityManager.addEntity(e);
			}
		}
	}
	
	private Entity getEntity(int id, int x, int y){
		Entity e = null;
		switch(id){
		
		case 0:
			e = null;
			break;
			
		case 1:
			e = new PineTree(handler, x*TILE_WIDTH, y*TILE_HEIGHT, 1);
			break;
			
		case 2:
			e = new DeadTree(handler, x*TILE_WIDTH, y*TILE_HEIGHT, 2);
			break;
			
		case 3:
			e = new FilledSign(handler, x*TILE_WIDTH, y*TILE_HEIGHT, 3);
			break;
			
		case 4:
			e = new EmptySign(handler, x*TILE_WIDTH, y*TILE_HEIGHT, 4);
			break;
			
		case 5:
			e = new StrawHouse(handler, x*TILE_WIDTH, y*TILE_HEIGHT, 5);
			break;
			
		default:
			e = null;
			break;
		
		
		}
		return e;
	}
}
