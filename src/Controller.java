public class Controller {
	private Model model;
	private View view;
	
	private final int DEFAULT_TIME_REMAINING = 300;
	
	int timeRemaining; //
	
	public Controller() {
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		this.timeRemainig = DEFAULT_TIME_REMAINING;
	}
	
	/* uncomment if needed
	
	public Controller(int time) { //allows for different game lengths
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		this.timeRemaining = time;
	}
	
	*/
	
	public void start() {
		//update
	}

}
