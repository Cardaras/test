package game.quests;

import game.entities.Entity;
import game.entities.statics.FilledSign;
import game.gfx.GUIManager;

public class QuestManager{
	
	private SignQuest signQuest;
	
	private Entity[] activeEntities;
	private int[] activeIndices;
	private int maxNumberOfActiveQuests;
	
	public QuestManager(){
		signQuest = new SignQuest();
		maxNumberOfActiveQuests = 3;
		
		activeEntities = new Entity[maxNumberOfActiveQuests];
		activeIndices = new int[maxNumberOfActiveQuests];
		
	}
	
	public void setQuestCompleted(Entity e, int questID){
		if(e instanceof FilledSign){
			signQuest.setQuestCompleted(questID);
		}
	}
	
	public boolean isQuestActive(Entity e, int questID){
		if(e instanceof FilledSign){
			return signQuest.isQuestActive(questID);
		}else 
			return false;
	}
	
	public void setQuestActive(Entity e, int questID){
		if(e instanceof FilledSign){
			signQuest.setQuestActive(questID);
		}
	}
	
	public void setQuestInactive(Entity e, int questID){
		if(e instanceof FilledSign){
			signQuest.setQuestInactive(questID);
		}	
	}
	
	public String getQuestRequirements(Entity e, int questID){	
		if(e instanceof FilledSign){
			return signQuest.getQuestRequirements(questID);
		}else 
			return null;
	}
	
	public boolean[] getCompletedQuests(Entity e){
		if(e instanceof FilledSign){
			return signQuest.getCompletedQuests();
		}else 
			return null;
	}
	
	// checks to see if there are any active quests affiliated with 
	// the asset currently interacting with the player
	public void setAssetCurrentlyInteractingWith(Entity e){
		
		checkForQuests(e);
		
		//GUIManager.setCompletedQuestsText(completedQuests);
	}
	
	private void checkForQuests(Entity e){
		for(int i = 0; i < maxNumberOfActiveQuests; i++){
			if(activeEntities[i] != null){
				if(e.getClass().equals(activeEntities[i].getClass())){
					
				}
			}
			
		}
	}
}
