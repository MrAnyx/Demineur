package application;

import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MainController {
	
	@FXML Canvas canvas;
	
	public void initialize() {
		Demineur game = new Demineur();
		
		Map<String, Image> images = game.getTabImage();
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		
		gc.drawImage(images.get("bombe"), 32*0, 0);
		gc.drawImage(images.get("tuile"), 32*1, 0);
		gc.drawImage(images.get("tuile1"), 32*2, 0);
		gc.drawImage(images.get("tuile2"), 32*3, 0);
		gc.drawImage(images.get("tuile3"), 32*4, 0);
		gc.drawImage(images.get("tuile4"), 32*5, 0);
		gc.drawImage(images.get("tuile5"), 32*6, 0);
		gc.drawImage(images.get("tuile6"), 32*7, 0);
		gc.drawImage(images.get("tuile7"), 32*8, 0);
		gc.drawImage(images.get("tuile8"), 32*9, 0);
		gc.drawImage(images.get("tuile9"), 32*10, 0);
		gc.drawImage(images.get("suspecte"), 32*11, 0);
		
		
		
	}
	
}
