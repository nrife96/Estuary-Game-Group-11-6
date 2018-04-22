
abstract class Stationary extends Item{

    boolean destroyed;

    public Stationary(int xLoc, int yLoc, int height, int width){
        super(xLoc, yLoc, height, width);
        destroyed = false;
    }

    public void create(){
        destroyed = false;
    }

    public void destroy(){
        destroyed = true;
    }

    public boolean isDestroyed() {return destroyed;}
} 