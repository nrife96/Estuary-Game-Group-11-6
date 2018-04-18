import java.util.Collection;
import java.util.ArrayList;

class Model{
    
    
    //Collections for the boats, wakes, shore segments and barriers
    Collection<Boat> fleet = new ArrayList<Boat>();
    Collection<Wake> wakes = new ArrayList<Wake>();
    Collection<Shore> shoreline = new ArrayList<Shore>();
    Collection<Barrier> barrierDefense = new ArrayList<Barrier>();
    
    //Tools tool;
    int level;              //difficulty level
    final int maxBoats = 0; //temporary value, this will hold the maximum number of boats on screen at a given time
    
    public Model(){
        
    }//Model
        
    /* remove once dependent classes are created
    
    public void addBoat(Boat boat){
        
    }//addBoat
    
    public void removeShore(Shore shore){
        
    }//removeShore
    
    */
    
    public void update(){
        
    }//update
    
    public void checkForCollisions(){
        
    }//checkForCollisions
    
    /* remove once dependent classes are created
    
    public void switchTool(Tool newTool){
        
    }//switchTool
    
    */
    
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