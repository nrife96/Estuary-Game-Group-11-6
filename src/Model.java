import java.util.Collection;
import java.util.ArrayList;
import java.util.Random;

class Model{
    
    
    //Collections for the boats, wakes, shore segments and barriers
    Collection<Boat> fleet;
    Collection<Wake> wakes;
    Collection<Shore> shoreline;
    Collection<Barrier> barrierDefense;
    
    Tool tool;
    int level;              //difficulty level
    final int maxBoats = 0; //temporary value, this will hold the maximum number of boats on screen at a given time
    final int maxSpeed = 1;
    final int numOfLanes = 5;

    Random rand = new Random();

    int frameHeight;
    int frameWidth;




    public Model(){

        fleet = new ArrayList<Boat>();
        wakes = new ArrayList<Wake>();
        shoreline = new ArrayList<Shore>();
        barrierDefense = new ArrayList<Barrier>();

        frameHeight = 100;
        frameWidth = 100;
        
    }//Model

    public void update(){
        spawnBoat();
    }//update
        

    
    public void spawnBoat(){

        int spawnChance = 100; //Spawns every n times

        if(rand.nextInt(spawnChance) == 0){
            
            //Assuming origin is bottom left

            //Pick left(0) or right(1) side
            int dirInt = rand.nextInt(2);
            String direction;
            if(dirInt == 1){
                direction = "Left";
            }
            else{
                direction = "Right";
            }

            //Pick side of screen to start on
            int xLoc = dirInt*frameWidth;

            //Pick height to start at
            int waterSpace = (frameHeight/2);
            int laneHeight = waterSpace/numOfLanes;
            int laneNum = rand.nextInt(numOfLanes);
            int yLoc = laneNum*laneHeight + waterSpace;
            
            //Random speed up to max
            int speed = rand.nextInt(maxSpeed+1);

            System.out.println(xLoc + " " + yLoc + " " + speed + " " + direction);

            Boat newBoat = new Boat(xLoc,yLoc,1,1,speed,direction);

        }



        // fleet.add(boat);
        
    }//addBoat
    
    public void removeShore(Shore shore){
        shore.destroy();
    }//removeShore
    
    public void checkForCollisions(){
        
    }//checkForCollisions
    
    public void switchTool(Tool newTool){
        
    }//switchTool1
    
    
    
    Collection<Boat> getBoats(){
        return fleet;
    };
    Collection<Wake> getWakes(){
        return wakes;
    };
    Collection<Shore> getShoreline(){
        return shoreline;
    };
    Collection<Barrier> getBarrierDefense(){
        return barrierDefense;
    };
    
}//Model