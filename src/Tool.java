
public enum Tool {
    WHISTLE("whistle",0),
    SHOVEL("shovel",1);

    private static Tool[] tools = values();

    public Tool next(){
        return tools[(this.ordinal()+1) % tools.length];
    }//next

}