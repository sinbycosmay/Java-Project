package imt2018008;
import animation.*;
import java.util.*;

public class derivedScene extends Scene
{
  public derivedScene(){ super(); }
  ////////////////////////////////CHECKSCENE//////////////////////////////////////////////
  protected synchronized void checkScene()
  {
    ArrayList<SceneObject> toRemove = new ArrayList<SceneObject>();
    for (SceneObject curr: this.getActors())
    {
      if(collides(curr)) toRemove.add(curr);
    }
    this.getActors().removeAll(toRemove);//Remove actors which have collided
  }
  ///////////////COLLIDES -- CHECKS IF A SCENEOBJECT COLLIDES WITH ANY OTHER SCENEOBJECTS///
  private boolean collides(SceneObject curr)
  {
    for(SceneObject other: this.getActors())///ACTOR-ACTOR COLLISIONS
    {
      if(curr != other && curr.getBBox().intersects(other.getBBox())) return true;
    }
    for(SceneObject other: this.getObstacles())//ACTOR-OBSTACLE COLLISIONS
    {
      if(curr.getBBox().intersects(other.getBBox())) return true;
    }
    return false;
  }
}
