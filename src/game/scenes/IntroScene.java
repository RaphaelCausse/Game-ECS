package game.scenes;

import java.util.ArrayList;
import java.util.List;
import game.Game;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import utils.Settings.App;

/**
 * Class responsible of introduction scene.
 * @see AbstractScene
 */
public class IntroScene extends AbstractScene
{
	/*----------------------------------------*/
	
	/*----------------------------------------*/
	
	/**
	 * Constructor of IntroScene class.
	 * @param _sceneManager
	 * @param _root
	 */
	public IntroScene(SceneManager _sceneManager, Pane _root)
	{
		super(_sceneManager, _root);
		initialize();
	}

	@Override
	public void initialize()
	{
		BorderPane pane = new BorderPane();
		pane.setPrefSize(App.SCREEN_W, App.SCREEN_H);
		pane.setStyle("-fx-background-color: white;");
		VBox centerBox = new VBox();
		centerBox.setAlignment(Pos.CENTER);
		centerBox.setSpacing(20);
		
		// Title text
	    Text titleText = new Text("Project Unknown: Level One");
	    titleText.setFont(Font.font("Arial", FontWeight.BOLD, 38));
	    titleText.setTextAlignment(TextAlignment.CENTER);
	    pane.setTop(titleText);
	    BorderPane.setAlignment(titleText, Pos.CENTER);

	    // Buttons
	    List<Button> buttons = new ArrayList<>();
	    Button playButton = new Button("Play");
	    playButton.setPrefSize(200, 50);
	    playButton.setStyle("-fx-font-size: 24px;");
	    playButton.setTextAlignment(TextAlignment.CENTER);
	    playButton.setOnAction(e ->
	    {
	    	Game.sceneManager.removeScene("IntroScene");    
	        Game.sceneManager.activate("GameScene");
	        
	    });
	    buttons.add(playButton);

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
	}

	@Override
	public void update()
	{
	}

	/*----------------------------------------*/
}
