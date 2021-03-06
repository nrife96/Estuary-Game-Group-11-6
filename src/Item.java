
abstract class Item{

    int xLoc;
    int yLoc;
    int width;
    int height;

    public Item(int xLoc, int yLoc, int width, int height){
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.width = width;
        this.height = height;
    }

    public int getX() {return xLoc;}

    public int getY() {return yLoc;}

    public int getWidth() {return width;}

    public int getHeight() {return height;}
}