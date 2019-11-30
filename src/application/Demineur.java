package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class Demineur {
	
	private Case[][] jeu;
	private boolean playerWin;
	private int getNbMineSuspecte;
	
	Demineur(){
		for(int i = 0; i<16; i++) {
			for(int j = 0; j<16; j++){
				this.jeu[i][j] = new Case(false,false,false,0);
			}
		}
		this.playerWin = false;
		this.getNbMineSuspecte = 0;
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
	


}
