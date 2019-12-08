import java.awt.*;

public class Cell {
    private Element element;   //the Element that is in the cell
    public int x;   //the x-value of the cell in the quadrant it is in
    public int y;   //the y-value of the cell in the quadrant it is in

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        element = null;
    }

    public Cell(Element element, int x, int y) {
        this.element = element;
        this.x = x;
        this.y = y;
    }

    //==================================================================================================================

    //region Gets and Sets


    public Element getElement() {
        return element;
    }

    public String getElementType() {
        return element.getType();
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public Color getColor(){
        return element.getColor();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    //endregion

    //==================================================================================================================

    public void update(){
        // TODO: 12/7/2019 check for stuff
    }

    //==================================================================================================================

    public String toString(){
        return x + " - " + y;
    }

}
