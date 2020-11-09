package others;

import animation.Point;
import animation.BBox;

class de_BBox implements BBox{
    private Point minpoint,maxpoint;
    
    public de_BBox(Point min,Point max){
        this.minpoint= min;
        this.maxpoint = max;
    }
    public Point getMinPt(){
        return  this.minpoint;
    }
    public Point getMaxPt(){
        return this.maxpoint;
    }
    private boolean is_point_inside(Point a){
        if(a.getX() >= this.minpoint.getX() && a.getX() <= this.maxpoint.getX() && a.getY() >=this.minpoint.getY() && a.getY() <= this.maxpoint.getY()){
            return true;
        }
        return false;
    }
    public boolean intersects(BBox b){
        Point corner1=new Point(b.getMaxPt().getX(),b.getMinPt().getY());
        Point corner2= new Point(b.getMinPt().getX(),b.getMaxPt().getY());
        if(this.is_point_inside(b.getMinPt()) || this.is_point_inside(b.getMaxPt()) || this.is_point_inside(corner1) || this.is_point_inside(corner2)){
            return true;
        }  
        return false;
    }
}
