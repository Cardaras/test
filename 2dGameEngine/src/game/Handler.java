package game;

import game.gfx.GameCamera;
import game.input.KeyManager;
import game.input.MouseManager;
import game.quests.QuestManager;
import game.world.World;


// Class responsible for handling the world, camera, input, etc. 

//TODO: Do something with public jframe dimensions


public class Handler {
	private Game game;
	private World world;
	
	public Handler(Game game){
		this.game = game;

		game.setQuestManager(this);
		
	}
	
	public int getWidth(){
		return game.getWidth();
	}
	
	public int getHeight(){
		return game.getHeight();
	}
	
	public KeyManager getKeyManager(){
		return game.getKeyManager();
	}
	
	public QuestManager getQuestManager(){
		return game.getQuestManager();
	}
	
	public GameCamera getGameCamera(){
		return game.getGameCamera();
	}

	public MouseManager getMouseManager(){
		return game.getMouseManager();
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
}
