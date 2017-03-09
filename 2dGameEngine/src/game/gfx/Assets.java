package game.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int WIDTH = 32, HEIGHT = 32;
	
	public static BufferedImage dirt, grass, stone;
	
	public static BufferedImage deadTree, pineTree, emptySign, filledSign;
	
	public static BufferedImage[] player_up;
	public static BufferedImage[] player_down;
	public static BufferedImage[] player_left;
	public static BufferedImage[] player_right;
	
	public static BufferedImage[] player_up_idol;
	public static BufferedImage[] player_down_idol;
	public static BufferedImage[] player_left_idol;
	public static BufferedImage[] player_right_idol;
	
	
	public static void init(){
		SpriteSheet terrainSS = new SpriteSheet(ImageLoader.loadImage("/textures/SpriteSheet32x32.png"));
		SpriteSheet playerSS = new SpriteSheet(ImageLoader.loadImage("/textures/Player.png"));
		
		//http://zetavares852.deviantart.com/art/Pokemon-Gaia-Project-Tileset-1-155863852
		SpriteSheet treeSS = new SpriteSheet(ImageLoader.loadImage("/textures/TreeSheet.png"));
		
		SpriteSheet terrainSheet = new SpriteSheet(ImageLoader.loadImage("/textures/asset_sheet.png"));
		
		//Tiles
		dirt = terrainSS.crop(WIDTH * 0, 0, WIDTH, HEIGHT);
		grass = terrainSS.crop(WIDTH * 1, 0, WIDTH, HEIGHT);
		stone = terrainSS.crop(WIDTH * 2, 0, WIDTH, HEIGHT);
		
		
		//Players
		player_up = new BufferedImage[3];
		player_up[0] = playerSS.crop(WIDTH * 0, HEIGHT * 3, WIDTH , HEIGHT);
		player_up[1] = playerSS.crop(WIDTH * 1, HEIGHT * 3, WIDTH , HEIGHT);
		player_up[2] = playerSS.crop(WIDTH * 2, HEIGHT * 3, WIDTH , HEIGHT);
		
		player_down = new BufferedImage[3];
		player_down[0] = playerSS.crop(WIDTH * 0, HEIGHT * 0, WIDTH , HEIGHT);
		player_down[1] = playerSS.crop(WIDTH * 1, HEIGHT * 0, WIDTH , HEIGHT);
		player_down[2] = playerSS.crop(WIDTH * 2, HEIGHT * 0, WIDTH , HEIGHT);
		
		player_left = new BufferedImage[3];
		player_left[0] = playerSS.crop(WIDTH * 0, HEIGHT * 1, WIDTH , HEIGHT);
		player_left[1] = playerSS.crop(WIDTH * 1, HEIGHT * 1, WIDTH , HEIGHT);
		player_left[2] = playerSS.crop(WIDTH * 2, HEIGHT * 1, WIDTH , HEIGHT);
		
		player_right = new BufferedImage[3];
		player_right[0] = playerSS.crop(WIDTH * 0, HEIGHT * 2, WIDTH , HEIGHT);
		player_right[1] = playerSS.crop(WIDTH * 1, HEIGHT * 2, WIDTH , HEIGHT);
		player_right[2] = playerSS.crop(WIDTH * 2, HEIGHT * 2, WIDTH , HEIGHT);
		
				//Idol
		
		player_up_idol = new BufferedImage[1];
		player_up_idol[0] = playerSS.crop(WIDTH * 1, HEIGHT * 3, WIDTH , HEIGHT);
		
		player_down_idol = new BufferedImage[1];
		player_down_idol[0] = playerSS.crop(WIDTH * 1, HEIGHT * 0, WIDTH , HEIGHT);
		
		player_left_idol = new BufferedImage[1];
		player_left_idol[0] = playerSS.crop(WIDTH * 1, HEIGHT * 1, WIDTH , HEIGHT);
		
		
		player_right_idol = new BufferedImage[1];
		player_right_idol[0] = playerSS.crop(WIDTH * 1, HEIGHT * 2, WIDTH , HEIGHT);
		
		
		//Trees
		emptySign = terrainSheet.crop(WIDTH * 0, HEIGHT * 6, WIDTH, HEIGHT);
		filledSign = terrainSheet.crop(WIDTH * 1, HEIGHT * 6, WIDTH, HEIGHT);
		pineTree = terrainSheet.crop(WIDTH * 0, HEIGHT * 9, WIDTH * 2, HEIGHT * 2);
		deadTree = terrainSheet.crop(WIDTH * 0, HEIGHT * 7, WIDTH * 2, HEIGHT * 2);
		
		
		
		
	}
	
	public static int getWidth(){
		return WIDTH;
	}
	public static int getHeight(){
		return HEIGHT;
	}
}
