
class Wake extends Moveable{
    
    String direction;

    static int HEIGHT = 63;
    static int WIDTH = 68;
    
    public Wake(int xLoc, int yLoc, int speed, String direction){

        //Moves 0 in the x, only in the y
        super(xLoc, yLoc, WIDTH, HEIGHT, 0, speed);
        this.direction = direction;

    }

}