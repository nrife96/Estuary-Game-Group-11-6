
public enum Tool {
    WHISTLE("whistle",0),
    SHOVEL("shovel",1);

    private static Tool[] tools = values();
    String name;
    int index;

    private Tool(String s, int i){
		name = s;
		index = i;
	}

    public Tool next(){
        return tools[(this.ordinal()+1) % tools.length];
    }//next

}