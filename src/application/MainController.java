package application;

import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class MainController {
	
	@FXML Canvas canvas;
	
	@FXML TextField tfTimer;
	
	@FXML Timeline tm;
	
	@FXML Button btnRestart;
	
	@FXML TextField tfNbMines;
	
	private Demineur game;
	private Map<String, Image> images;
	private GraphicsContext gc;
	private int currentTime = 0;
	
	private int tmp;
	
	public void initialize() {
		game = new Demineur();
		
		images = game.getTabImage();
		
		gc = canvas.getGraphicsContext2D();
		
		actualiser();
		
		tm = new Timeline(new KeyFrame(Duration.millis(10), e -> {
			tmp++;
			if(tmp % 100 == 0) {
				this.currentTime++;
				tfTimer.setText(String.format("%03d", this.currentTime));
			}
		}));
		tm.setCycleCount(Timeline.INDEFINITE);
		tm.play();
	}
	
	@FXML public void actionReset(){
		game = new Demineur();
		tm.play();
		this.currentTime = 0;
		actualiser();
	}
	
	@FXML private void actionClic(MouseEvent evt) {
		int clicX = (int) evt.getX();
		int clicY = (int) evt.getY();
		MouseButton bt = evt.getButton();
		
		if(bt == MouseButton.PRIMARY && !game.isPlayerLose()) {
			if(game.playerWin()) {
				btnRestart.setText("Player Win");
			}else {
				if(game.getJeu()[clicX/32][clicY/32].isMine()) {
					game.setPlayerLose(true);
					tm.stop();
				}
				if(game.getJeu()[clicX/32][clicY/32].getNbMineAround() == 0) {
					game.expansion(clicX/32, clicY/32);
				}else {
					game.getJeu()[clicX/32][clicY/32].setVisible(true);
				}
				actualiser();
			}
		}else if(bt == MouseButton.SECONDARY && !game.isPlayerLose()) {
			if(game.playerWin()) {
				btnRestart.setText("Player Win");
			}else {
				if(!game.getJeu()[clicX/32][clicY/32].isVisible()) {
					if(game.getJeu()[clicX/32][clicY/32].isMineForPlayer()) {
						game.getJeu()[clicX/32][clicY/32].setMineForPlayer(false);
						game.setGetNbMineSuspecte(game.getGetNbMineSuspecte()-1);
						tfNbMines.setText(String.format("%02d", game.getGetNbMineSuspecte()));
					}else {
						game.getJeu()[clicX/32][clicY/32].setMineForPlayer(true);
						game.setGetNbMineSuspecte(game.getGetNbMineSuspecte()+1);
						tfNbMines.setText(String.format("%02d", game.getGetNbMineSuspecte()));
					}
					actualiser();
				}
			}
		}
	}
	
	private void actualiser() {
		for(int i = 0; i<16; i++) {
			for(int j = 0; j<16; j++) {
				if(!game.getJeu()[i][j].isVisible()) {
					if(!game.getJeu()[i][j].isMineForPlayer()) gc.drawImage(images.get("tuile"), i*32, j*32);
					else gc.drawImage(images.get("suspecte"), i*32, j*32);
				}else {
					if(game.getJeu()[i][j].isMine()) gc.drawImage(images.get("bombe"), i*32, j*32);
					else {
						gc.drawImage(images.get(String.format("tuile%d", game.getJeu()[i][j].getNbMineAround())), i*32, j*32);
					}
				}
			}
		}
	}
	
}
