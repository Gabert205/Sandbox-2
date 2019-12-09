import java.awt.*;

public class Element {
    private String type;   //the Elemental type of the Cell
    private String state;   //SOLID -- LIQUID -- GAS -- NONE
    private Color color;   //the color of the Element

    public Element() {
        this.type = "NONE";
        this.state = "NONE";
        this.color = new Color(0x2977CB);
    }

    public Element(String type, String state, Color color) {
        this.type = type;
        this.state = state;
        this.color = color;
    }


    //==================================================================================================================

    //region Gets and Sets


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Color getColor(){
        return color;
    }

    public String getState() {
        return state;
    }


    //endregion

    //==================================================================================================================

    public Element clone(){
        return new Element(type, state, color);
    }
}
