package engineTester;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
		float[] verticies = {
				-0.5f, 0.5f, 0f, //v0
				-0.5f, -0.5f, 0f, //v1
				0.5f, -0.5f, 0f, //v2
				0.5f, 0.5f, 0f, //v3
 
		};
		
		int [] indices = {
				0,1,3,
				3,1,2,
		};
		
		float[] textureCoords = {
				0,0,
				0,1,
				1,1,
				1,0
		};
		
		RawModel model = loader.loadToVAO(verticies,textureCoords, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("kosmos"));
		TexturedModel texturedModel = new TexturedModel(model,texture);
		
		while(!Display.isCloseRequested()){
			renderer.prepare();
			shader.start();
			renderer.render(texturedModel);
			shader.stop();
			
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
