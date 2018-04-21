import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JPanel;
import java.awt.Point;

import java.util.Collection;

class View extends JPanel {

    BufferedImage[] rightBoatPics;
    BufferedImage[] leftBoatPics;
	BufferedImage wakePicLeft;
    BufferedImage wakePicRight;
    BufferedImage backGround;
	BufferedImage sandPic;
	BufferedImage[][] barriersPics;
    BufferedImage waterPic;		// assuming single (background) image of water
    BufferedImage[] clockPics;
    BufferedImage whistlePic;
    BufferedImage shovelPic;

	final int frameWidth = 960;
	final int frameHeight = 540;
	final int shoreWidth = 100;		// applies to both sand and barriers
    final int shoreHeight = 98;
    final int clocksImgSize = 100;
    final int whistleHeight = 63;
    final int whistleWidth = 100;
    final int shovelImgSize = 128;

    final Color clear = new Color(0, 0, 0, 0);

	// Tool activeTool;			// should we instead just call getter from the Model?

	boolean paused;            // should view or controller contain paused?
    boolean clickFlag = false;
    Point lastClick;

    // image cycle var's
    int boatPicNum = 0;
    final int boatFrameCount = 4;
    int wakePicNum = 0;
    final int wakeFrameCount = 1;
    int smoothCounter = 0;

    // // UNCOMMENT LATER AFTER CLASSES IMPLEMENTED
    Collection<Boat> fleet;
    Collection<Wake> wakes;
    Collection<Shore> shoreline;
    // Collection<Barrier> barrierDefence;

    int hour;

	public View() {

        rightBoatPics = new BufferedImage[boatFrameCount];
        BufferedImage img = createImage("./../images/boatRightspray.png");
        for(int i = 0; i < boatFrameCount; i++) {
            rightBoatPics[i] = img.getSubimage(Boat.WIDTH*i, 0, Boat.WIDTH, Boat.HEIGHT);
        }

        leftBoatPics = new BufferedImage[boatFrameCount];
        img = createImage("./../images/boatLeftspray.png");
        for(int i = 0; i < boatFrameCount; i++) {
            leftBoatPics[i] = img.getSubimage(Boat.WIDTH*i, 0, Boat.WIDTH, Boat.HEIGHT);
        }

        //Uncomment when we have pics for the waves
        wakePicRight = createImage("./../images/rightWake.png");
        wakePicLeft = createImage("./../images/leftWake.png");
        
        backGround = createImage("./../images/background.png");

        //Uncomment when we have pics for the sand
        sandPic = createImage("./../images/sandPiece.png");

        //Clocks pics
        img = createImage("./../images/stopwatch.png");

        int rows = img.getHeight()/clocksImgSize;
        int cols = img.getWidth()/clocksImgSize;

        clockPics = new BufferedImage[rows*cols];
        int count = 0;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++){
                clockPics[count] = img.getSubimage(clocksImgSize*j, clocksImgSize*i, clocksImgSize, clocksImgSize);
                count++;
            }
        }

        //Whistle pic
        whistlePic = createImage("./../images/whistle.png");

        //Shovel pic
        shovelPic = createImage("./../images/shovel.png");

        // add mouse input
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                clickFlag = true;
                lastClick = me.getPoint();
            }
        });

    }
    
	protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(backGround,0,0,null);
        if (smoothCounter++ % 10 == 0){boatPicNum++;}

        for(Boat b:fleet){
            if(b.direction.equals("Right"))
                g.drawImage(rightBoatPics[(boatPicNum) % boatFrameCount], b.xLoc, b.yLoc, clear, this);
            if(b.direction.equals("Left"))
                g.drawImage(leftBoatPics[(boatPicNum) % boatFrameCount], b.xLoc, b.yLoc, clear, this);
        }
        
        for(Wake w: wakes){
            if(w.direction.equals("Right")){
                g.drawImage(wakePicRight, w.xLoc, w.yLoc, clear, this);
            }
            if(w.direction.equals("Left")){
                g.drawImage(wakePicLeft, w.xLoc, w.yLoc, clear, this);
            }
        }//for
        
        for(Shore s: shoreline){
            g.drawImage(sandPic, s.xLoc, s.yLoc, clear, this);
        }

        g.drawImage(clockPics[hour], 0, 0, clear, this);
        g.drawImage(whistlePic, frameWidth-(int)(1.3*whistleWidth), 0, clear, this);
        g.drawImage(shovelPic, frameWidth-(int)(1.1*shovelImgSize), (int)(1.1*whistleHeight), clear, this);
        
    }

    public Dimension getPreferredSize() {
        return new Dimension(frameWidth, frameHeight);
    }

    //public void update(){
    public void update(Collection<Boat> fleet, Collection<Wake> wakes, Collection<Shore> shoreline, int hour) { //Collection<Barrier> barriers) {
        if(!paused){

            this.fleet = fleet;
            this.wakes = wakes;
            this.shoreline =  shoreline;

            this.hour = hour;

            // redraw board
            repaint();
        }
    }

    public boolean getClickFlag(){
            return clickFlag; 
    }

    public void setClickFlag(boolean flag){
        clickFlag = flag; 
    }

    public int getWidth(){return frameWidth;}
    
    public int getHeight(){return frameHeight;}

    // public int getBoatWidth(){return boatWidth;}

    // public int getBoatHeight(){return boatHeight;}

    public int getSandWidth(){return shoreWidth;}

    public int getSandHeight(){return shoreHeight;}

    public boolean isPaused(){return paused;}

    public Point getClick(){
        return lastClick;
    }

    // import image by image
    public BufferedImage createImage(String filename){
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File(filename));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
}
