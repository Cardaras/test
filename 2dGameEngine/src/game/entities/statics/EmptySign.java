package game.entities.statics;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game.Handler;
import game.gfx.Assets;
import game.gfx.GUIManager;
import game.quests.QuestManager;

public class EmptySign extends StaticEntity implements Interactable{
	private boolean interactable;
	private boolean currentlyInteracting;
	private QuestManager questManager;
	
	private int id;
	
	public EmptySign(Handler handler, int x, int y, int id) {
		super(handler, x, y, handler.getWidth() / 16 , handler.getHeight() / 9 );
		
		bounds.x = 0;
		bounds.y = handler.getHeight() / 9 / 2;
		bounds.width = handler.getWidth() / 16 - 1;
		bounds.height = handler.getHeight() / 9 / 2 - 1;
		
		interactable = true;
		currentlyInteracting = false;
		questManager = handler.getQuestManager();
		
		this.id = id;
	}

	@Override
	public void tick() {
		if(handler.getKeyManager().isKeyDown(KeyEvent.VK_UP) == false){
			currentlyInteracting = false;
		}
		if(!handler.getWorld().getEntityManager().getPlayer().getDirectionFacing().equals("UP")){
			GUIManager.setCompletedQuestsText("");
		}
	}

	@Override
	public boolean isInteractable() {
		return interactable;
	}
	
	@Override
	public void interact(){
		if(!currentlyInteracting && (handler.getKeyManager().isKeyDown(KeyEvent.VK_W) || handler.getKeyManager().isKeyDown(KeyEvent.VK_UP))){
			currentlyInteracting = true;
			checkInteractions();
		}	
	}
	
	public void checkInteractions(){
		updateQuests();
	}
	
	private void updateQuests(){	
		if(questManager == null){
			System.out.println("Halp");
		}
		questManager.setAssetCurrentlyInteractingWith(this);
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.emptySign, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
	}
	
	public int getID(){
		return id;
	}

}
