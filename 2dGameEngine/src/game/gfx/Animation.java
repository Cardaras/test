package game.gfx;

import java.awt.image.BufferedImage;

public class Animation {

	private int speed;
	private int index;
	private long lastTime;
	private long timer;
	private BufferedImage[] frames;
	private boolean isIncreasing;
	private boolean isFlip;
														// is flip: 0 > 1 > 2 > 1 > 0 > 1...
	public Animation(int speed, BufferedImage[] frames, boolean isFlip){
		this.speed = speed;
		this.frames = frames;
		index = 0;
		if(frames.length > 1)
			timer = speed;
		else timer = 0;
		lastTime = System.currentTimeMillis();
		isIncreasing = true;
		this.isFlip = isFlip;
	}
	
	public void tick(){
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed){
			if(isFlip){
				if(isIncreasing){
					index++;
					timer = 0;
					if(index >= frames.length){
						index-=1;
						isIncreasing = false;
					}
				}else{
					index--;
					timer = 0;
					
					if(index < 0){
						index = 0;
						isIncreasing = true;
					}
				}
			}else{
				index++;
				timer = 0;
				
				if(index >= frames.length){
					index = 0;
				}
			}
		}
	}
	
	public BufferedImage getCurrentFrame(){
		return frames[index];
	}
	
	public void setCurrentFrame(int index){
		this.index = index;
	}
}
