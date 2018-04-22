
abstract class Moveable extends Item{

    int xIncrement;
    int yIncrement;

    public Moveable(int xLoc, int yLoc, int width, int height, int xIncrement, int yIncrement){
        super(xLoc, yLoc, width, height);
        this.xIncrement = xIncrement;
        this.yIncrement = yIncrement;
    }

    public void updateLoc(){
        this.xLoc += xIncrement;
        this.yLoc += yIncrement;
    }

    public void updateXIncrement(int x){
        this.xIncrement = x;
    }

    public void updateYIncrement(int y){
        this.yIncrement = y;
    }

    public int getXIncrement(){
        return this.xIncrement;
    }

    public int getYIncrement(){return this.yIncrement;}
}