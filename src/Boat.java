
class Boat extends Moveable{

    String direction;
    boolean isSpeeding;

    public Boat(int xLoc, int yLoc, int height, int width, int speed, String direction){


        super(xLoc, yLoc, height, width, speed, 0);

        this.direction = direction;

        if (speed > 1) {isSpeeding = true;}
        else {isSpeeding = false;}

        if(direction == "Left"){
            this.xIncrement = -1*this.xIncrement;
        }

    }

    public void lowerSpeed(){
    	if (isSpeeding) {this.xIncrement *= .5;}
    	if (xIncrement <= 1) {isSpeeding = false;}
    }

    public boolean isSpeeding() {return isSpeeding;}

}