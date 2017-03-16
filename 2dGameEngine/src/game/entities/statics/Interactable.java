package game.entities.statics;

public interface Interactable {

	public abstract boolean isInteractable();
	public abstract void interact();
	
	public abstract void checkInteractions();
}
