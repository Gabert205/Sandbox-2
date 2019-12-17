import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private List<Quadrant> quadrants;   //holds all of the Quadrants in the game
    public Character character;   //the Character that the player controls
    private final double GRAVITY = -.00002;   //the strength of gravity

    public Game(){
        quadrants = new ArrayList<>();
        character = new Character(.5,.8);

        addLotsOfQuadrants(-2,-3,2,2);// TODO: 12/8/2019 something broken with either this or isActive()

        int elevation = 5;
        for (int i = -2 ; i <= 2 ; i++) {
            elevation = setTopLevelOfGroundRight(i,0,elevation);
        }

        fillDirtBelowTop(true);
    }

    //==================================================================================================================

    //region Gets and Sets


    //uses a binary search tree to find the Quadrant ### QUADRANT NEEDS TO EXIST ###
    public Quadrant getQuadrant(int x, int y){
        sort();   //binary search tree requires a sorted List

        int lowerBound = 0;   //the lower bound of the search area
        int upperBound = quadrants.size();   //the upper bound of the search area

        while(true){

            int mid = (upperBound + lowerBound) / 2;   //the index of the quadrant in the middle of the bounds

            Quadrant quadrant = quadrants.get(mid);   //the quadrant in the middle of the bounds

            //if the quadrant's x-value is less than the desired one
            if(x < quadrant.x){
                upperBound = mid;
            }

            //if the quadrant's x-value is greater than the desired one
            else if(x > quadrant.x){
                lowerBound = mid;
            }

            //else the x-value has been found
            else {

                int index = mid;   //just changing variable names

                while (true){

                    quadrant = quadrants.get(index);   //the quadrant under question

                    //if the y-value of the quadrant is too high - go down
                    if(y < quadrant.y){
                        index--;
                    }
                    //if the y-value of the quadrant is too low - go up
                    else if(y > quadrant.y){
                        index++;
                    }
                    //else it has been found
                    else {
                        return quadrant;
                    }

                }//while

            }//if x == quadrant.x

            if(upperBound == lowerBound)return null;
        }
    }

    //returns the Quadrant at the given index
    public Quadrant getQuadrant(int index){
        return quadrants.get(index);
    }

    //adds a new quadrant at (x, y)
    public void addNewQuadrant(int x, int y){
        quadrants.add(new Quadrant(x, y));
    }

    //makes new Quadrants from (x0, y0) to (x1, y1)
    public void addLotsOfQuadrants(int x0, int y0, int x1, int y1){

        //add a new Quadrant at every combination of numbers between the parameters
        for (int x = x0 ; x <= x1 ; x++) {
            for (int y = y0 ; y <= y1 ; y++) {
                addNewQuadrant(x, y);
            }
        }
    }

    //returns the quadrants's size
    public int getQuadrantsSize(){
        return quadrants.size();
    }

    // TODO: 12/8/2019 add a way to check for the left side of the character as well as the right side
    //returns the Cell that is beneath the character
    public Cell getCellBelowCharacter(double deltaX){

        Quadrant quadrant;

        //if the Cell below the Character is in a different Quadrant than the one the Character is in
        if(character.getCellY() - 1 < 0){
            quadrant = getQuadrant(getQuadrantPosition(character.pos.getX() + deltaX), character.getQuadrantY() - 1);
        }
        else {
            quadrant = getQuadrant(getQuadrantPosition(character.pos.getX() + deltaX), character.getQuadrantY());
        }
        return quadrant.getCell(getCellPosition(character.pos.getX() + deltaX) , (character.getCellY() + 9) % 10);
    }

    //UNTESTED - DEFIANTLY DOESN'T WORK
    //returns the Cell that is left of the character
    public Cell getCellLeftOfCharacter(){

        Quadrant quadrant;

        //if the Cell below the Character is in a different Quadrant than the one the Character is in
        if(character.getCellX() - 1 < 0){
            quadrant = getQuadrant(getQuadrantPosition(character.pos.getX()), character.getQuadrantY() - 1);
        }
        else {
            quadrant = getQuadrant(getQuadrantPosition(character.pos.getX()), character.getQuadrantY());
        }
        return quadrant.getCell(getCellPosition((character.pos.getX() + 9) % 10 ) , character.getCellY());
    }

    //UNTESTED - DEFIANTLY DOESN'T WORK
    //returns the Cell that is right of the character
    public Cell getCellRightOfCharacter(){

        Quadrant quadrant;

        //if the Cell below the Character is in a different Quadrant than the one the Character is in
        if(character.getCellX() + 1 < 9){
            quadrant = getQuadrant(character.getQuadrantX() + 1, character.getQuadrantY());
        }
        else {
            quadrant = getQuadrant(character.getQuadrantX(), character.getQuadrantY());
        }

        return quadrant.getCell((character.getCellX() + 11) % 10, character.getCellY());
    }

    public int getCellPosition(double value){
        if(value < 0){
            return (int) (-Math.abs((value * 30) % 10) + 10);
        }

        return (int) (value * 30) % 10;
    }

    public int getQuadrantPosition(double value) {
        return (int) ( value * 3 );
    }

    //endregion

    //==================================================================================================================

    //region Private Methods


    private void sort(){
        Collections.sort(quadrants);
    }

    //sets the top level of ground to Dirt starting from the Right
    private int setTopLevelOfGroundRight(int quadrantX, int quadrantY, int cellY){

        Quadrant quadrant = getQuadrant(quadrantX, quadrantY);

        int deltaY = 0;

        //for every cell in the x direction
        for (int x = 0 ; x < quadrant.cells.length ; x++) {

            //if the cellY is too high or low, change it to a usable number
            while (cellY < 0 || cellY >= 10) {

                if (cellY < 0) {
                    deltaY--;
                    cellY += 10;
                } else {
                    deltaY++;
                    cellY -= 10;
                }
            }

            quadrant = getQuadrant(quadrantX, quadrantY + deltaY);
            quadrant.filled = true;

            quadrant.cells[x][cellY].setElement(new Dirt());

            cellY += (int) ( Math.random() * 4 - 2);
        }

        return cellY + deltaY * 10;
    }


    //endregion

    //==================================================================================================================

    //if the Cell below the Character is Solid
    public boolean isCharacterTouchingSolidCell(){
        return getCellBelowCharacter(Screen.CELLSIZE / 2).getState().equals("SOLID") ||
               getCellBelowCharacter(-Screen.CELLSIZE / 2).getState().equals("SOLID");
    }

    //adds gravity to the Character
    public void addCharacterGravity(){
        character.vel.addY(GRAVITY);
    }

    //fills all the Cells
    public void fillDirtBelowTop(boolean addingGrass){
        sort();
        int xSave = getQuadrant(quadrants.size() - 1).x;
        boolean foundFilled = false;

        for (int i = quadrants.size() - 1 ; i >= 0 ; i--) {
            Quadrant quadrant = getQuadrant(i);

            if(quadrant.filled){
                quadrant.fillBelowWithDirt();

                if(addingGrass)
                    quadrant.setTopLevelToGrass();

                foundFilled = true;
                continue;
            }

            if(xSave != quadrant.x) {
                foundFilled = false;
                xSave = quadrant.x;
            }

            if(foundFilled){
                quadrant.setAllElements(new Dirt());
            }
        }
    }

    //updates the game
    public void update(){

        //for every Quadrant - if the Quadrant is active - update
        for (Quadrant quadrant : quadrants) {
            if(quadrant.isActive()) {
                quadrant.update();
            }
        }

        addCharacterGravity();

        character.movement();

        //if the Character is on the ground - stop moving down and allow jumping
        if(isCharacterTouchingSolidCell()){
            character.vel.setY(0);

            if(StdDraw.isKeyPressed(38)){
                character.jump();
            }
        }

        character.update();

        System.out.println(getCellLeftOfCharacter());
    }
}
