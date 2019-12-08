public class Quadrant implements Comparable<Quadrant>{
    public int x;//the global x position of the quadrant
    public int y;//the global y position of the quadrant
    public Cell[][] cells;//stores the cells that are found in this quadrant
    public static final int QUADRANTSIZE = 10;//the the number of Cells that this quadrant holds in one dimension
    private boolean active;//whether or not this quadrant should be updated

    public Quadrant(int xPos, int yPos) {
        this.x = xPos;
        this.y = yPos;
        cells = new Cell[QUADRANTSIZE][QUADRANTSIZE];
        active = false;

        fillCells();
    }

    //==================================================================================================================

    //region Gets and Sets


    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setCell(int x, int y, Cell cell){
        cells[x][y] = cell;
    }

    //sets the element of the cell at (x,y)
    public void setElement(Element element, int x, int y){
        cells[x][y].setElement(element);
    }

    public void setAllElements(Element element){

        //for every Cell in the Quadrant - set the Cell's element to the given type
        for (Cell[] cell : cells) {
            for (Cell value : cell) {

                value.setElement(element.clone());
            }
        }
    }

    //checks to see if the cell should be active
    public boolean isActive(){
        // TODO: 12/7/2019 check to see if the Quadrant should be active
        return true;
    }


    //endregion

    //==================================================================================================================

    //region Private Methods


    //fills cells with new Cells
    private void fillCells() {

        //for every cell - add a new cell
        for (int x = 0 ; x < 10 ; x++) {
            for (int y = 0 ; y < 10 ; y++) {
                cells[x][y] = new Cell(x, y);
            }
        }

    }


    //endregion

    //==================================================================================================================

    //updates all the cells in cells
    public void update(){
        for (Cell[] cell : cells) {
            for (Cell value : cell) {
                value.update();
            }
        }
    }

    //==================================================================================================================

    //prints the contents of cells
    public String toString(){
        String end = "";    //the string that is being returned

        //for every cell add the Cell's toString() to end
        for (int y = 0 ; y < cells[x].length ; y++) {
            for (Cell[] cell : cells) {
                end += cell[y] + "\t";
            }

            end += "\n";
        }

        return end;
    }

    @Override
    public int compareTo(Quadrant o) {

        //if the x coordinates are equal - compare y coordinate - else compare x coordinates
        if(this.x - o.x == 0){
            return this.y - o.y;
        }
        else {
            return this.x - o.x;
        }
    }
}
