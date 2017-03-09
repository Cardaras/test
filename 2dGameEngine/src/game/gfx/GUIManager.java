package game.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.Handler;

public class GUIManager {

	private static String incompletedQuests;
	private static String completedQuests;
	
	private Color c1;
	private Color c2;
	private Font font;

	private int width, height;
	private int textX, textY;
	
	public GUIManager(Handler handler){
		incompletedQuests = "";
		height = handler.getHeight();
		width = handler.getWidth();
		
		font = new Font("Arial",Font.PLAIN, width/16/5);
		
		textX = width / 5;
		textY = height - height / 6;
	}
	
	public void tick(){
		
	}
	
	
	
	public void render(Graphics g){
		
		int textYOffset = textY;
		
		c1 = new Color(255,255,255,150);
		c2 = new Color(50,50,50,150);
		
		g.setColor(c1);
		g.fillRect(0, height - height/5, width, height/5);
		
		
		g.setColor(c2);
		g.setFont(font);
		g.drawString("Incompleted Quests", width/10, textYOffset);
		for (String line : incompletedQuests.split("\n")){
			textYOffset += g.getFontMetrics().getHeight();
            g.drawString(line, width/10, textYOffset);
		}
		
		textYOffset = textY;
		g.drawString("Completed Quests", width/2, textYOffset);
		for (String line : completedQuests.split("\n")){
			textYOffset += g.getFontMetrics().getHeight();
            g.drawString(line, width/2, textYOffset);
		}
		
		
	}
	
	public static void setIncompletedQuestsText(String incompletedQuests){
		GUIManager.incompletedQuests = incompletedQuests;
	}
	
	public static void setCompletedQuestsText(String completedQuests){
		GUIManager.completedQuests = completedQuests;
	}
}
