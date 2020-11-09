package others;

import animation.SceneObject;
import animation.Point;
import animation.BBox;
import animation.Scene;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class de_SceneObject extends SceneObject{
    private String objname = "r"; 
    private Point position;
    private Point destposition;
    private BBox bbox;
    private ArrayList outpoints = new ArrayList<Point>();
    private int flag=0;
    private static int dx=5;
    private static int dy=5; 
    private static int outline_resolution = 3;
    private static int max_size=20;
    public de_SceneObject(){
        super();
        
    }
    public String getObjName(){
        return this.objname;
    }
    public Point getPosition(){
        return this.position;
    }
    public void setPosition(int x,int y){
        this.position=new Point(x,y);
        this.setOutline();
    }
    public void setDestPosition(int x,int y){
        this.destposition=new Point(x,y);
    }
    public BBox getBBox(){
        return this.bbox;
    }
    protected ArrayList<Point> getOutline(){
        return this.outpoints;
    }
    public void flagit(){
        flag=1;
    }
    private void setOutline(){
        int i=0;
        int maxx=this.position.getX()+15;
        int maxy=this.position.getY()+15;
        int minx=this.position.getX()-15;
        int miny=this.position.getY()-15;
        Random rand = new Random();
        this.bbox=new de_BBox(new Point(minx,miny),new Point(maxx,maxy));
    }
    private boolean check_and_do(int dx,int dy){
        Scene scene=Scene.getScene();
        ArrayList<SceneObject> actors=scene.getActors();
        ArrayList<SceneObject> obstacles = scene.getObstacles();
        int collision_flag=0;
        this.setPosition(this.getPosition().getX()+dx, this.getPosition().getY()+dy);
        Point min=this.bbox.getMinPt();
        Point max=this.bbox.getMaxPt();
        min.setPos(min.getX()+dx,min.getY()+dy);
        max.setPos(max.getX()+dx,max.getY()+dy);
        BBox newbbox = new de_BBox(min,max);
        this.bbox=newbbox;
        for(SceneObject sceneobject2:actors){           
            if(sceneobject2!=this){
                    BBox bbox2 =sceneobject2.getBBox();   
                    if(bbox2.intersects(this.bbox)){
                        collision_flag=1;
                        break;
                    }
            }
        } 
        for(SceneObject sceneobject3:obstacles){
                if(sceneobject3!=this){
                    BBox bbox3 = sceneobject3.getBBox();   
                    if(bbox3.intersects(this.bbox)){
                        collision_flag=1;
                        break;
                    }
                }
        }
        if(collision_flag==1){
            this.position.setPos(this.position.getX()-dx,this.position.getY()-dy);
            Point min1=this.bbox.getMinPt();
            Point max1=this.bbox.getMaxPt();
            min1.setPos(min1.getX()-dx,min1.getY()-dy);
            max1.setPos(max1.getX()-dx,max1.getY()-dy);
            BBox newbbox1 = new de_BBox(min1,max1);
            this.bbox=newbbox1;
        }
        if(collision_flag==1){
            return false;
        }
        return true;
    }
    protected void updatePos(int deltaT){
        int slope=(this.position.getY()-this.destposition.getY())/(this.position.getX()-this.destposition.getX());
        int xslope=this.destposition.getX()-this.getPosition().getX();
        int yslope=this.destposition.getY()-this.getPosition().getY();
        int dx,dy;
        if(xslope<0){
            dx=-1*de_SceneObject.dx;
        }
        else{
            dx=de_SceneObject.dx;
        }
        if(yslope<0){
            dy=-1*de_SceneObject.dy;    
        }
        else{
            dy=de_SceneObject.dy;
        }
        if(slope<1){
            dy=slope*dx;
        }
        else{
            dx=dy/slope;
        }
        if(check_and_do(dx,dy)){
            return;
        }
        if(check_and_do(dx, -dy)){
            return;
        }
        if(check_and_do(-dx, dy)){
            return;
        }
        if(check_and_do(2*dx, 2*dy)){
            return;
        }
    }

}
