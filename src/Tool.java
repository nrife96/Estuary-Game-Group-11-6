
public enum Tool {
    WHISTLE("whistle",0),
    SHOVEL("shovel",1);

    private static Tool[] tools = values();
    String name;
    int index;
    // private String imageName;

    private Tool(String s, int i){
		name = s;
		index = i;
	}

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }
    
    // public String getImageName() {
    //     return imageName;
    // }

    public Tool next(){
        return tools[(this.ordinal()+1) % tools.length];
    }//next

}