import java.util.Collection;
import java.util.ArrayList;

class Model{
    
    
    //Collections for the boats, wakes, shore segments and barriers
    Collection<Boat> fleet;
    Collection<Wake> wakes;
    Collection<Shore> shoreline;
    Collection<Barrier> barrierDefense;
    
    Tool tool;
    int level;              //difficulty level
    final int maxBoats = 0; //temporary value, this will hold the maximum number of boats on screen at a given time
    
    public Model(){

        fleet = new ArrayList<Boat>();
        wakes = new ArrayList<Wake>();
        shoreline = new ArrayList<Shore>();
        barrierDefense = new ArrayList<Barrier>();
        
    }//Model
        

    
    public void addBoat(Boat boat){
        fleet.add(boat);
        
    }//addBoat
    
    public void removeShore(Shore shore){
        shore.destroy();
    }//removeShore
    
    
    public void update(){
        
    }//update
    
    public void checkForCollisions(){
        
    }//checkForCollisions
    
    public void switchTool(Tool newTool){
        
    }//switchTool
    
    
    
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