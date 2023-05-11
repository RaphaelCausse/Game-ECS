package app;
	
import game.Game;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
	/*----------------------------------------*/
	
	private Game game;
	
	/*----------------------------------------*/
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception
	{
		game = new Game(stage);
		game.run();
	}
	
//	@Override
//	public void stop()
//	{
//		game.stop();
//	}
	
	/*----------------------------------------*/
}
