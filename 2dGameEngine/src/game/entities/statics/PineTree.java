package game.entities.statics;

import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;

public class PineTree extends StaticEntity{
	
	private boolean interactable;
	
	public PineTree(Handler handler, int x, int y) {
		super(handler, x, y, handler.getWidth() / 16*2, handler.getHeight() / 9 *2);
		
		bounds.x = handler.getWidth() / 16 / 2;
		bounds.y = handler.getHeight() / 9 ;
		bounds.width = handler.getWidth() / 16  - 1;
		bounds.height = handler.getHeight() / 9  - 1;
	}

	@Override
	public void tick() {
		
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.pineTree, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public boolean isInteractable() {
		return interactable;
	}
	
	@Override
	public void interact(){
		
	}
}
