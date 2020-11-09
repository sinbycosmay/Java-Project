package others;
import animation.*;
import java.util.ArrayList;
public class DemoSceneObject extends SceneObject
{
 private static int nameval=0;
 private static int direction=0;
 private String name;
 private Point position;
 private Point dest;
 
 public DemoSceneObject()
 {
  nameval++;
  name="rect"+(nameval);
  position=new Point(0,0);
  dest=new Point(0,0);
 }
 public String getObjName()
 {
  return name;
 }

 public Point getPosition()
 {
  return position;
 }
 
 public void setPosition(int x, int y)
 {
  position.setPos(x,y);
 }

 public void setDestPosition(int x, int y)
 {
  dest.setPos(x,y);
 }
 public BBox getBBox()
 {
  return new DemoBBox(new Point(position),new Point(position.getX()+6,position.getY()+6));
 }
 public ArrayList<Point> getOutline()
 {
  ArrayList<Point> a=new ArrayList<>(4);
  a.add(new Point(position));
  a.add(new Point(position.getX()+6,position.getY()));
  a.add(new Point(position.getX()+6,position.getY()+6));
  a.add(new Point(position.getX(),position.getY()+6));
  return a;
 }
 public void updatePos(int deltaT) 
 {
 if(Math.abs(dest.getX()-position.getX())<Math.abs(20) && Math.abs(dest.getY()-position.getY())<Math.abs(20))
   Scene.getScene().getActors().remove(this);
 if (position.getX()<=0 || position.getY()<=0)
   Scene.getScene().getActors().remove(this);  
  Scene s;
  s=Scene.getScene();
  float dx,dy;
  float slope=(dest.getY()-(float)(position.getY()))/(dest.getX()-(float)(position.getX()));
  if(slope>1)
  {
   dy=20;
   if(dest.getY()<position.getY())
    dy*=-1;
   dx=dy/slope;
  }
  else
  {
   dx=20;
   if(dest.getX()<position.getX())
    dx*=-1;
   dy=dx*slope;
  }

  boolean colide=false;
  DemoBBox k;
  Point pnt1;
  Point pnt2;
  ArrayList<SceneObject> lst = new ArrayList<SceneObject>();
//  ArrayList1.addAll(ArrayList2);
  lst.addAll(s.getObstacles());
  lst.addAll(s.getActors());

  for(SceneObject sc : lst)
  {
   if(sc.getBBox().intersects( k=new DemoBBox(pnt1= new Point(getPosition().getX()+(int)dx,getPosition().getY()+(int)dy) ,pnt2= new Point(getPosition().getX()+(int)dx+15,getPosition().getY()+(int)dy+10)) ) )
    colide=true;
  }
  while(colide)
  {
//   colide=false;
  for(SceneObject sc : s.getObstacles())
  {
   if(sc.getBBox().intersects( k=new DemoBBox(pnt1= new Point(getPosition().getX()+(int)dx,getPosition().getY()+(int)dy) ,pnt2= new Point(getPosition().getX()+(int)dx+15,getPosition().getY()+(int)dy+10)) ) )
   {
    colide=true;
    break;
   }
   else
   {
    colide=false;
   }
  }
  if(direction==0)
   dx=-dx;     
  else if(direction==1)
  {
   dx=-dx;
   dy=-dy;
  }


  else if(direction==2)
  {
   dx=0;
   dy=0;
   break;
  }
  direction+=1;
  }
  
  setPosition(getPosition().getX()+(int)dx,getPosition().getY()+(int)dy);
  direction=0;
  slope=(dest.getY()-(float)(position.getY()))/(dest.getX()-(float)(position.getX()));
  if(slope>1)
  {
    dy=20;
    if(dest.getY()<position.getY())
    dy*=-1;
    dx=dy/slope;
  }
  else
  {
    dx=20;
    if(dest.getX()<position.getX())
    dx*=-1;
    dy=dx*slope;
  }
 }
}
