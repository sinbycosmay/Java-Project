package others;

import java.util.ArrayList;

import animation.BBox;
import animation.Point;
import animation.SceneObject;
import animation.Scene;

public class TestObject extends SceneObject implements BBox
{

	private Point minpt;
	private Point maxpt;
	private Point current;
	private Point destination;
	private BBox b1; 
	private static int id=0;
    String name;

	public TestObject()
	{
		current= new Point(0,0);
		destination = new Point(0,0);;
		minpt =  new Point(0,0);
        maxpt =  new Point(0,0);
        name = " " + id++;


	}
	@Override
	public String getObjName() {
		return this.name;
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return current;
	}

	@Override
	public void setPosition(int x, int y) {
		current.setPos(x,y);
		
	}

	public void setDestPosition(int x, int y) {
		destination.setPos(x,y);
	}
	
	@Override
	public BBox getBBox() {
		
		return this;
	}
	public Point getMinPt()
	{
		return current;
	}

	public Point getMaxPt()
	{
		return this.getOutline().get(2);
	}

	@Override
	protected ArrayList<Point> getOutline() {
		
		ArrayList<Point> a=new ArrayList<>();
		
		Point p=new Point(current);
		a.add(p);
		Point p1=new Point(current.getX(),current.getY()+40);
		//p.setPos(p.getX(),p.getY()+40);
		a.add(p1);
		Point p2=new Point(current.getX()+40,current.getY()+40);
		a.add(p2);
		Point p3=new Point(current.getX()+40,current.getY());
		a.add(p3);


		return a;
	}

	public boolean intersects(BBox b)
	{

		if(b.getMinPt().getX()>this.getMaxPt().getX() || b.getMinPt().getY()>this.getMaxPt().getY() || this.getMinPt().getX()>b.getMaxPt().getX() || this.getMinPt().getY()>b.getMaxPt().getY())
			return false;

		else
			return true;
	}

	@Override
	protected void updatePos(int deltaT) {
	    
		if(current.getX()!=destination.getX() && current.getY()!=destination.getY())
		{
				int c=0;
				int v=0;
				if(current.getX()<destination.getX())
				{
						current.setPos(current.getX()+deltaT/100,current.getY());
						c=1;
				}
				else if(current.getX()>destination.getX())
				{
					current.setPos(current.getX()-deltaT/100,current.getY());
					c=2;
				}
				if(current.getY()<destination.getY())
				{
					current.setPos(current.getX(),current.getY()+deltaT/100);
					v=1;
				}
				else if(current.getY()>destination.getY())
				{
					current.setPos(current.getX(),current.getY()-deltaT/100);
					v=2;
				}
				
				
				Scene s1=Scene.getScene();
         		int a=s1.getActors().size();
         		int b=s1.getObstacles().size();
         		
         			for(int j=0;j<b;j++)
         			{
         				if(this.getBBox().intersects(s1.getObstacles().get(j).getBBox()))
         				{

         					if(c==1 && v==1)
         					{
         						current.setPos(current.getX()-deltaT/100,current.getY()-deltaT/100);
         						current.setPos(current.getX()+deltaT/100,current.getY());
         					}
         					else if(c==1 && v==2)
         					{
         						current.setPos(current.getX()-deltaT/100,current.getY()+deltaT/100);
         						current.setPos(current.getX()+deltaT/100,current.getY());

         					}
         					else if(c==2 && v==2)
         					{
         						current.setPos(current.getX()+deltaT/100,current.getY()+deltaT/100);
         						current.setPos(current.getX()+deltaT/100,current.getY());

         					}
         					else if(c==2 && v==1)
         					{
         						current.setPos(current.getX()+deltaT/100,current.getY()-deltaT/100);
         						current.setPos(current.getX()+deltaT/100,current.getY());

         					}
         				}

         			}
         			for(int k=0;k<a;k++)
         			{

         				if(this.getBBox().intersects(s1.getActors().get(k).getBBox()) && this!=s1.getActors().get(k))
         				{
         					if(c==1 && v==1)
         					{
         						current.setPos(current.getX()-deltaT/100,current.getY()-deltaT/100);
         						current.setPos(current.getX()+deltaT/100,current.getY());
         					}
         					else if(c==1 && v==2)
         					{
         						current.setPos(current.getX()-deltaT/100,current.getY()+deltaT/100);
         						current.setPos(current.getX()+deltaT/100,current.getY());

         					}
         					else if(c==2 && v==2)
         					{
         						current.setPos(current.getX()+deltaT/100,current.getY()+deltaT/100);
         						current.setPos(current.getX()+deltaT/100,current.getY());

         					}
         					else if(c==2 && v==1)
         					{
         						current.setPos(current.getX()+deltaT/100,current.getY()-deltaT/100);
         						current.setPos(current.getX()+deltaT/100,current.getY());

         					}
                        	
                    	}


         			}


         		}
         	


		}}