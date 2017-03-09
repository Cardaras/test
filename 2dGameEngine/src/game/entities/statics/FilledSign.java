package game.entities.statics;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game.Handler;
import game.gfx.Assets;
import game.gfx.GUIManager;
import game.quests.QuestManager;

public class FilledSign extends StaticEntity{
	private boolean interactable;
	private boolean currentlyInteracting;
	
	private QuestManager questManager;
	
	public FilledSign(Handler handler, int x, int y) {
		super(handler, x, y, handler.getWidth() / 16 , handler.getHeight() / 9 );
		
		bounds.x = 0;
		bounds.y = handler.getHeight() / 9 / 2;
		bounds.width = handler.getWidth() / 16 - 1;
		bounds.height = handler.getHeight() / 9 / 2 - 1;
		
		interactable = true;
		currentlyInteracting = false;
		
		questManager = handler.getQuestManager();
	}

	@Override
	public void tick() {
		if(handler.getKeyManager().isKeyDown(KeyEvent.VK_UP) == false){
			currentlyInteracting = false;
		}
		if(!handler.getWorld().getEntityManager().getPlayer().getDirectionFacing().equals("UP")){
			GUIManager.setIncompletedQuestsText("");
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.filledSign, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
		
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
	
	private void checkInteractions(){
		updateQuests();
		setCurrentQuests();
		GUIManager.setIncompletedQuestsText(getCurrentQuestDescription());

	}
	
	private void updateQuests(){	
		
		//tells the questManager that this asset is currently being interacted with
		questManager.setAssetCurrentlyInteractingWith(this);

	}
	
	private void setCurrentQuests(){
		int count = 0;
		int index = 0;
		boolean[] questCompleted = questManager.getCompletedQuests(this);
		
		while(count <= 2 && index < questCompleted.length){
			// updates active quest in the sign quest class
			if(questCompleted[index] == false && questManager.isQuestActive(this, index) == false){
				questManager.setQuestActive(this, index);
				count++;
			}
			index++;
		}
	}
	
	private String getCurrentQuestDescription(){
		String s = "";
		boolean[] questCompleted = questManager.getCompletedQuests(this);
		
		int count = 0;
		int index = 0;
		while(count <= 2 && index < questCompleted.length){
			if(questCompleted[index] == false){
				String description = questManager.getQuestRequirements(this, index);
				if(description != null){
					s += "   -" + description + "\n";
					count++;
				}
			}	
			index++;
		}
		return s;
	}
}