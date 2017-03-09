package game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Handler;

// Entity: pretty much anything that moves will extend this class

// Efficiency problem in checkEntityCollisions

public abstract class Entity {
	
	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	
	
	public Entity(Handler handler, float x, float y, int width, int height){
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		bounds = new Rectangle(0, 0, width, height);
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public abstract boolean isInteractable();
	public abstract void interact();
	
	// offset = intended move location
	public boolean checkEntityCollisions(float xOffset, float yOffset){
		for(Entity e: handler.getWorld().getEntityManager().getEntities()){
			//since this is looping through all entities and checking them against themselves, make sure to ignore themselves 
			if(e.equals(this))
				continue;
			// otherwise check collision
			//See if rectangles intersect (This is highly inefficient change later on)
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
				if(e.isInteractable()){
					e.interact();
				}
				return true;
			}			
		}
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset){
		return new Rectangle((int)(x + bounds.x + xOffset), (int)(y + bounds.y + yOffset), bounds.width, bounds.height);
	
	}
	
	public float getX() { return x; }
	public void setX(float x) { this.x = x; }
	
	public float getY() { return y; }
	public void setY(float y) { this.y = y; }
	
	public int getWidth() { return width; }
	public void setWidth(int width) { this.width = width; }
	
	public int getHeight() { return height; }
	public void setHeight(int height) { this.height = height; }

	
}
