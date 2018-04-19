
class Boat extends Moveable{

    String direction;

    public Boat(int xLoc, int yLoc, int height, int width, int speed, String direction){


        super(xLoc, yLoc, height, width, speed, 0);

        this.direction = direction;

        if(direction == "Left"){
            this.xIncrement = -1*this.xIncrement;
        }

    }

}