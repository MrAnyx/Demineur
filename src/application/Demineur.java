package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class Demineur {
	
	private Case[][] jeu = new Case[16][16];
	private boolean playerLose;
	private int getNbMineSuspecte;
	
	Demineur(){
		
		//initialisation des cases
		for(int i = 0; i<16; i++) {
			for(int j = 0; j<16; j++){
				this.jeu[i][j] = new Case(false, false, false, 0);
			}
		}
		// initialisation des mines
		for(int j = 0; j<16; j++) {
			for(int k = 0; k<1; k++) {
				this.jeu[(int)Math.ceil(Math.random()*15)][j].setMine(true);
			}
		}
	
		
		//nb Mine around
		for(int i = 0; i<16; i++) {
			for(int j = 0; j<16; j++){
				this.jeu[i][j].setNbMineAround(nbMineAround(i, j));
			}
		}
		
		
		this.playerLose = false;
		this.getNbMineSuspecte = 0;
	}
	
	private int nbMineAround(int i, int j) {
		if(!this.jeu[i][j].isMine() && i>0 && i<15 && j<15 && j>0) {
			return boolToInt(this.jeu[i+1][j].isMine())+boolToInt(this.jeu[i+1][j+1].isMine())+boolToInt(this.jeu[i+1][j-1].isMine())+boolToInt(this.jeu[i][j+1].isMine())+boolToInt(this.jeu[i][j-1].isMine())+boolToInt(this.jeu[i-1][j].isMine())+boolToInt(this.jeu[i-1][j-1].isMine())+boolToInt(this.jeu[i-1][j+1].isMine());
		}if(!this.jeu[i][j].isMine() && i==0 && j<15 && j>0) {
			return boolToInt(this.jeu[i+1][j].isMine())+boolToInt(this.jeu[i+1][j+1].isMine())+boolToInt(this.jeu[i+1][j-1].isMine())+boolToInt(this.jeu[i][j+1].isMine())+boolToInt(this.jeu[i][j-1].isMine());
		}if(!this.jeu[i][j].isMine() && i==15 && j<15 && j>0) {
			return boolToInt(this.jeu[i][j+1].isMine())+boolToInt(this.jeu[i][j-1].isMine())+boolToInt(this.jeu[i-1][j].isMine())+boolToInt(this.jeu[i-1][j+1].isMine())+boolToInt(this.jeu[i-1][j-1].isMine());
		}if(!this.jeu[i][j].isMine() && i>0 && i<15 && j==0) {
			return boolToInt(this.jeu[i+1][j].isMine())+boolToInt(this.jeu[i+1][j+1].isMine())+boolToInt(this.jeu[i][j+1].isMine())+boolToInt(this.jeu[i-1][j].isMine())+boolToInt(this.jeu[i-1][j+1].isMine());
		}if(!this.jeu[i][j].isMine() && i>0 && i<15 && j==15) {
			return boolToInt(this.jeu[i+1][j].isMine())+boolToInt(this.jeu[i+1][j-1].isMine())+boolToInt(this.jeu[i][j-1].isMine())+boolToInt(this.jeu[i-1][j].isMine())+boolToInt(this.jeu[i-1][j-1].isMine());
		}if(!this.jeu[i][j].isMine() && i==0 && j==0) {
			return boolToInt(this.jeu[i+1][j].isMine())+boolToInt(this.jeu[i+1][j+1].isMine())+boolToInt(this.jeu[i][j+1].isMine());
		}if(!this.jeu[i][j].isMine() && i==0 && j==15) {
			return boolToInt(this.jeu[i+1][j].isMine())+boolToInt(this.jeu[i+1][j-1].isMine())+boolToInt(this.jeu[i][j-1].isMine());
		}if(!this.jeu[i][j].isMine() && i==15 && j==0) {
			return boolToInt(this.jeu[i-1][j].isMine())+boolToInt(this.jeu[i-1][j+1].isMine())+boolToInt(this.jeu[i][j+1].isMine());
		}if(!this.jeu[i][j].isMine() && i==15 && j==15) {
			return boolToInt(this.jeu[i-1][j].isMine())+boolToInt(this.jeu[i-1][j-1].isMine())+boolToInt(this.jeu[i][j-1].isMine());
		}else {
			return 1;
		}
		
		
	}
	
	public void expansion(int i, int j) {
		this.jeu[i][j].setVisible(true);

		
		if(j<15 && !this.jeu[i][j+1].isVisible()) {
			this.jeu[i][j+1].setVisible(true);
			if(this.jeu[i][j+1].getNbMineAround() == 0 ){
				expansion(i, j+1);
			}
		}if(j>0 && !this.jeu[i][j-1].isVisible()) {
			this.jeu[i][j-1].setVisible(true);
			if(this.jeu[i][j-1].getNbMineAround() == 0 ) {
				expansion(i, j-1);
			}
		}if(i<15 && !this.jeu[i+1][j].isVisible()) {
			this.jeu[i+1][j].setVisible(true);
			if(this.jeu[i+1][j].getNbMineAround() == 0) {
				expansion(i+1, j);
			}
		}if(i<15 && j<15 && !this.jeu[i+1][j+1].isVisible()) {
			this.jeu[i+1][j+1].setVisible(true);
			if(this.jeu[i+1][j+1].getNbMineAround() == 0) {
				expansion(i+1, j+1);
			}
		}if(i<15 && j>0 && !this.jeu[i+1][j-1].isVisible()) {
			this.jeu[i+1][j-1].setVisible(true);
			if(this.jeu[i+1][j-1].getNbMineAround() == 0) {
				expansion(i+1, j-1);
			}
		}if(i>0 && j<15 && !this.jeu[i-1][j+1].isVisible()) {
			this.jeu[i-1][j+1].setVisible(true);
			if(this.jeu[i-1][j+1].getNbMineAround() == 0) {
				expansion(i-1, j+1);
			}
		}if(i>0 && j>0 && !this.jeu[i-1][j-1].isVisible()) {
			this.jeu[i-1][j-1].setVisible(true);
			if(this.jeu[i-1][j-1].getNbMineAround() == 0) {
				expansion(i-1, j-1);
			}
		}if(i>0 && !this.jeu[i-1][j].isVisible()) {
			this.jeu[i-1][j].setVisible(true);
			if(this.jeu[i-1][j].getNbMineAround() == 0) {
				expansion(i-1, j);
			}
		}
		
	}
	
	private int boolToInt(boolean b) {
		return b ? 1 : 0;
	}
	
	public Map<String, Image> getTabImage() {
		String directory = System.getProperty("user.dir")+File.separator+"images"+File.separator;
		Map<String, Image> images = new HashMap<String, Image>();
		
		File[] listOfFiles = new File(directory).listFiles();

		try {
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					images.put(listOfFiles[i].getName().replace(".png", ""), new Image(new FileInputStream(directory+listOfFiles[i].getName())));
				}
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}  
		return images;
	}

	public Case[][] getJeu() {
		return this.jeu;
	}

	public void setJeu(Case[][] jeu) {
		this.jeu = jeu;
	}

	
	public boolean isPlayerLose() {
		return this.playerLose;
	}

	public void setPlayerLose(boolean playerLose) {
		this.playerLose = playerLose;
	}

	public int getGetNbMineSuspecte() {
		return this.getNbMineSuspecte;
	}

	public void setGetNbMineSuspecte(int getNbMineSuspecte) {
		this.getNbMineSuspecte = getNbMineSuspecte;
	}
	
	public boolean playerWin() {
		
		
		for(int i = 0; i<16; i++) {
			for(int j = 0; j<16; j++) {
				if(!this.jeu[i][j].isVisible() && !this.jeu[i][j].isMine()) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	


}
