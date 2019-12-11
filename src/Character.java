import java.awt.*;

public class Character {
    public Vector pos;
    public Vector vel;
    public Vector acc;
    private boolean facingRight;   //if the Character is facing to the right

    public Character(double x, double y) {
        pos = new Vector(x, y);
        vel = new Vector();
        acc = new Vector();
        facingRight = true;
    }

    //==================================================================================================================

    //region Gets and Sets

    //returns the x-value of the Quadrant it is in
    public int getQuadrantX() {
        return (int) ( pos.getX() * 3 );
    }

    //returns the x-value of the Quadrant it is in
    public int getQuadrantY() {
        return (int) ( pos.getY() * 3 );
    }

    //returns the x-value of the Cell it is in
    public int getCellX(){
        if(pos.getX() < 0){
            return (int) (-Math.abs((pos.getX() * 30) % 10) + 10);
        }

        return (int) (pos.getX() * 30) % 10;
    }

    //returns the y-value of the Cell it is in
    public int getCellY(){
        if(pos.getY() < 0){
            return (int) (-Math.abs((pos.getY() * 30) % 10) + 10);
        }
        return (int) (pos.getY() * 30) % 10;
    }


    //endregion

    //==================================================================================================================

    public void movement() {

        //left
        if (StdDraw.isKeyPressed(37)) {
            pos.addX(-.003);
            facingRight = false;
        }

        //right
        if (StdDraw.isKeyPressed(39)) {
            pos.addX(.003);
            facingRight = true;
        }

    }

    public void jump(){
        vel.addY(.003);
    }

    //==================================================================================================================

    public void update() {
        vel.update(acc);
        pos.update(vel);

    }

    //==================================================================================================================

    //draws the Character as a white rectangle where x and y are in the center of the rectangle
    public void draw() {
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledRectangle(pos.getX(),
                pos.getY() + Screen.CELLSIZE / 2,
                .025, .05);

        StdDraw.setPenColor(StdDraw.BLACK);

        //draw the center eye
        StdDraw.line(pos.getX(), pos.getY() + Screen.CELLSIZE, pos.getX(), pos.getY() + Screen.CELLSIZE + Screen.CELLSIZE / 2);

        //draw the other eye
        if(facingRight){
            StdDraw.line(pos.getX() + Screen.CELLSIZE / 2, pos.getY() + Screen.CELLSIZE,
                    pos.getX() + Screen.CELLSIZE / 2, pos.getY() + Screen.CELLSIZE + Screen.CELLSIZE / 2);
        }
        else{
            StdDraw.line(pos.getX() - Screen.CELLSIZE / 2, pos.getY() + Screen.CELLSIZE,
                    pos.getX() - Screen.CELLSIZE / 2, pos.getY() + Screen.CELLSIZE + Screen.CELLSIZE / 2);
        }
    }
}
