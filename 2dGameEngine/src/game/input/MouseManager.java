package game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


/////////////////////////
// New Class 2/28/2017 //
/////////////////////////

// This class manages mouse button and mouse movement input.

public class MouseManager implements MouseListener, MouseMotionListener{

	// is the left or right mouse button currently being clicked?
	private boolean leftPressed, rightPressed;
	
	// position of mouse on screen
	private int mouseX, mouseY;
	
	public MouseManager(){
		
	}
	
	
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//left button
		if(e.getButton() == MouseEvent.BUTTON1){
			leftPressed = true;
		}else //Right button
		if(e.getButton() == MouseEvent.BUTTON3){
			rightPressed = true;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//left button
		if(e.getButton() == MouseEvent.BUTTON1){
			leftPressed = false;
		}else //Right button
		if(e.getButton() == MouseEvent.BUTTON3){
			rightPressed = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	// Getters
	public boolean isLeftPressed(){
		return leftPressed;
	}
	public boolean isRightPressed(){
		return rightPressed;
	}
	
	public int getMouseX(){
		return mouseX;
	}
	public int getMouseY(){
		return mouseY;
	}
	
}
