import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private List<Quadrant> quadrants;
    public Character character;

    public Game(){
        quadrants = new ArrayList<>();
        character = new Character(0,0);
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

    //returns the quadrants's size
    public int getQuadrantsSize(){
        return quadrants.size();
    }


    //endregion

    //==================================================================================================================

    //region Private Methods


    private void sort(){
        Collections.sort(quadrants);
    }


    //endregion

    //==================================================================================================================

    // TODO: 12/8/2019 add a method that prevents the character from crossing solid blocks 

    // TODO: 12/8/2019 add character gravity 
}
