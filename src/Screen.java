import java.awt.*;

public class Screen {
    private Game game;   //the Game that holds all the gameplay
    public static double centerOfScreenX;  //the x-value of the center of the screen
    public static double centerOfScreenY;  //the y-value of the center of the screen
    public static final double VIEWSIZE = .5;   //the distance that the Character can see
    public final static double CELLSIZE = 1 / 30.;   //the size of the Cells (duh)

    public Screen(int screenWidth, int screenHeight) {
        StdDraw.setCanvasSize(screenWidth, screenHeight);
        centerOfScreenX = 0;
        centerOfScreenY = 0;
        game = new Game();

        run();
    }

    //==================================================================================================================

    //region Gets and Sets


    private double getScreenX(int quadrantX, int cellX){
        double x = 0;

        x += quadrantX * (Quadrant.QUADRANTSIZE * CELLSIZE);

        x += cellX * CELLSIZE + CELLSIZE / 2;

        return x;
    }

    private double getScreenY(int quadrantY, int cellY){
        double y = 0;

        y += quadrantY * (Quadrant.QUADRANTSIZE * CELLSIZE);

        y += cellY * CELLSIZE + CELLSIZE / 2;

        return y;
    }

    private void setCenterOfScreen(){
        centerOfScreenX = game.character.pos.getX();
        centerOfScreenY = game.character.pos.getY();

        StdDraw.setXscale(centerOfScreenX - VIEWSIZE, centerOfScreenX + VIEWSIZE);
        StdDraw.setYscale(centerOfScreenY - VIEWSIZE, centerOfScreenY + VIEWSIZE);
    }


    //endregion

    //==================================================================================================================

    public void run(){
        int count = 0;
        while(true){
            count++;
            //if(count % 60 == 0) System.out.println(count);
            game.update();
            setCenterOfScreen();
            draw();
        }
    }
    //draws the entire screen
    private void draw() {
        StdDraw.clear();

        //for every Quadrant in game's quadrants
        for (int i = 0 ; i < game.getQuadrantsSize() ; i++) {
            Quadrant quadrant = game.getQuadrant(i);

            //draw only is the cell is active
            if(quadrant.isActive()) {

                //for every Cell in the Quadrant - draw the Cell
                for (int x = 0 ; x < quadrant.cells.length ; x++) {
                    for (int y = 0 ; y < quadrant.cells[x].length ; y++) {

                        drawCell(getScreenX(quadrant.x, x),
                                getScreenY(quadrant.y, y),
                                quadrant.getCell(x, y).getColor());
                    }
                }
            }
        }

        /*
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.line(0,0,0,1);
        StdDraw.line(0,0,1,0);

        // */

        game.character.draw();

        StdDraw.show();
    }

    //draws a single Cell in pic
    private void drawCell(double picX, double picY, Color color) {
        StdDraw.setPenColor(color);
        StdDraw.filledRectangle(picX, picY, CELLSIZE / 2 + .001, CELLSIZE / 2 + .001);
    }
}
