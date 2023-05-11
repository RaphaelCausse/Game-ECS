package game.scenes;

import java.util.Stack;
import javafx.stage.Stage;

/**
 * Classe responsable de la gestion de l'ensemble des scenes de l'application, avec une pile de scene.
 */
public class SceneManager
{
	/*----------------------------------------*/
	
	private Stage stage;
	private AbstractScene activeScene;
	private Stack<AbstractScene> sceneStack;
	
	/*----------------------------------------*/

	/**
	 * Constructeur de la classe SceneManager.
	 * @param _stage Stage principal de l'application
	 */
	public SceneManager(Stage _stage)
	{
		stage = _stage;
		sceneStack = new Stack<AbstractScene>();
	}
	
	/**
	 * Ajouter une scene a la pile et l'activer.
	 * @param scene Scene a ajouter et a activer
	 */
	public void pushScene(AbstractScene scene)
	{
        sceneStack.push(scene);
        activeScene = sceneStack.peek();
        stage.setScene(activeScene);
	}
	
	/**
	 * Retirer la scene courante et activer la prochaine scene de la pile.
	 * @param scene Scene a retirer
	 */
	public void popScene(AbstractScene scene)
	{
		if (!sceneStack.isEmpty())
		{
			sceneStack.pop();
			activeScene = sceneStack.peek();
			stage.setScene(activeScene);
		}
	}
	
	/**
	 * Passer a la scene suivante. Retirer la scene courante de la pile et l'ajouter a la fin.
	 */
	public void nextScene()
	{
		if (!sceneStack.isEmpty())
		{
            AbstractScene popped = sceneStack.pop();
            activeScene = sceneStack.peek();
            sceneStack.insertElementAt(popped, 0);
            stage.setScene(activeScene);
        }
	}
	
	/**
	 * Appeler la methode start() de la scene active.
	 */
	public void start()
	{
		activeScene.start();
	}
	
	/**
	 * Appeler la methode update() de la scene active.
	 */
	public void update()
	{
		activeScene.update();
	}
	
	/*----------------------------------------*/
	
	public Stage getStage() { return stage; }

	public AbstractScene getActiveScene() { return activeScene; }
}