
class Boat extends Moveable{

    String direction;
    boolean isSpeeding;

    static int BOAT_HEIGHT = 68;
    static int BOAT_WIDTH = 175;

    public Boat(int xLoc, int yLoc, int speed, String direction){

        super(xLoc, yLoc, BOAT_WIDTH, BOAT_HEIGHT, speed, 0);

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