package game.quests;

import game.Handler;
import game.entities.Entity;

public class SignQuest implements QuestInterface{
	private final int PINE_TREE = 1;
	private final int DEAD_TREE = 2;
	private final int FILLED_SIGN = 3;
	private final int EMPTY_SIGN = 4;
	
	private boolean questCompleted[];
	private boolean questActive[];
	private static String questRequirements[];
	private Handler handler;
	
	int numberOfQuests;
	
	public SignQuest(Handler handler){
		init();
		this.handler = handler;
	}

	@Override
	public void init() {
		numberOfQuests = 10;
		questCompleted = new boolean[numberOfQuests];
		questActive = new boolean[numberOfQuests];
		questRequirements = new String[numberOfQuests];
		
		questRequirements[0] = "Look at the blank sign.";
		questRequirements[1] = "This is a 2nd quest.";
		questRequirements[2] = "This is a 3rd quest.";
		questRequirements[3] = "This is a 4th quest.";
		
		questRequirements[4] = "This is a 5th quest.";
		questRequirements[5] = "This is a 6th quest.";
		questRequirements[6] = "This is a 7th quest.";
		
		questRequirements[7] = "This is a 8th quest.";
		questRequirements[8] = "This is a 9th quest.";
		questRequirements[9] = "This is a 10th quest.";
		
	}

	@Override
	public boolean isQuestActive(int questID) {
		return questActive[questID];
	}

	@Override
	public void setQuestCompleted(int questID) {
		questCompleted[questID] = true;
	}

	@Override
	public void setQuestActive(int questID) {
		questActive[questID] = true;
	}

	@Override
	public void setQuestInactive(int questID) {
		questActive[questID] = false;
	}

	@Override
	public boolean[] getCompletedQuests() {
		return questCompleted;
	}
	
	
	public int getIncompletedQuestID(){
		int i = 0;
		while(i < numberOfQuests){
			if(questCompleted[i] == false && questActive[i] == false){
				questActive[i] = true;
				return i;
			}
			i++;
		}
		// no incompleted quests were found
		return -1;
	}

	@Override
	public String getQuestRequirements(int questID) {
		return questRequirements[questID];
	}

	@Override
	public Entity getEntityAtQuestNum(int location) {
		switch(location){
		case 0: 
			return handler.getWorld().getEntityManager().getEntity(EMPTY_SIGN);
		case 1: 
			return handler.getWorld().getEntityManager().getEntity(EMPTY_SIGN);
		case 2: 
			return handler.getWorld().getEntityManager().getEntity(EMPTY_SIGN);
		case 3: 
			return handler.getWorld().getEntityManager().getEntity(EMPTY_SIGN);
		case 4: 
			return handler.getWorld().getEntityManager().getEntity(EMPTY_SIGN);
		case 5: 
			return handler.getWorld().getEntityManager().getEntity(EMPTY_SIGN);
		case 6: 
			return handler.getWorld().getEntityManager().getEntity(EMPTY_SIGN);
		case 7: 
			return handler.getWorld().getEntityManager().getEntity(EMPTY_SIGN);
		case 8: 
			return handler.getWorld().getEntityManager().getEntity(EMPTY_SIGN);
		case 9: 
			return handler.getWorld().getEntityManager().getEntity(EMPTY_SIGN);

		default:
			return null;
		
		}
	}
}