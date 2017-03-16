package game.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Handler;
import game.gfx.Animation;
import game.gfx.Assets;

public class Player extends Creature{
	
	//Animations
	private Animation animationUp;
	private Animation animationDown;
	private Animation animationLeft;
	private Animation animationRight;
	
	private Animation idolAnimationUp;
	private Animation idolAnimationDown;
	private Animation idolAnimationLeft;
	private Animation idolAnimationRight;
	
	private int moveDistance;
	private int targetX, targetY;
	private int threshold;
	private boolean isMoving;
	private boolean up, down, left, right;
	private boolean snapped;
	private boolean interactable;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, handler.getWidth() / 16, handler.getHeight() / 9);
		moveDistance = handler.getWidth() / 16 /2 ;
		bounds.x = 0;
		bounds.y = handler.getHeight() / 9 / 2;
		bounds.width = handler.getWidth() / 16 / 2 - 1;
		bounds.height = handler.getHeight() / 9 / 2 - 1;
		
		//Animations
		animationUp = new Animation(100, Assets.player_up, true);
		animationDown = new Animation(100, Assets.player_down, true);
		animationLeft = new Animation(100, Assets.player_left, true);
		animationRight = new Animation(100, Assets.player_right, true);
		
		idolAnimationUp = new Animation(200, Assets.player_up_idol, true);
		idolAnimationDown = new Animation(200, Assets.player_down_idol, true);
		idolAnimationLeft = new Animation(200, Assets.player_left_idol, true);
		idolAnimationRight = new Animation(200, Assets.player_right_idol, true);
		
		threshold = TILE_LENGTH / 10;
		interactable = true;
		
	}

	public void tick() {

		if(up)
			animationUp.tick();
		else if(down)
			animationDown.tick();
		else if(left)
			animationLeft.tick();
		else if(right)
			animationRight.tick();
		else
			animationDown.tick();
		
		getInput();
		move();
		
		//center camera on player
		handler.getGameCamera().centerOnEntity(this);
	}
	
	private void getInput(){
		
		xMove = 0;
		yMove = 0;
		boolean up = false, down = false, left = false, right = false;
		
		if(!isMoving){		//Start player movements
			
			//change this later on to support idol animation
			//resetDirection();
			if(!snapped){
				snapPosition();
				snapped = true;
			}
			
			
			if(handler.getKeyManager().up){
				targetX = (int) x;
				targetY = (int) (y-moveDistance);
				resetDirection();
				up = true;
				setDirection(up,down,left,right);
				if(!isTargetSolid("up", (int)targetX, (int)(targetY + moveDistance / 2))){
					isMoving = true;
				}
			}else
			if(handler.getKeyManager().down){
				targetX = (int) x;
				targetY = (int) (y + moveDistance);
				resetDirection();
				down = true;
				setDirection(up,down,left,right);
				if(!isTargetSolid("down", (int)targetX, (int)(targetY - moveDistance / 2))){
					isMoving = true;
				}
			}else
			if(handler.getKeyManager().left){
				targetX = (int) (x - moveDistance);
				targetY = (int) y;
				resetDirection();
				left = true;
				setDirection(up,down,left,right);
				if(!isTargetSolid("left", (int)(targetX + moveDistance / 2), (int)targetY)){
					isMoving = true;
				}
				
			}else
			if(handler.getKeyManager().right){
				targetX = (int) (x + moveDistance);
				targetY = (int) y;
				resetDirection();
				right = true;
				setDirection(up,down,left,right);
				if(!isTargetSolid("right", (int)(targetX - moveDistance / 2), (int)targetY)){
					isMoving = true;
				}
			}
			
			if(isMoving){
				snapped = false;
			}
			
		}
		///////////////////////////Already Moving///////////////////////////////////////
		if(isMoving){
			
			if(this.up){
				if(y > targetY){
					yMove = -speed;
					if(Math.abs(y - targetY) <= threshold){
						if(handler.getKeyManager().up){
							targetY = targetY - moveDistance;
							if(isTargetSolid("up", (int)targetX, (int)(targetY + moveDistance / 2))){
								up = false;
								isMoving = false;
							}
						}
					}
				}
				if(y <= targetY){
					y = targetY;
					resetAnimation();
					isMoving = false;
				}
			}else
			if(this.down){
				if(y < targetY){
					yMove = +speed;
					if(Math.abs(y - targetY) <= threshold){
						if(handler.getKeyManager().down){
							targetY = targetY + moveDistance;
							if(isTargetSolid("down", (int)targetX, (int)(targetY - moveDistance / 2))){
								down = false;
								isMoving = false;
							}
						}
					}
				}
				if(y >= targetY){
					y = targetY;
					resetAnimation();
					isMoving = false;
				}
			}else
			if(this.left){
				if(x > targetX){
					xMove = -speed;
					if(Math.abs(x - targetX) <= threshold){
						if(handler.getKeyManager().left){
							targetX = targetX - moveDistance;
							if(isTargetSolid("left", (int)(targetX + moveDistance / 2), (int)targetY)){
								left = false;
								isMoving = false;
							}
						}
					}
				}
				
				if(x <= targetX){
					x = targetX;
					resetAnimation();
					isMoving = false;
				}
			}else
			if(this.right){
				if(x < targetX){
					xMove = +speed;
					if(Math.abs(x - targetX) <= threshold){
						if(handler.getKeyManager().right){
							targetX = targetX + moveDistance;
							if(isTargetSolid("right", (int)(targetX - moveDistance / 2), (int)targetY)){
								right = false;
								isMoving = false;
							}
						}
					}
				}
				if(x >= targetX){
					x = targetX;
					resetAnimation();
					isMoving = false;
				}
			}
				
		}
		
	}

	private void setDirection(boolean up, boolean down, boolean left, boolean right){
		if(!this.up && !this.down && !this.left & !this.right){
			this.up = up;
			this.down = down;
			this.left = left;
			this.right = right;
		}
	}
	
	private void resetAnimation(){
		animationUp.setCurrentFrame(1);
		animationDown.setCurrentFrame(1);
		animationLeft.setCurrentFrame(1);
		animationRight.setCurrentFrame(1);
	}
	
	private void resetDirection(){
		this.up = false;
		this.down = false;
		this.left = false;
		this.right = false;		
	}
	
	public void render(Graphics g) {
		int displayX = (int) (x - handler.getGameCamera().getxOffset()); //(x - 24);
		int displayY = (int) (y - handler.getGameCamera().getyOffset()); //(y + 48);

		g.drawImage(getCurrentAnimationFrame(),displayX - width / 4,displayY, width, height,null);
		
	}
	
	private BufferedImage getCurrentAnimationFrame(){
		if(up){
			if(isMoving)
				return animationUp.getCurrentFrame();
			return idolAnimationUp.getCurrentFrame();
		}else if(down){
			if(isMoving)
				return animationDown.getCurrentFrame();
			return idolAnimationDown.getCurrentFrame();
		}else if(left){
			if(isMoving)
				return animationLeft.getCurrentFrame();
			return idolAnimationLeft.getCurrentFrame();
		}else if(right){
			if(isMoving)
				return animationRight.getCurrentFrame();
			return idolAnimationRight.getCurrentFrame();
		}else{ //Default
			return idolAnimationDown.getCurrentFrame();
		}
	}
	
	public String getDirectionFacing(){
		if(up){
			return "UP";
		}else if(down){
			return "DOWN";
		}else if(left){
			return "LEFT";
		}else 
			return "RIGHT";
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
}