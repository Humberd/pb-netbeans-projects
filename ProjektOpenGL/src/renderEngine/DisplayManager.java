package renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;


public class DisplayManager {
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final int FPS = 60;
	
	public static void createDisplay(){
		
		ContextAttribs attribs = new ContextAttribs(3,2) 		//ustawianie jakichs atrybutow
		.withForwardCompatible(true)
		.withProfileCore(true);
		
		try {
			
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT)); 	//ustawia rozdzielczosc
			Display.create(new PixelFormat(), attribs);
			//Display.setResizable(true);
			Display.setTitle("Truant");
			
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		GL11.glViewport(0, 0, WIDTH, HEIGHT); 		//renderowanie na calym obszarze
		
	}
	
	public static void updateDisplay(){
		Display.sync(FPS);		//liczba fpsow
		Display.update();
		
	}
	
	public static void closeDisplay(){
		Display.destroy();
	}
}