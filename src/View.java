import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import java.awt.event.MouseEvent;
import javax.swing.JPanel;

import java.util.Collection;

class View extends JPanel {

	BufferedImage[] boatPics;
	BufferedImage[] wakePics;
	BufferedImage[] sandPics;
	BufferedImage[][] barriersPics;
	BufferedImage waterPic;		// assuming single (background) image of water

	final int frameWidth = 800;
	final int frameHeight = 800;
	final int boatWidth = 175;
	final int boatHeight = 68;
	final int shoreWidth = 30;		// applies to both sand and barriers
	final int shoreHeight = 30;

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
    // Collection<Wake> wakes;
    // Collection<Shore> shorePiece;
    // Collection<Barrier> barrierDefence;

	public View() {

        boatPics = new BufferedImage[boatFrameCount];

        BufferedImage img = createImage("./../images/boatRight.png");
        for(int i = 0; i < boatFrameCount; i++) {
            boatPics[i] = img.getSubimage(boatWidth*i, 0, boatWidth, boatHeight);
        }
    }
    
	protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        for(Boat b:fleet){
            if (smoothCounter % 100 == 0){boatPicNum++;}
            g.drawImage(boatPics[(boatPicNum) % boatFrameCount], b.xLoc, b.yLoc, Color.BLUE, this);
            // System.out.println(b);
            smoothCounter++;
        }        



    }

    public Dimension getPreferredSize() {
        return new Dimension(frameWidth, frameHeight);
    }

    //public void update(){
    public void update(Collection<Boat> fleet){ //, Collection<Shore> shorePiece, Collection<Barrier> barriers) {
        if(!paused){

            this.fleet = fleet;


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