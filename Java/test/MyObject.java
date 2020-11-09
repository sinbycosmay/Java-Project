package test;

import java.util.ArrayList;
import java.util.Random;


import animation.BBox;
import animation.Point;
import animation.SceneObject;
import animation.Scene;
 
 
 
public class MyObject extends SceneObject implements BBox{

	public MyObject()
	{
		MoveLeftPos = true;
		MoveRightPos = true;
		MoveUpPos = true;
		MoveDownPos = true;
	
		current_P = new Point(0,0);
		Dest_P =  new Point(0,0);;
		min_pt =  new Point(0,0);
        max_pt =  new Point(0,0);
        wait = 0;
		vel = 10;

		CurrentScene = Scene.getScene();
	}


	@Override
	public String getObjName() {
		// TODO Auto-generatedObjectObject method stub
		return "lala";
	}

	@Override
	public Point getPosition() {
		return current_P;
	}

	@Override
	public void setPosition(int x, int y) {
		current_P.setPos(x,y);
	}

	public Point getMinPt()
	{
		return this.getOutline().get(0);
	}

	public Point getMaxPt()
	{
		return this.getOutline().get(3);
	}


	public void setDestPosition(int x, int y) {
		Dest_P.setPos(x,y);
    }
    

	public boolean intersects(BBox b)
	{
		if (this.getMaxPt().getX() < b.getMinPt().getX() ||	b.getMaxPt().getX() < this.getMinPt().getX()) { 
            return false; 
        } 
  
        // If one rectangle is above other  
        if (this.getMinPt().getY() > b.getMaxPt().getY() || b.getMinPt().getY() > this.getMaxPt().getY()) { 
            return false; 
        } 
  
        return true; 
  		//return false;
    } 

	
	@Override
	public BBox getBBox() {
		return (BBox)this;
	}

	@Override
	public ArrayList<Point> getOutline() {
		ArrayList<Point> Rect = new ArrayList<Point>();
		Point temp  = new Point(current_P);
		Rect.add(temp);
		Rect.add(new Point(temp.getX() + 10,temp.getY()));
		Rect.add(new Point(temp.getX() , temp.getY() + 10));
		Rect.add(new Point(temp.getX() + 10,temp.getY() + 10));
		return Rect;
	}

	protected void collisionHandleX(int deltaT)
	{
		Random random = new Random();
		int way = random.nextInt(2);
        while((state == 1 && !MoveLeftPos) || (state == 2 && !MoveRightPos))
		{
        if(current_P.getX() < 0)
        {
            MoveLeftPos = false;
        }
            
        if(way == 0)
			if(MoveUpPos && current_P.getY() > 0)
				current_P.setPos(current_P.getX(),current_P.getY()  - vel);
			else
				current_P.setPos(current_P.getX(),current_P.getY()  + vel);

		if(way == 1)
			if(MoveDownPos)
				current_P.setPos(current_P.getX(),current_P.getY()  + vel);
			else
				current_P.setPos(current_P.getX(),current_P.getY()  - vel);
        CollisionChecker(deltaT);		
		}
	}

	protected void collisionHandleY(int deltaT)
	{
		Random random = new Random();
		int way = random.nextInt(2);
		while((state == 3 && !MoveDownPos) || (state == 4 && !MoveUpPos))
		{
		if(current_P.getX() < 0)
		{
			MoveLeftPos = false;
		}
		if(way == 0)
			if(MoveRightPos)
				current_P.setPos(current_P.getX()   + vel,current_P.getY());
			else
				current_P.setPos(current_P.getX()  - vel,current_P.getY());

		if(way == 1)
			if(MoveLeftPos)
				current_P.setPos(current_P.getX()  - vel,current_P.getY());
			else
				current_P.setPos(current_P.getX()  + vel,current_P.getY());
		CollisionChecker(deltaT);
        }	
	}

	
	protected BBox MoveBBoxU() {
		BBox temp = this.getBBox();
		temp.getMaxPt().setPos(temp.getMaxPt().getX(),temp.getMaxPt().getY() + 5);
		temp.getMinPt().setPos(temp.getMinPt().getX(),temp.getMinPt().getY() + 5);
		return temp;
	}

	
	protected BBox MoveBBoxL() {
		BBox temp = this.getBBox();
		temp.getMaxPt().setPos(temp.getMaxPt().getX() - 5,temp.getMaxPt().getY());
		temp.getMinPt().setPos(temp.getMinPt().getX() - 5,temp.getMinPt().getY());
		return temp;
	}

	
	protected BBox MoveBBoxR() {
		BBox temp = this.getBBox();
		temp.getMaxPt().setPos(temp.getMaxPt().getX() + 5,temp.getMaxPt().getY());
		temp.getMinPt().setPos(temp.getMinPt().getX() + 5,temp.getMinPt().getY());
		return temp;
	}

	
	protected BBox MoveBBoxD() {
		BBox temp = this.getBBox();
		temp.getMaxPt().setPos(temp.getMaxPt().getX(),temp.getMaxPt().getY() - 5);
		temp.getMinPt().setPos(temp.getMinPt().getX(),temp.getMinPt().getY() - 5);
		return temp;
	}

	protected void CollisionChecker(int deltaT)
	{
        int flagUp = 0;
        int flagDown = 0;
        int flagLeft = 0;
        int flagRight = 0;
            
		for(int i= 0;i<CurrentScene.getObstacles().size();i++)
		{
			
			BBox OtherActorBBox = CurrentScene.getObstacles().get(i).getBBox();
			
			if(MoveBBoxD().intersects(OtherActorBBox) || current_P.getY() < 0 || flagDown == 1)
			{
				MoveDownPos = false;
				flagDown = 1;
			}
			else
			{
				MoveDownPos = true;
			}
			if(MoveBBoxL().intersects(OtherActorBBox) || current_P.getX() < 0 || flagLeft == 1)
			{
				MoveLeftPos = false;
				flagLeft = 1;
			}
			else
			{
				MoveLeftPos = true;
			}
			
			if(MoveBBoxR().intersects(OtherActorBBox) || flagRight == 1)
			{
				MoveRightPos = false;
				flagRight = 1;
			}
			else
			{
				MoveRightPos = true;
			}
			
			if(MoveBBoxU().intersects(OtherActorBBox) || flagUp == 1)
			{
				MoveUpPos = false;
				flagUp = 1;
			}
			else
			{
				MoveUpPos = true;
            }	
        }
        
        for(int i= 0;i<CurrentScene.getActors().size() && CurrentScene.getActors().get(i) != this;i++)
		{
			
			BBox OtherActorBBox = CurrentScene.getActors().get(i).getBBox();
			
			if(MoveBBoxD().intersects(OtherActorBBox) || current_P.getY() < 0 || flagDown == 1)
			{
				MoveDownPos = false;
                flagDown = 1;
                wait = 1;
			}
			else
			{
				MoveDownPos = true;
			}
			if(MoveBBoxL().intersects(OtherActorBBox) || current_P.getX() < 0 || flagLeft == 1)
			{
				MoveLeftPos = false;
                flagLeft = 1;
                wait = 1;
			}
			else
			{
				MoveLeftPos = true;
			}
			
			if(MoveBBoxR().intersects(OtherActorBBox) || flagRight == 1)
			{
				MoveRightPos = false;
				flagRight = 1;
                wait = 1;
            }
			else
			{
				MoveRightPos = true;
			}
			
			if(MoveBBoxU().intersects(OtherActorBBox) || flagUp == 1)
			{
				MoveUpPos = false;
                flagUp = 1;
                wait = 1;
			}
			else
			{
				MoveUpPos = true;
            }	
		}
	}


	@Override
	protected synchronized void updatePos(int deltaT) {
		CollisionChecker(deltaT);
        System.out.print(MoveLeftPos);
        if(current_P.getX() < 0)
		{
			MoveLeftPos = false;
		}
		
		if(Math.abs(current_P.getX() - Dest_P.getX()) < vel)
			current_P.setPos(Dest_P.getX(),current_P.getY());
		if(Math.abs(current_P.getY() - Dest_P.getY()) < vel)
			current_P.setPos(current_P.getX(),Dest_P.getY());
		if(current_P.getX() != Dest_P.getX() || current_P.getY() != Dest_P.getY())
		{
			if(Dest_P.getX() < current_P.getX())
			{
			if(MoveLeftPos)	{
				current_P.setPos(current_P.getX() - vel,current_P.getY());
				state = 1;
			}
			else
				collisionHandleX(deltaT);
			}
			if(Dest_P.getX() > current_P.getX())
			{
			if(MoveRightPos) {
				current_P.setPos(current_P.getX() + vel,current_P.getY());
				state = 2;
			}
			else
				collisionHandleX(deltaT);
				if(state == 1)
					MoveLeftPos = true;
				else
					MoveRightPos = true;
				
			}
		}
		if(current_P.getX() == Dest_P.getX() && current_P.getY() != Dest_P.getY())
		{
			if(Dest_P.getY() < current_P.getY())
			{
			if(MoveDownPos)	{
				current_P.setPos(current_P.getX(),current_P.getY()  - vel);
				state = 3;
			}
			else
				collisionHandleY(deltaT);
			}
			if(Dest_P.getY() > current_P.getY())
			{
			if(MoveUpPos) {
				current_P.setPos(current_P.getX(),current_P.getY() + vel);
				state = 4;
			}
			else
				collisionHandleY(deltaT);
			}
		}
	}

	Boolean MoveLeftPos;
	Boolean MoveRightPos;
	Boolean MoveUpPos;
	Boolean MoveDownPos;
    int state;
    int wait;
	
	//SceneObject CollisionWith;
    Point current_P;
	Point Dest_P;
	BBox collision_box;
	Point min_pt;
	Point max_pt;

	Point UpPos;
	Point LeftPos;
	Point RightPos;
	Point DownPos;

	int vel;

	Scene CurrentScene;
	
}