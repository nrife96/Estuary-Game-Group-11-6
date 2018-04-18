
import java.awt.EventQueue;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.AbstractAction;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class Controller extends JFrame {

    Action drawAction;
    final int drawDelay = 30; //msec

	private Model model;
	private View view;
	
	private final int DEFAULT_TIME_REMAINING = 300;
	
	int timeRemaining; //
	
	public Controller(){
		drawAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                // update based on whether the game is "paused"

                model.update();
                view.update();

            }//actionPerformed
		};//AbastractAction
		
		add(view);
        
        // add the settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());                // i added this (helps display the button correctly)
        setSize(view.getWidth(), view.getHeight());
        setVisible(true);

	}

	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                Controller a = new Controller();
                Timer t = new Timer(a.drawDelay, a.drawAction);
                t.start();
            }//run
        });//runnable
    }//main

}
