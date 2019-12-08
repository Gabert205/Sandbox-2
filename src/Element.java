import java.awt.*;

public class Element {
    private String type;
    private String state;
    private Color color;

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


    //endregion

    //==================================================================================================================

    public Element clone(){
        return new Element(type, state, color);
    }
}
