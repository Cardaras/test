package game.quests;

public interface QuestInterface {
	public abstract void init();
	public abstract boolean[] getCompletedQuests();
	public abstract boolean isQuestActive(int questID);
	public abstract void setQuestCompleted(int questID);
	public abstract void setQuestActive(int questID);
	public abstract void setQuestInactive(int questID);
	public abstract String getQuestRequirements(int questID);
}
