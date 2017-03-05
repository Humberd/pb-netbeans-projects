import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import static com.jogamp.opengl.GL.*;  // GL constants
import static com.jogamp.opengl.GL2.*; // GL2 constants


@SuppressWarnings("serial")
public class Renderer extends GLCanvas implements GLEventListener, KeyListener{
	private GLU glu;
	private float rtriangle = 0.0f;
	
	public Renderer() {
		addGLEventListener(this);
		addKeyListener(this);
	}
	
	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		glu = new GLU();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); //kolor tla
		gl.glClearDepth(1.0f);
		gl.glEnable(GL_DEPTH_TEST);
		gl.glDepthFunc(GL_LEQUAL);
		gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		gl.glShadeModel(GL_SMOOTH);
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		szescian1(gl);
		
	}
	
	private GL2 szescian1(GL2 gl) {
		gl.glTranslatef(0.0f, 0.0f, -6.0f);
		
		gl.glRotatef(rtriangle, 0.0f, 1.0f, 0.0f); //rotacje
		gl.glRotatef(45.0f, 0.0f, 0.0f, 1.0f); //zaczecie pod katem
		
		gl.glBegin(GL_QUADS);
			gl.glColor3f(0, 1, 0); //green
			gl.glVertex3f(1f, 1f, 1f);
			gl.glColor3f(1, 0, 0); //red
			gl.glVertex3f(1f, -1f, 1f);
			gl.glColor3f(0, 0, 1); //blue
			gl.glVertex3f(-1f, -1f, 1f);
			gl.glColor3f(0, 1, 1); //light blue
			gl.glVertex3f(-1.0f, 1.0f, 1f);
			
			gl.glColor3f(0, 1, 1); //light blue
			gl.glVertex3f(-1.0f, 1.0f, 1f);
			gl.glColor3f(0, 0, 1); //blue
			gl.glVertex3f(-1f, -1f, 1f);
			gl.glColor3f(1, 0, 0); //red
			gl.glVertex3f(-1f, -1f, -1f);
			gl.glColor3f(0, 1, 0); //green
			gl.glVertex3f(-1f, 1f, -1f);
			
			gl.glColor3f(1, 0, 0); //red
			gl.glVertex3f(-1f, -1f, -1f);
			gl.glColor3f(0, 1, 0); //green
			gl.glVertex3f(-1f, 1f, -1f);
			gl.glColor3f(0, 0, 1); //blue
			gl.glVertex3f(1f, 1f, -1f);
			gl.glColor3f(0, 1, 1); //light blue
			gl.glVertex3f(1f, -1f, -1f);
			
			gl.glColor3f(0, 0, 1); //blue
			gl.glVertex3f(1f, 1f, -1f);
			gl.glColor3f(0, 1, 1); //light blue
			gl.glVertex3f(1f, -1f, -1f);
			gl.glColor3f(1, 0, 0); //red
			gl.glVertex3f(1f, -1f, 1f);
			gl.glColor3f(0, 1, 0); //green
			gl.glVertex3f(1f, 1f, 1f);
			
			gl.glColor3f(0, 1, 0); //green
			gl.glVertex3f(1f, 1f, 1f);
			gl.glColor3f(0, 1, 1); //light blue
			gl.glVertex3f(-1f, 1f, 1f);
			gl.glColor3f(0, 1, 0); //green
			gl.glVertex3f(-1f, 1f, -1f);
			gl.glColor3f(0, 0, 1); //blue
			gl.glVertex3f(1f, 1f, -1f);
			
			gl.glColor3f(1, 0, 0); //red
			gl.glVertex3f(1f, -1f, 1f);
			gl.glColor3f(0, 0, 1); //blue
			gl.glVertex3f(-1f, -1f, 1f);
			gl.glColor3f(1, 0, 0); //red
			gl.glVertex3f(-1f, -1f, -1f);
			gl.glColor3f(0, 1, 1); //light blue
			gl.glVertex3f(1f, -1f, -1f);
			
		gl.glEnd();
		return gl;
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL2 gl = drawable.getGL().getGL2();
		
		if (height == 0)
			height = 1;
		
		float aspect = (float)width/height;
		
		gl.glViewport(0, 0, width, height);
		
		gl.glMatrixMode(GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(45.0, aspect, 0.5, 100.0);
		
		gl.glMatrixMode(GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	

	@Override
	public void keyPressed(KeyEvent arg0) {
		int keyCode = arg0.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_E: 
			rtriangle += 1.5f;
			StaticSound.volume(rtriangle);
			System.out.println("LOUDER  " + rtriangle);
			break;
		case KeyEvent.VK_Q:
			rtriangle -= 1.5f;
			StaticSound.volume(rtriangle);
			System.out.println("QUIETER  " + rtriangle);
			break;
		case KeyEvent.VK_P:
			StaticSound.play();
			System.out.println("PLAY!");
			break;
		case KeyEvent.VK_H:
			StaticSound.pause();
			System.out.println("PAUSE");
			break;
		case KeyEvent.VK_A:
			rtriangle-= 1.5f;
			System.out.println("LEFT  " + rtriangle);
			break;
		case KeyEvent.VK_D:
			rtriangle+=1.5f;
			System.out.println("RIGHT  " + rtriangle);
			break;
		case KeyEvent.VK_Z:
			rtriangle -=1.5f;
			System.out.println("LEFT SPEAKER  " + rtriangle);
			//StaticSound.leftSpeaker();
			StaticSound.leftSpeakerBeta();
			break;
		case KeyEvent.VK_C:
			rtriangle +=1.5f;
			System.out.println("RIGHT SPEAKER " + rtriangle);
			//StaticSound.rightSpeaker();
			StaticSound.rightSpeakerBeta();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
}
