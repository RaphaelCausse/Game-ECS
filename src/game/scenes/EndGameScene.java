package game.scenes;

import java.util.ArrayList;
import java.util.List;

import game.Game;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import utils.Settings.App;
import utils.Settings.GameStatus;

/**
 * Class responsible of endgame scene.
 * @see AbstractScene 
 */
public class EndGameScene extends AbstractScene
{
	/*----------------------------------------*/
	
	private BorderPane pane;
	private VBox centerBox;
	private Text mainText;
	private List<Button> buttons;
	
	/*----------------------------------------*/
	
	public EndGameScene(SceneManager _sceneManager, Pane _root)
	{
		super(_sceneManager, _root);
		initialize();
	}

	@Override
	public void initialize()
	{
		pane = new BorderPane();
		pane.setPrefSize(App.SCREEN_W, App.SCREEN_H);
		pane.setStyle("-fx-background-color: white;");
		centerBox = new VBox();
		centerBox.setAlignment(Pos.CENTER);
		centerBox.setSpacing(20);
		
		// Main text
	    mainText = new Text();
	    mainText.setTextAlignment(TextAlignment.CENTER);
	    centerBox.getChildren().add(mainText);

	    // Buttons
	    buttons = new ArrayList<>();

	    Button quitButton = new Button("Quit");
	    quitButton.setPrefSize(200, 50);
	    quitButton.setStyle("-fx-font-size: 24px;");
	    quitButton.setTextAlignment(TextAlignment.CENTER);
	    quitButton.setOnAction(e ->
	    {
	        Game.sceneManager.getStage().close();
	    });
	    buttons.add(quitButton);

	    centerBox.getChildren().addAll(buttons);
	    pane.setCenter(centerBox);
	    root.getChildren().add(pane);
	    
	    // Credits text
	    Text creditsText = new Text("2023 - Raphael CAUSSE");
	    creditsText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	    creditsText.setTextAlignment(TextAlignment.RIGHT);
	    pane.setBottom(creditsText);
	}
	
	@Override
	public void start()
	{
		if (Game.gameStatus == GameStatus.GAME_WIN)
	    {
	    	mainText.setText("GAME WIN");;
	    	mainText.setFill(Color.GREEN);
	    	mainText.setFont(Font.font("Arial", FontWeight.BOLD, 48));
	    }
	    else if (Game.gameStatus == GameStatus.GAME_OVER)
	    {
	    	mainText.setText("GAME OVER");;
	    	mainText.setFill(Color.RED);
	    	mainText.setFont(Font.font("Arial", FontWeight.BOLD, 48));
	    }
	}

	@Override
	public void update()
	{
	}

	/*----------------------------------------*/
}
