package game.entities;

// Dedicated to updating and rendering all Entities && adding and deleting Entities

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import game.Handler;
import game.entities.creatures.Player;
import game.tiles.Tile;

public class EntityManager {
	
	private Player player;
	private Handler handler;
	private ArrayList<Entity> entities;	
	
	private Entity uniqueEntities[];	

 	private Comparator<Entity> renderSort = new Comparator<Entity>(){

		@Override
		public int compare(Entity a, Entity b) {
			// compares the bottom y coordinates against each other
			// whichever entity is higher is rendered first.
			if(a.getY() + a.getHeight() < b.getY() + b.getHeight())
				return -1;
			return 1;
			
		}
		
	};
	
	public EntityManager(Handler handler, Player player){
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		uniqueEntities = new Entity[100];
		uniqueEntities[0] = player;
		entities.add(player);
	}
	

	public void tick(){
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
		int xEnd = (int) Math.min(60, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
		int yEnd = (int) Math.min(60, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);
		
		for(int i = 0; i < entities.size(); i++){
			Entity e = entities.get(i);
			int eX = (int)(e.getX() / Tile.TILE_WIDTH);
			int eY = (int)(e.getY() / Tile.TILE_HEIGHT);
			int xOffset = (int)(e.getWidth() / Tile.TILE_WIDTH);
			int yOffset = (int)(e.getHeight() / Tile.TILE_HEIGHT);
			if(e == player || (eX > xStart - xOffset && eX < xEnd + xOffset && eY > yStart - yOffset && eY < yEnd)){
				e.tick();
			}
		}
		// re-sort the entities.
		entities.sort(renderSort);
	}
	public void render(Graphics g){
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
		int xEnd = (int) Math.min(60, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
		int yEnd = (int) Math.min(60, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);
		
		for(int i = 0; i < entities.size(); i++){
			Entity e = entities.get(i);
			int eX = (int)(e.getX() / Tile.TILE_WIDTH);
			int eY = (int)(e.getY() / Tile.TILE_HEIGHT);
			int xOffset = (int)(e.getWidth() / Tile.TILE_WIDTH);
			int yOffset = (int)(e.getHeight() / Tile.TILE_HEIGHT);
			if(e == player || (eX > xStart - xOffset && eX < xEnd + xOffset && eY > yStart - yOffset && eY < yEnd)){
				e.render(g);
			}
		}
	}
	
	public void addEntity(Entity e){
		entities.add(e);
		if(uniqueEntities[e.getID()] == null){
			uniqueEntities[e.getID()] = e;
		}
	}
	
	//Getters & Setters

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	public Entity getEntity(int id){
		return uniqueEntities[id];
	}
}
