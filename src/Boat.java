
class Boat extends Moveable{

    public Boat(int xLoc, int yLoc, int height, int width, int speed, String direction){


        super(xLoc, yLoc, height, width, speed, 0);

        if(direction == "Left"){
            this.xIncrement = -1*this.xIncrement;
        }

    }

}