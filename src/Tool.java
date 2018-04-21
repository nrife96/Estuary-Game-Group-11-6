
class Tool extends Item{

    String name;

    public Tool(int xLoc, int yLoc, int width, int height, String name){
        super(xLoc, yLoc, width, height);
        this.name = name;
    }

    public String getName(){
        return name;
    } 

}