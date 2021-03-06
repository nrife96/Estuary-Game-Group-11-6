import java.util.Collection;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Point;

class Model{
    
    
    //Collections for the boats, wakes, shore segments and barriers
    Collection<Boat> fleet;
    Collection<Wake> wakes;
    Collection<Shore> shoreline;
    Collection<Barrier> barrierDefense;

    Tool shovel;
    Tool whistle;
    
    Tool tool;
    int level;              //difficulty level
    long startTime;
    long curTime;
    int hour = 0;
    boolean timeOver = false;
    boolean gameOver = false;

    final int maxBoats = 0; //temporary value, this will hold the maximum number of boats on screen at a given time
    final int maxSpeed = 4;
    final int numOfLanes = 5;
    int numOfWakeCols;
    final int gameLenSec = 180;

    Point click;
    double Xclick;
    double Yclick;

    Random rand = new Random();

    int frameHeight;
    int frameWidth;

    int shoreSize;
    int shoreRows = 4;
    int shoreCols = shoreRows*5;

    int barrierHeight = 100;
    int barrierWidth = 100;
    final int numOfBarriers = 8;


    // WE NEED A BETTER METHOD TO KEEP THESE VALUES CONSISTENT BETWEEN THE MODEL & VIEW...
    final int whistleWidth = 75;
    final int whistleHeight = 47;
    final int shovelWidth = 75;      // why was this 128 when in view it was 75???
    final int shovelHeight = 47;
    final int[] shovelStartLocation;
    final int[] whistleStartLocation;

    public Model(int frameWidth, int frameHeight){

        fleet = new ArrayList<Boat>();
        wakes = new ArrayList<Wake>();
        shoreline = new ArrayList<Shore>();
        barrierDefense = new ArrayList<Barrier>();

        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;

        this.shoreSize = frameWidth/shoreCols;

        shovelStartLocation = new int[] {(frameWidth - shovelWidth), 0};
        whistleStartLocation = new int[] {(frameWidth - (shovelWidth + whistleWidth) ), 0};

        whistle = new Tool(whistleStartLocation[0], whistleStartLocation[1], whistleWidth, whistleHeight, "Whistle");
        shovel = new Tool(shovelStartLocation[0], shovelStartLocation[1], shovelWidth, shovelHeight, "Shovel");

        numOfWakeCols = (frameWidth/Wake.WIDTH)+10;

        tool = whistle;

        startTime = System.currentTimeMillis();

        for(int i = shoreRows; i > 0; i--){
            for(int j = 0; j < shoreCols; j++){
                Shore newShore = new Shore(j*shoreSize, frameHeight - i*shoreSize, shoreSize, shoreSize);
                shoreline.add(newShore);
            }
        }

        int spaceForBarrier = frameWidth/numOfBarriers;
        for(int i = 0; i < numOfBarriers; i++){
            
            Barrier newBarrier = new Barrier((int)((i+.5)*spaceForBarrier) - (barrierWidth/2), frameHeight/2, barrierHeight, barrierWidth, "", 20);
            barrierDefense.add(newBarrier);
        }

        
    }//Model

    public void update(){
        if (!gameOver && !timeOver){

            processClick();
            updateTimer();
            spawnBoat();
            spawnWaves();
            moveBoats();
            moveWakes();
            checkForCollisions();
            checkBoatsOffScreen();
            checkWakesOffScreen();
            winCheck();

        }//if

    }//update

    public void updateTimer() {
        // System.out.println(System.nanoTime()-startTime);
        long sec = (System.currentTimeMillis()-startTime)/1000;
        hour = (int)(12*sec/gameLenSec)%12;

        if (sec >= gameLenSec){
            timeOver = true;
        }
        
    }
        

    public void spawnBoat(){

        int spawnChance = 30; //Spawns every n times

        if(rand.nextInt(spawnChance) == 0){

            //Find open lanes 
            ArrayList<String> openLanes = new ArrayList<>();
            for (int i = 1; i < numOfLanes; i++){
                openLanes.add(Integer.toString(i));
            }
            int laneHeight = frameHeight/(2*numOfLanes);
            for(Boat b:fleet){
                String lane = Integer.toString(b.getY()/laneHeight);
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
                    xLoc = -Boat.WIDTH;
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
                int speed = rand.nextInt(maxSpeed)+1;

                // System.out.println(speed);

                Boat newBoat = new Boat(xLoc,yLoc,speed,direction);
                fleet.add(newBoat); 

            }

        }
        
    }//addBoat
    
    public void processClick(){
        
        if (click != null) {
            switch (tool.getName()) {
                case ("Whistle"):
                    //do stuff
                    Xclick = click.getX();
                    Yclick = click.getY();

                    // check if shovel was clicked
                    if ( shovelStartLocation[0] < Xclick && Xclick < (shovelStartLocation[0] + shovelWidth) ) {
                        if ( shovelStartLocation[1] < Yclick && Yclick < (shovelStartLocation[1] + shovelHeight) ) {
                            switchTool(shovel);
                            break;
                        }
                    }
                    // otherwise, check if any boats were clicked
                    for (Boat b: fleet) {
                        if (0 < (Xclick - b.getX()) && (Xclick - b.getX()) < b.getWidth()) {
                            if (0 < (Yclick - b.getY()) && (Yclick - b.getY()) < b.getHeight()) {
                                b.lowerSpeed();
                            }
                        }
                    }
                    break;

                case ("Shovel"):
                    Xclick = click.getX();
                    Yclick = click.getY();

                    // check if whistle was clicked
                    if ( (whistleStartLocation[0] < Xclick) && (Xclick < (whistleStartLocation[0] + whistleWidth)) ) {
                        if ( whistleStartLocation[1] < Yclick && Yclick < (whistleStartLocation[1] + whistleHeight) ) {
                            switchTool(whistle);
                            break;
                        }
                    }
                    break;
            }

            //DO NOT REMOVE
            //Used to reset click
            click = null;
        }
        
    }

    public void spawnWaves(){

        int wakeColWidth = frameWidth/numOfWakeCols;

        for(Boat b:fleet){
            int speed = Math.abs(b.getXIncrement());
            if(b.getDirection().equals("Left")){
                if(b.isSpeeding() && Math.abs(b.getX()) % wakeColWidth < speed){
                    Wake newWake = new Wake(b.getX()+((int)(.75*(b.getWidth()))), b.getY()+((int)(.5*b.getHeight())), Math.abs(b.getXIncrement()),b.getDirection());
                    wakes.add(newWake);
                }//if
            }//if
            
            if(b.getDirection().equals("Right")){
                if(b.isSpeeding() && b.getX() % wakeColWidth > wakeColWidth-speed-1){
                    Wake newWake = new Wake(b.getX(), b.getY()+((int)(.5*b.getHeight())),Math.abs(b.getXIncrement()),b.getDirection());
                    wakes.add(newWake);
                }//if
            }//if
            
        }//for

    }//spawnWaves

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

            if( b.getX() > frameWidth){
                iterator.remove();
            }

            else if(b.getX()+b.getWidth() < 0){
                iterator.remove();
            }
        }


    }

    public void checkWakesOffScreen(){
  
        for (Iterator<Wake> iterator = wakes.iterator(); iterator.hasNext();) {
            
            Wake w = iterator.next();

            if( w.getY() > frameHeight){
                iterator.remove();
            }
        }

    }

    //Fix
    public void checkForCollisions(){

        for(Barrier b:barrierDefense){

            for (Iterator<Wake> iterator = wakes.iterator(); iterator.hasNext();) {

                Wake w = iterator.next();

                if(w.getY()+w.getHeight()>b.getY()+(.5*b.getHeight())  && !b.isDestroyed() && w.getX() + w.getWidth() > b.getX() && w.getX() + w.getWidth() < b.getX() + b.getWidth()){
                    b.damage(Math.abs(w.getYIncrement()));
                    iterator.remove(); 
                }
                
            }

        }

        for(Shore s:shoreline){

            for (Iterator<Wake> iterator = wakes.iterator(); iterator.hasNext();) {

                Wake w = iterator.next();

                if(w.getY()+w.getHeight()>s.getY()+(.5*s.getHeight())  && !s.isDestroyed() && w.getX() + w.getWidth() > s.getX() && w.getX() + w.getWidth() < s.getX() + s.getHeight()){
                    s.destroy();
                    iterator.remove(); 
                }
            }
        }

    }//checkForCollisions

    public void winCheck(){
        gameOver = true;
        
        for(Shore s:shoreline){
            if (!s.isDestroyed()){
                gameOver = false;
            }//if
        }//for
        
        if (gameOver){
            System.out.println("GAME OVER: LOSE");
        }
        else if (timeOver==true){
            System.out.println("GAME OVER: WIN");
        }//if
    }
    
    public void switchTool(Tool newTool){
        this.tool = newTool;
    }//switchTool

    public void setClick(Point click){
        this.click = click;
    }
    
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