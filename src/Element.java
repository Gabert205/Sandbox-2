import java.awt.*;

public class Element {
    private String type;
    private Color color;

    public Element(String type) {
        this.type = type;
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
}
