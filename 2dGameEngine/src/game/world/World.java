package game.world;

import java.awt.Graphics;

import game.Handler;
import game.entities.EntityManager;
import game.entities.creatures.Player;
import game.entities.statics.Terrain;
import game.gfx.GUIManager;
import game.tiles.Tile;
import game.utils.Utils;

// TODO: remove world.txt spawn location functionality
// TODO: only render assets the player can see
public class World {
	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	
	
	//Entities
	private EntityManager entityManager;
	
	private Terrain terrain;
	
	private GUIManager guiManager;

	public World(Handler handler, String worldPath, String terrainPath){
		
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 0, 0));
		
		terrain = new Terrain(entityManager, handler, terrainPath);
		
		loadWorld(worldPath);
		
		// reads the second line of world.txt to find player spawn location.
		entityManager.getPlayer().setX(spawnX * handler.getWidth() / 16);
		entityManager.getPlayer().setY(spawnY * handler.getHeight() / 9);
		
		
		guiManager = new GUIManager(handler);
	}
	
	public void tick(){
		entityManager.tick();
	}
	
	public void render(Graphics g){
		
		// only render tiles that the player can see
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
		int yEnd = (int) Math.min(width, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);
		
		for(int y = yStart; y < yEnd; y++){
			for(int x = xStart; x < xEnd; x++){
				getTile(x,y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()), 
									   (int) (y * Tile.TILE_WIDTH - handler.getGameCamera().getyOffset()));
			}
		}
		
		//Entities
		entityManager.render(g);
		
		guiManager.render(g);
	}
	
	public Tile getTile(int x, int y){
		
		if(x < 0 || y < 0 || x >= width || y >= height){
			return Tile.grassTile;
		}
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null) return Tile.dirtTile;
		return t;
	}
	

	
	private void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");//splits every number it reads
		
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

}
