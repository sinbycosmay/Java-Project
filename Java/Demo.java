import animation.Scene;
import animation.SceneObject;
import animation.View;
import test.TestObject;
import test.TestScene;
import test.DemoScene;
import test.MyObject;
import imt2018008.*;
import others.*;


// Driver class to set up and exercise the animation
public class Demo {

	public static void main(String[] args) {
		Scene scene = new TestScene(); // replace with your implementation

		// populate the scene with objects implemented by the team
		for (int i = 0; i < 6; i++) {
			SceneObject s = new MyObject(); // replace with your implementation
			s.setPosition(i * 50, i * 50);
			scene.addObstacle(s); // using appropriate derived classes
		}

		SceneObject s = new MyObject(); // replace with your implementation
		s.setPosition(500,200); // these will be changed for the demo
		s.setDestPosition(20, 0);
		scene.addActor(s); // using appropriate derived classes
   

			 s = new de_SceneObject(); // replace with your implementation
			 s.setPosition(500,50); // these will be changed for the demo
			 s.setDestPosition(0, 0);
			 scene.addActor(s); // using appropriate derived classes
		
			 s = new DemoSceneObject(); // replace with your implementation
			 s.setPosition(500,100); // these will be changed for the demo
			 s.setDestPosition(0, 0);
			 scene.addActor(s); // using appropriate derived classes
		
			 s = new derivedSceneObject(); // replace with your implementation
			 s.setPosition(500,50); // these will be changed for the demo
			 s.setDestPosition(0, 0);
			 scene.addActor(s); // using appropriate derived classes

			 s = new mySceneObject(); // replace with your implementation
			 s.setPosition(500,50); // these will be changed for the demo
			 s.setDestPosition(0, 0);
			 scene.addActor(s); // using appropriate derived classes
		
		
		// View view = new DemoTextView();
		View view = new DemoSwingView();

		scene.setView(view);

		view.init();

	}

}
