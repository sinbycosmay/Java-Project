package test;

import animation.Scene;

public class MyCurrentScene extends Scene{
    protected void checkScene()
			{
				//System.out.println("H");
				
				//System.out.print(this.getActors());
				
				for(int i = 0;i<this.getActors().size();i++)
				{
				for(int j=0;j<this.getActors().size() && i!=j;j++)
				{
					if(this.getActors().get(i) != this.getActors().get(j))
					{
						if(this.getActors().get(i).getBBox().intersects(this.getActors().get(j).getBBox()))
						{
							this.getActors().remove(i);
							this.getActors().remove(j);
						}
					}
				}
					
				for(int j=0;j<this.getObstacles().size();j++)
				{
					if(this.getActors().get(i).getBBox().intersects(this.getObstacles().get(j).getBBox()))
					{
						//System.out.print("removed" +  this.getActors().get(i).getPosition().getX() + " " + this.getActors().get(i).getPosition().getY() + ":" + this.getObstacles().get(j).getPosition().getX() + " " + this.getObstacles().get(j).getPosition().getY());
						this.getActors().remove(i);
					}
				}
			}
		}
}