import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

class View extends JPanel {
	BufferedImage[] boat;
	BufferedImage[] wake;
	BufferedImage[] sand;
	BufferedImage[][] barriers;
	BufferedImage water;		// assuming single (background) image of water

	final int frameWidth = 800;
	final int frameHeight = 800;
	final int boatWidth = 60;
	final int boatHeight = 60;
	final int sandWidth = 30;		// applies to both sand and barriers
	final int sandHeight = 30;


	// Tool activeTool;			// should we instead just call getter from the Model?

	boolean paused;

	public View() {
		// initialize stuff
		paused = true;
	}

	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public Dimension getPreferredSize() {
        return new Dimension(frameWidth, frameHeight);
    }

    public void update(){
    // public void update(Collection<Boat> boats, Collection<Sand> sandBlocks, Collection<Barrier> barriers) {
        if(!paused){
            // redraw board
            repaint();
        }
    }

    public int getWidth(){return frameWidth;}
    
    public int getHeight(){return frameHeight;}

    public int getBoatWidth(){return boatWidth;}

    public int getBoatHeight(){return boatHeight;}

    public int getSandWidth(){return sandWidth;}

    public int getSandHeight(){return sandHeight;}

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