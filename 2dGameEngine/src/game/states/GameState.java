package game.states;

import java.awt.Graphics;

import game.Handler;
import game.entities.creatures.Player;
import game.world.World;

public class GameState extends State{
	
	private Player player;
	private World world;

	public GameState(Handler handler){
		super(handler);
		world = new World(handler, "res/world/world1.txt", "res/world/terrain.txt");
		handler.setWorld(world);
	}
	
	public void tick() {
		world.tick();
	}

	public void render(Graphics g) {
		world.render(g);
	}

}
