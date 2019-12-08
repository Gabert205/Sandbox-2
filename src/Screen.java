import java.awt.*;

public class Screen {
    private Game game;
    private int centerOfScreen;
    public final static double CELLSIZE = 1 / 30.;

    public Screen(int screenWidth, int screenHeight) {
        StdDraw.setCanvasSize(screenWidth, screenHeight);
        centerOfScreen = 0;
        game = new Game();

        for (int x = 0 ; x < 3 ; x++) {
            for (int y = 0 ; y < 3 ; y++) {
                game.addNewQuadrant(x, y);
                if(y == 0){
                    game.getQuadrant(x, y).setAllElements(new Dirt());

                    game.getQuadrant(x, y).setTopLevelToGrass();
                }
            }
        }



        draw();
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

    public void setCenterOfScreen(){
        StdDraw.setXscale(centerOfScreen - .5, centerOfScreen + .5);
    }

    //==================================================================================================================

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
            else{
                //for every Cell in the Quadrant - draw the Cell as black
                for (int x = 0 ; x < quadrant.cells.length ; x++) {
                    for (int y = 0 ; y < quadrant.cells[x].length ; y++) {

                        drawCell(getScreenX(quadrant.x, x), getScreenY(quadrant.y, y), new Color(0));
                    }
                }
            }
        }

        game.character.draw();

        StdDraw.show();
    }

    //draws a single Cell in pic
    private void drawCell(double picX, double picY, Color color) {
        StdDraw.setPenColor(color);
        StdDraw.filledRectangle(picX, picY, CELLSIZE / 2 + .001, CELLSIZE / 2 + .001);
    }
}
