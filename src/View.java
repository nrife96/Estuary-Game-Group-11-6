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

import java.util.Collection;

class View extends JPanel {

    BufferedImage[] rightBoatPics;
    BufferedImage[] leftBoatPics;
	BufferedImage wakePic;
	BufferedImage[] sandPics;
	BufferedImage[][] barriersPics;
	BufferedImage waterPic;		// assuming single (background) image of water

	final int frameWidth = 960;
	final int frameHeight = 540;
	final int boatWidth = 175;
	final int boatHeight = 68;
	final int shoreWidth = 30;		// applies to both sand and barriers
    final int shoreHeight = 30;
    final int wakeHeight = 153;
    final int wakeWidth = 158;

	// Tool activeTool;			// should we instead just call getter from the Model?

	boolean paused;            // should view or controller contain paused?

    // image cycle var's
    int boatPicNum = 0;
    final int boatFrameCount = 4;
    int wakePicNum = 0;
    final int wakeFrameCount = 1;
    int smoothCounter = 0;

    // // UNCOMMENT LATER AFTER CLASSES IMPLEMENTED
    Collection<Boat> fleet;
    Collection<Wake> wakes;
    // Collection<Shore> shorePiece;
    // Collection<Barrier> barrierDefence;

	public View() {

        rightBoatPics = new BufferedImage[boatFrameCount];
        BufferedImage img = createImage("./../images/boatRight.png");
        for(int i = 0; i < boatFrameCount; i++) {
            rightBoatPics[i] = img.getSubimage(boatWidth*i, 0, boatWidth, boatHeight);
        }

        leftBoatPics = new BufferedImage[boatFrameCount];
        img = createImage("./../images/boatLeft.png");
        for(int i = 0; i < boatFrameCount; i++) {
            leftBoatPics[i] = img.getSubimage(boatWidth*i, 0, boatWidth, boatHeight);
        }

        //Uncomment when we have pics for the waves
        wakePic = createImage("./../images/wake.png");


/*
        //Uncomment when we have pics for the sand
        sandPics = new BufferedImage[sandFrameCount];
        img = createImage("./../images/sandPics.png");
        for(int i = 0; i < boatFrameCount; i++) {
            sandPics[i] = img.getSubimage(sandWidth*i, 0, sandWidth, sandHeight);
        }
*/

        setBackground(Color.BLUE);

        // addMouseListener();
        addMouseListener(new MouseAdapter() { 
          public void mousePressed(MouseEvent me) { 
            System.out.println(me.getPoint());
          } 
        });

    }
    
	protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (smoothCounter++ % 10 == 0){boatPicNum++;}

        for(Boat b:fleet){
            if(b.direction == "Right")
                g.drawImage(rightBoatPics[(boatPicNum) % boatFrameCount], b.xLoc, b.yLoc, Color.BLUE, this);
            if(b.direction == "Left")
                g.drawImage(leftBoatPics[(boatPicNum) % boatFrameCount], b.xLoc, b.yLoc, Color.BLUE, this);
        }
        
        for(Wake w: wakes)
        g.drawImage(wakePic, w.xLoc, w.yLoc, Color.BLUE, this);


    }

    public Dimension getPreferredSize() {
        return new Dimension(frameWidth, frameHeight);
    }

    //public void update(){
    public void update(Collection<Boat> fleet, Collection<Wake> wakes, Collection<Shore> shorePiece) { //Collection<Barrier> barriers) {
        if(!paused){

            this.fleet = fleet;
            this.wakes = wakes;

            // redraw board
            repaint();
        }
    }

    public int getWidth(){return frameWidth;}
    
    public int getHeight(){return frameHeight;}

    public int getBoatWidth(){return boatWidth;}

    public int getBoatHeight(){return boatHeight;}

    public int getSandWidth(){return shoreWidth;}

    public int getSandHeight(){return shoreHeight;}

    public boolean isPaused(){return paused;}

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