package game.entities.creatures;
import game.Game;
import game.Handler;
import game.entities.Entity;
import game.tiles.Tile;

// All players and NPCs(maybe add NPCs) will extend this

// TODO: Fix arbitrary 10 & -10 values for entity collision

public abstract class Creature extends Entity{
	
	// Default stats
	
	public static final int DEFAULT_HEALTH = 10;
	public static int DEFAULT_CREATURE_WIDTH,// = Game.width / 16,
					   DEFAULT_CREATURE_HEIGHT;// = Game.height / 9;
	
	public static int TILE_LENGTH ;
	public static float DEFAULT_SPEED ;
	
	protected int health;
	protected float speed;
	protected float xMove, yMove;
	
	
	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		
		DEFAULT_CREATURE_WIDTH = handler.getWidth() / 16;
		DEFAULT_CREATURE_HEIGHT = handler.getHeight() / 9;
		
		TILE_LENGTH = handler.getWidth() / 16 / 2;
		DEFAULT_SPEED = TILE_LENGTH / 10;
		
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	public boolean isTargetSolid(String dir, int x, int y){
		
		//!checkEntityCollisions(0f, yMove)
		if(dir.equals("up")){
			// Tile y pos (min = 0, max = check world.txt 2nd line) = [ (current yPos in px) + (move distance in px) +- (collision box parameters) ] / Tile height;
			int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;
			// check Upper Left && right side of player collision box (Tiles only)
			if(!collisionWithTile((int) (x + bounds.x)/ Tile.TILE_WIDTH, ty) && 
			   !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty) &&
			   // 10 is an arbitrary value for the player move amount (Entities and Assets only)
			   !checkEntityCollisions(0,-TILE_LENGTH/5)){
				return false;
			}
		}else if(dir.equals("down")){
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
			if(!collisionWithTile((int) (x + bounds.x)/ Tile.TILE_WIDTH, ty) && 
			   !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty) &&
			   !checkEntityCollisions(0,TILE_LENGTH/5)){
				return false;
			}
		}else if(dir.equals("left")){
			int tx = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;
			if(!collisionWithTile(tx, (int) (y + bounds.y)/ Tile.TILE_HEIGHT) && 
			   !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT) &&
			   !checkEntityCollisions(-TILE_LENGTH/5,0)){
				return false;
			}
		}else if(dir.equals("right")){
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
			if(!collisionWithTile(tx, (int) (y + bounds.y)/ Tile.TILE_HEIGHT) && 
			   !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT) &&
			   !checkEntityCollisions(TILE_LENGTH/5, 0)){
				return false;
			}
		} 
		
		
		return true;
	}
	
	protected void snapPosition(){
		x = (float)(Math.round(x / TILE_LENGTH) * TILE_LENGTH);
		y = (float)(Math.round(y / TILE_LENGTH) * TILE_LENGTH);
	}
	
	public void move(){
		//if(!checkEntityCollisions(xMove, 0f))
			moveX();
		//if(!checkEntityCollisions(0f, yMove))
			moveY();
	}
	
	public void moveX(){
		x += xMove;
	}
	public void moveY(){ 
		y += yMove;
	}
	
	protected boolean collisionWithTile(int x, int y){
		return handler.getWorld().getTile(x, y).isSolid();
	}

	public float getxMove() { return xMove; }
	public void setxMove(float xMove) { this.xMove = xMove; }

	public float getyMove() { return yMove; }
	public void setyMove(float yMove) { this.yMove = yMove; }

	public int getHealth() { return health; }
	public void setHealth(int health) { this.health = health; }

	public float getSpeed() { return speed; }
	public void setSpeed(float speed) { this.speed = speed; }
	
	
}
