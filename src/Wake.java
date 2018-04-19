
class Wake extends Moveable{
    
    String direction;
    
    public Wake(int xLoc, int yLoc, int height, int width, int speed,String direction){

        //Moves 0 in the x, only in the y
        super(xLoc, yLoc, height, width, 0, speed);

        //System.out.println(speed);

    }

}