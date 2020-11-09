package imt2018008;
import animation.*;
import java.util.*;
public class derivedSceneObject extends SceneObject
{
  /////CONSTRUCTOR///////////////////////////////////
  public derivedSceneObject()
  {
    super();
    objName = "imt2018008" + nextObjectNumber++;
  }
  //////////CONSTRUCTOR///////////////////////////
  //////////GETTERS///////////////////////////////
  public String getObjName() { return objName; }
  public Point getPosition() { return position;}

  //The BBox is hardcoded because it is more convenient than
  //manually iterating through the list of outline points.
  public BBox getBBox(){ return new derivedBBox(new Point(getPosition().getX() - 5, getPosition().getY() - 5), new Point(getPosition().getX() + 5, getPosition().getY() + 5)); }

  protected ArrayList<Point> getOutline()
  {
    ArrayList<Point> toReturn = new ArrayList<Point>();
    Point currPos = this.getPosition();
    toReturn.add(new Point(currPos.getX() - 5, currPos.getY() - 5));
    toReturn.add(new Point(currPos.getX() - 5, currPos.getY() + 5));
    toReturn.add(new Point(currPos.getX() + 5, currPos.getY() + 5));
    toReturn.add(new Point(currPos.getX() + 5, currPos.getY() - 5));
    return toReturn;
  }
  //GETTERS////////////////////////////////////////
  //SETTERS/////////////////////////////////////////
  public void setPosition(int x, int y) { position = new Point(x, y);}
  public void setDestPosition(int x, int y)  {  destination = new Point(x, y); }
  //SETTERS//////////////////////////////////////////
  //UPDATEPOS////////////////////////////////////////////////////////
  protected synchronized void updatePos(int deltaT)
  {
    Scene thisScene = derivedScene.getScene();//The current scene
    boolean intersecting;//A flag variable

    int delX = destination.getX() - getPosition().getX();//The distance to be moved in X
    int delY = destination.getY() - getPosition().getY();// " " in Y

    ArrayList<SceneObject> actors = thisScene.getActors();//List of actors
    ArrayList<SceneObject> obstacles = thisScene.getObstacles();//List of obstacles

    while(Math.abs(delX) > 14 || Math.abs(delY) > 14)
    {//To ensure that the actor doesn't move too far too fast.
      delX /= 2;
      delY /= 2;
    }
    do{//Check for intersections. Stay in loop as long as intersecting
      //1) Move by delX, delY.
      //2) Check for intersection
      //3) If intersecting, move back and move in a perpendicular direction

      intersecting = false;
      //1)
      setPosition(getPosition().getX() + delX, getPosition().getY() + delY);

      //2)
      for(SceneObject other: thisScene.getActors())//Actor-actor collisions
      {
        if(other != this && this.getBBox().intersects(other.getBBox()))
        {
          intersecting = true;
        }
      }

      for(SceneObject other: thisScene.getObstacles())//Actor-obsstacle collisions
      {
        if(this.getBBox().intersects(other.getBBox()))
        {
          intersecting = true;
        }
      }
      if(intersecting) //3)
      {
        setPosition(getPosition().getX() - delX, getPosition().getY() - delY);
        int temp;
        temp = delX;
        delX = -delY;
        delY = temp;
      }
    }while (intersecting);
  }
  //UPDATEPOS/////////////////////////////////////////////
  //VARS/////////////////////////////////////////////
  protected String objName;
  private Point position;
  private Point destination;
  static int nextObjectNumber = 0;
  //VARS//////////////////////////////////////////////////
}
