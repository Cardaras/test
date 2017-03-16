
	//https://www.youtube.com/user/CodeNMore/playlists


//NOTE: There is an inconsistency with rendering layers


package game;

// Heart of project, holds game loop

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import game.display.Display;
import game.gfx.Assets;
import game.gfx.GameCamera;
import game.input.KeyManager;
import game.input.MouseManager;
import game.quests.QuestManager;
import game.states.GameState;
import game.states.MenuState;
import game.states.State;

public class Game implements Runnable{
	
	//Display class, creates/ sets up JFrame
	private Display display;
	
	//Thread to start game loop
	private Thread thread;
	private boolean running = false;
	
	// palette to draw graphics to
	private BufferStrategy bs;
	private Graphics g;
	
	//Game title(Top of window)
	public String title;
	
	// Window width and height
	public static int width, height;
	
	
	//private State gameState;
	//private State menuState;
	// this is just for debugging purposes to test out changing states from the MenuState class.
	public State gameState;
	public State menuState;
	
//	private State settingState;
	
	//INPUT
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//Camera class used to follow player
	private GameCamera gameCamera;
	
	//questManager is set inside the handler class
	private QuestManager questManager;
	
	//Handler class used to give other classes access to specific variables
	private Handler handler;
	
	
	public Game(String title, int width, int height){
		this.title = title;
		Game.width = width;
		Game.height = height;
		
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		
	
	}


	private void init(){
		display = new Display(title, width, height);	
		display.getFrame().addKeyListener(keyManager);
		
		//adds functionality to both mouse listener and mouse motion listener to the JFrame and Canvas
		//we add it to both JFrame and Canvas because one or the other might not be focused at a given time.
		//with both of them being listened to, no mouse movements or clicks will be missed.
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);

		
		Assets.init();
		
		handler = new Handler(this);
		gameCamera= new GameCamera(handler, 0, 0);
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
//		settingState = new SettingState(handler);
		
		
		
		// Debugging, testing out menuState
		State.setState(gameState);

		
		
		
	}
	
	// all nongraphical calculations 
	private void tick(){
		keyManager.tick();
		
		if(State.getState() != null){
			State.getState().tick();
		}
	}
	
	//graphics
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		//start Draw
		
		if(State.getState() != null){
			State.getState().render(g);
		}
 
		// end Draw
		bs.show();
		g.dispose();
		
	}
	
	public void run() {
		init();
		long lastTime = System.nanoTime();
		//how many nanoseconds should go by before tick
		double nsPerTick = 1000000000D/60D;
		
		int frames = 0;
		int ticks = 0;
		
		double delta = 0;
		long lastTimer = System.currentTimeMillis();
		
		while(running){
			long now = System.nanoTime();
			delta +=(now - lastTime)/nsPerTick;
			lastTime = now;
			boolean shouldRender = false;
			
			// when target 1/60 fps time is reached delta >= 1
			while(delta>=1){
				ticks++;
				tick();
				delta-=1;
				shouldRender = true;
			}
			
			//The reason for this 'if' is in case i want to limit the fps to 
			// the amount of ticks. Get rid of 'if' for uncapped frames.
			if(shouldRender){
				frames++;
				render();
			}
			
			// one second has gone by
			if(System.currentTimeMillis() - lastTimer >= 1000){
				lastTimer += 1000;
				System.out.println("FPS: "+frames+", TPS: "+ticks);
				//reset frame and tick counter
				frames = 0;
				ticks = 0;
			}
		}
		// destroy thread, end program
		stop();
	}
	
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	public MouseManager getMouseManager(){
		return mouseManager;
	}
	
	public GameCamera getGameCamera(){
		return gameCamera;
	}
	
	public synchronized void start(){
		// in case start is accidentally called twice
		if(running)return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		// sometimes a thread fails to join, repeat when necessary 
		if(!running)return;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public QuestManager getQuestManager(){
		return questManager;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setQuestManager(Handler handler){
		this.questManager = new QuestManager(handler);
	}
}
