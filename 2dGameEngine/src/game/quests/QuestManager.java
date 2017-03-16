package game.quests;

import game.Handler;
import game.entities.Entity;
import game.entities.statics.FilledSign;
import game.entities.statics.Interactable;

public class QuestManager{
	
	private SignQuest signQuest;
	
	private Interactable[] activeEntities;
	private int[] activeIndicies;
	private int maxNumberOfActiveQuests;
	
	private Handler handler;
	
	public QuestManager(Handler handler){
		signQuest = new SignQuest(handler);
		maxNumberOfActiveQuests = 3;
		
		activeEntities = new Interactable[maxNumberOfActiveQuests];
		activeIndicies = new int[maxNumberOfActiveQuests];
		
		this.handler = handler;
		
		
		
	}
	
	// checks to see if there are any active quests affiliated with 
	// the asset currently interacting with the player
	public void setAssetCurrentlyInteractingWith(Interactable i){
		if(getQuestType(i) != null)
			setQuests(i);
		checkForQuests(i);
		
		//GUIManager.setCompletedQuestsText(completedQuests);
	}
	
	public void setQuestCompleted(Interactable i, int questID){
		getQuestType(i).setQuestCompleted(questID);
	}
	
	public boolean isQuestActive(Interactable i, int questID){
		return getQuestType(i).isQuestActive(questID);
	}
	
	public void setQuestActive(Interactable i, int questID){
		getQuestType(i).setQuestActive(questID);
	}
	
	public void setQuestInactive(Interactable i, int questID){
		getQuestType(i).setQuestInactive(questID);

	}
	
	public String getQuestRequirements(Entity e){	
		String str = "";
		for(int i = 0; i < maxNumberOfActiveQuests; i++){
			if(activeIndicies[i] != -1)
				str += "    -"+getQuestType(activeEntities[i]).getQuestRequirements(activeIndicies[i]) + "\n";
		}
		
		return str;
	}
	
	public boolean[] getCompletedQuests(Interactable i){
		return getQuestType(i).getCompletedQuests();
	}
	
	private void setQuests(Interactable i){
		for(int n = 0; n < maxNumberOfActiveQuests; n++){
			if(activeEntities[n] == null){
				activeEntities[n] = i;
				activeIndicies[n] = getQuestType(i).getIncompletedQuestID();
			}
		}
	}
	
	private void checkForQuests(Interactable i){
		for(int n = 0; n < maxNumberOfActiveQuests; n++){
			if(activeEntities[n] != null){
				if(getQuestType(activeEntities[n]).getEntityAtQuestNum(activeIndicies[n]) == null)
					continue;
				if(i.toString().equals(handler.getWorld().getEntityManager().getEntity(getQuestType(activeEntities[n]).getEntityAtQuestNum(activeIndicies[n]).getID()).toString())){
					getQuestType(activeEntities[n]).setQuestCompleted(activeIndicies[n]);
					getQuestType(activeEntities[n]).setQuestInactive(activeIndicies[n]);
					activeIndicies[n] = -1;
					activeEntities[n] = null;
					sortActives(n);
					break;
				}
			}
		}
	}
	
	private void sortActives(int index){
		for(; index < maxNumberOfActiveQuests-1; index++){
			activeEntities[index] = activeEntities[index+1];
			activeIndicies[index] = activeIndicies[index+1];
		}
		
		activeEntities[index] = null;
		activeIndicies[index] = -1;
	}
	
	private QuestInterface getQuestType(Interactable i){
		if(i instanceof FilledSign){
			return signQuest;
		}else 
			return null;
	}
	
	public void setHandler(Handler handler){
		this.handler = handler;
	}
}
