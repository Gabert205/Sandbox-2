import java.awt.*;

public class Character {
    public Vector pos;
    public Vector vel;
    public Vector acc;

    public Character(double x, double y){
        pos = new Vector(x, y);
        vel = new Vector();
        acc = new Vector();
    }

    //==================================================================================================================

    //region Gets and Sets


    public int getQuadrantX(){
        return (int) (pos.getX() * 3);
    }

    public int getQuadrantY(){
        return (int) (pos.getY() * 3);
    }


    //endregion

    //==================================================================================================================

    public void update(){
        vel.update(acc);
        pos.update(vel);
    }

    //==================================================================================================================

    //draws the Character as a white rectangle where x is in the middle and y is at the very bottom
    public void draw(){
        StdDraw.setPenColor(new Color(0xFFFFFF));
        StdDraw.filledRectangle(pos.getX() ,
                pos.getY() + Screen.CELLSIZE + Screen.CELLSIZE / 2,
                .025, .05);
    }
}
