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
    final int numOfWakeCols = 5;

    Random rand = new Random();

    int frameHeight;
    int frameWidth;

    int boatWidth = 175;
    int boatHeight = 68;

    int wakeHeight = 153;
    int wakeWidth = 158;


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
        spawnWaves();
        moveBoats();
        moveWakes();
        checkBoatsOffScreen();

    }//update
        

    public void spawnBoat(){

        int spawnChance = 30; //Spawns every n times

        if(rand.nextInt(spawnChance) == 0){

            //Find open lanes 
            ArrayList<String> openLanes = new ArrayList<>();
            for (int i = 0; i < numOfLanes; i++){
                openLanes.add(Integer.toString(i));
            }
            int laneHeight = frameHeight/(2*numOfLanes);
            for(Boat b:fleet){
                String lane = Integer.toString(b.yLoc/laneHeight);
                if( openLanes.contains(lane) )
                    openLanes.remove(lane);
            }

            //Make a boat if a lane is avaliable
            if( !openLanes.isEmpty() ){
            // if(true){   
            
                //Assuming origin is bottom left

                //Pick side of screen to start on
                //Pick left(0) or right(1) side
                int dirInt = rand.nextInt(2);
                int xLoc;
                String direction;
                if(dirInt == 0){
                    direction = "Right";
                    xLoc = -boatWidth;
                }
                else{
                    direction = "Left";
                    xLoc = frameWidth;
                }

                //Pick height to start at
                int index = rand.nextInt(openLanes.size());
                int laneNum = Integer.parseInt(openLanes.get(index)); //Choose open lane
                int yLoc = laneHeight*laneNum;
                
                //Random speed up to max
                int speed = rand.nextInt(maxSpeed+1)+1;

                // System.out.println(speed);

                Boat newBoat = new Boat(xLoc,yLoc,boatWidth,boatHeight,speed,direction);
                fleet.add(newBoat); 

            }

        }
        
    }//addBoat
    
    public void processClick(){}


    //TODO right moving boats spawn waves weird
    //TODO Slow boats should not spawn waves
    public void spawnWaves(){

        int wakeColWidth = frameWidth/numOfWakeCols;

        for(Boat b:fleet){

            if(b.xLoc % wakeColWidth == 0){
                Wake newWake = new Wake(b.xLoc, b.yLoc+b.height, wakeHeight, wakeWidth, Math.abs(b.xIncrement));
                wakes.add(newWake);
            }
        }

    }


    public void moveBoats(){

        for(Boat b:fleet){
            b.updateLoc();
        }

    }


    public void moveWakes(){
        for(Wake w:wakes){
            w.updateLoc();
        }
    }
    

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