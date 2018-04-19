import java.util.Collection;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

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

    int boatWidth = 175;
    int boatHeight = 68;




    public Model(int frameWidth, int frameHeight){

        fleet = new ArrayList<Boat>();
        wakes = new ArrayList<Wake>();
        shoreline = new ArrayList<Shore>();
        barrierDefense = new ArrayList<Barrier>();

        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;
        
    }//Model

    public void update(){
        
        spawnBoat();
        moveBoats();
        checkBoatsOffScreen();

    }//update
        

    // TODO verify working 
    public void spawnBoat(){

        int spawnChance = 100; //Spawns every n times

        if(rand.nextInt(spawnChance) == 0){
            
            //Assuming origin is bottom left

            //Pick left(0) or right(1) side
            int dirInt = rand.nextInt(2);
            int xLoc;
            String direction;
            if(dirInt == 0){
                direction = "Right";
                xLoc = 0;
            }
            else{
                direction = "Left";
                xLoc = frameWidth - boatWidth;
            }

            //Pick side of screen to start on

            //Pick height to start at
            int laneHeight = frameHeight/(2*numOfLanes);
            int laneNum = rand.nextInt(numOfLanes);
            int yLoc = laneNum*laneHeight;
            
            //Random speed up to max
            int speed = rand.nextInt(maxSpeed+1)+1;

            // System.out.println(speed);

            Boat newBoat = new Boat(xLoc,yLoc,boatWidth,boatHeight,speed,direction);
            fleet.add(newBoat);

        }
        
    }//addBoat
    
    public void processClick(){}

    public void spawnWaves(){}

    public void moveBoats(){

        for(Boat b:fleet){
            b.updateLoc();
        }


    }

    public void moveWakes(){}
    
    // TODO verify working 
    public void checkBoatsOffScreen(){

        for (Iterator<Boat> iterator = fleet.iterator(); iterator.hasNext();) {
            
            Boat b = iterator.next();

            if( b.xLoc > frameWidth){
                iterator.remove();
            }

            else if(b.xLoc+b.width < 0){
                iterator.remove();
            }
        }


    }

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