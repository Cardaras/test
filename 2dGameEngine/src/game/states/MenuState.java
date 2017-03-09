package game.states;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;
import game.input.MouseManager;

public class MenuState extends State{

	public MenuState(Handler handler){
		super(handler);
	}
	
	
	public void tick() {
		////Start//////
		// 2/28/2017 // 		Alex
		///////////////
		// Tests the functionality of mouse listener
		// Press left and right mouse button to return to game.
		if(handler.getMouseManager().isLeftPressed()){
			State.setState(handler.getGame().gameState);
		}
		////Stop//////
		// 2/28/2017 // 		/Alex
		///////////////
	}

	public void render(Graphics g) {
		
			////Start//////
			// 2/28/2017 // 		Alex
			///////////////
			// Tests the functionality of mouse motion listener.
			g.setColor(Color.RED);
			g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 10, 10);
			////Stop//////
			// 2/28/2017 // 		/Alex
			///////////////
		
	}

}
