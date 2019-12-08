import java.awt.*;

public class Screen {
    private Game game;
    private int leftBound;
    private int rightBound;
    private final double CELLSIZE = 1 / 30.;

    public Screen(int screenWidth, int screenHeight) {
        //StdDraw.setCanvasSize(screenWidth, screenHeight);
        game = new Game();
    }

    //==================================================================================================================

    public double getScreenX(int quadrantX, int cellX){
        double x = 0;

        x += quadrantX * (Quadrant.QUADRANTSIZE * CELLSIZE);

        x += cellX * CELLSIZE + CELLSIZE / 2;

        return x;
    }

    public double getScreenY(int quadrantY, int cellY){
        double y = 0;

        y += quadrantY * (Quadrant.QUADRANTSIZE * CELLSIZE);

        y += cellY * CELLSIZE + CELLSIZE / 2;

        return y;
    }

    //==================================================================================================================

    //draws the entire screen
    private void draw() {

        //for every Quadrant in game's quadrants
        for (int i = 0 ; i < game.getQuadrantsSize() ; i++) {
            Quadrant quadrant = game.getQuadrant(i);

            //if the Quadrant is active - draw it
            if (quadrant.isActive()) {
                // TODO: 12/7/2019 find a way to draw with functional bounds

                
            }
        }
    }

    //draws a single Cell in pic
    private void drawCell(int picX, int picY, Color color) {
        StdDraw.setPenColor(color);
        StdDraw.filledRectangle(picX, picY, CELLSIZE, CELLSIZE);
    }
}
