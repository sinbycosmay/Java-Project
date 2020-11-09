package imt2018008;
import animation.*;
public class derivedBBox implements BBox
{
  private Point minPoint, maxPoint;//Bottom-left and top-right points

  public derivedBBox(Point minPoint, Point maxPoint)
  {
    this.minPoint = minPoint;
    this.maxPoint = maxPoint;
  }

  //Getters
  public Point getMinPt() { return minPoint;}
  public Point getMaxPt() { return maxPoint;}

  //Check for bbox-bbox intersections.
  public boolean intersects(BBox b)
  {
    boolean toReturn = (this.getMinPt().getX() <= b.getMaxPt().getX() && b.getMinPt().getX() <= this.getMaxPt().getX() && this.getMinPt().getY() <= b.getMaxPt().getY() && b.getMinPt().getY() <= this.getMaxPt().getY());
    return toReturn;
  }
}
