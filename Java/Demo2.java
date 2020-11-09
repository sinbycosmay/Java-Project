import animation.Scene;
import animation.SceneObject;
import animation.View;
import test.TestObject;
import test.MyCurrentScene;
import test.MyObject;
import test.MyObject;
import imt2018008.*;
import others.*;


// Driver class to set up and exercise the animation
public class Demo2 {

	public static void main(String[] args) {
		Scene scene = new TestScene(); // replace with your implementation

		// populate the scene with objects implemented by the team
		for (int i = 0; i < 6; i++) {
			SceneObject s = new MyObject(); // replace with your implementation
			s.setPosition(i * 50, i * 50);
			scene.addObstacle(s); // using appropriate derived classes
		}

        for (int i = 0; i < 6; i++) {
            SceneObject s = new MyObject(); // replace with your implementation
            s.setPosition(500 - i * 50, 50 + i * 50);
            s.setDestPosition(50, 0);
			scene.addActor(s); // using appropriate derived classes
        }
        
        View view = new DemoSwingView();

		scene.setView(view);

		view.init();

    }
}
