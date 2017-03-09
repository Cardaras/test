package game.quests;

public class SignQuest extends Quest implements QuestInterface{
	private boolean questCompleted[];
	private boolean questActive[];
	private static String questRequirements[];
	
	int numberOfQuests;
	
	public SignQuest(){
		init();
		
	}

	@Override
	public void init() {
		numberOfQuests = 10;
		questCompleted = new boolean[numberOfQuests];
		questActive = new boolean[numberOfQuests];
		questRequirements = new String[numberOfQuests];
		
		questRequirements[0] = "Look at the blank sign.";
		questRequirements[1] = "This is a second quest.";
		questRequirements[2] = "This is a third quest.";
		
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

	@Override
	public String getQuestRequirements(int questID) {
		return questRequirements[questID];
	}

}
