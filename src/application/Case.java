package application;

public class Case {
	
	private boolean isVisible;
	private boolean isMine;
	private boolean isMineForPlayer;
	private int nbMineAround;

	Case(boolean isVisible, boolean isMine, boolean isMineForPlayer, int nbMineAround){
		this.isVisible = isVisible;
		this.isMine = isMine;
		this.isMineForPlayer = isMineForPlayer;
		this.nbMineAround = nbMineAround;		
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public boolean isMine() {
		return isMine;
	}

	public void setMine(boolean isMine) {
		this.isMine = isMine;
	}

	public boolean isMineForPlayer() {
		return isMineForPlayer;
	}

	public void setMineForPlayer(boolean isMineForPlayer) {
		this.isMineForPlayer = isMineForPlayer;
	}

	public int getNbMineAround() {
		return nbMineAround;
	}

	public void setNbMineAround(int nbMineAround) {
		this.nbMineAround = nbMineAround;
	}
	
	
}
