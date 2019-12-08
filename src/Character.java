public class Character {
    public Vector pos;
    public Vector vel;
    public Vector acc;

    public Character(int x, int y){
        pos = new Vector(x, y);
        vel = new Vector();
        acc = new Vector();
    }

    //==================================================================================================================

    //region Gets and Sets


    public int getX(){
        return (int) Math.round(pos.getX());
    }

    public int getY(){
        return (int) Math.round(pos.getY());
    }


    //endregion

    //==================================================================================================================

    public void draw(){

    }
}
