package game.quests;

import game.entities.Entity;

public interface QuestInterface {
	public abstract void init();
	public abstract boolean[] getCompletedQuests();
	public abstract boolean isQuestActive(int questID);
	public abstract void setQuestCompleted(int questID);
	public abstract void setQuestActive(int questID);
	public abstract void setQuestInactive(int questID);
	public abstract String getQuestRequirements(int questID);
	public abstract int getIncompletedQuestID();
	public abstract Entity getEntityAtQuestNum(int location);
}
