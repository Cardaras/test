package game.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Game;

public class Tile {
 
	public static Tile[] tiles = new Tile[256];// amount of tiles that i have
	public static Tile grassTile = new GrassTile(0);
	public static Tile dirtTile = new DirtTile(1);
	public static Tile rockTile = new RockTile(2);
	
	public static final int TILE_WIDTH = Game.width/16 / 2,  TILE_HEIGHT = Game.height/9 / 2;
	
	protected BufferedImage texture;
	protected final int id;
	
	
	public Tile(BufferedImage texture, int id){
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g, int x, int y){
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public int getId(){ return id; }

}
