import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;


@SuppressWarnings("serial")
public class Window extends JFrame{
	
	private static String TITLE = "Moj OPENGL";
	private static final int CANVAS_WIDTH = 1024;//640
	private static final int CANVAS_HEIGHT = 720;//480
	private static final int FPS = 60;
	Toolkit t=Toolkit.getDefaultToolkit();
	Image img=new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
	Cursor pointer=t.createCustomCursor(img, new Point(0,0), "none");
	
	public Window(){
		GLCanvas canvas= new Renderer();
		final FPSAnimator animator = new FPSAnimator(canvas, FPS, true);
		getContentPane().add(canvas);
		
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				new Thread() {
					public void run() {
						if (animator.isStarted())
							animator.stop();
						System.exit(0);
					}
				}.start();
			}
		});
		
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH,CANVAS_HEIGHT));
		setTitle(TITLE);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setCursor(pointer); //robi niewidzialny kursor
		animator.start();
		/////////////////
	}
}
