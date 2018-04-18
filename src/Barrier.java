
class Barrier extends Stationary{

    String type;
    int health;

	public Barrier(int xLoc, int yLoc, int height, int width, String type, int health) {
        super(xLoc, yLoc, height, width);
        this.type = type;
        this.health = health;
    }
    
    public int getHealth(){
        return health;
    }

    public int setHealth(int newHealth){
        this.health = newHealth;
    }

    public String getType(){
        return type;
    }

    public int setType(String type){
        this.type = type;
    }

}
