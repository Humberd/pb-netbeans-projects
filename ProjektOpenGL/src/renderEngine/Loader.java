package renderEngine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import models.RawModel;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Loader {
	
	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();
	private List<Integer> textures = new ArrayList<Integer>();
	
	public RawModel loadToVAO(float[] positions, float[] textureCoords, int [] indices){ 		//wchodza wszystkie wierzcholki
		int vaoID = createVAO();						//musi dla naszego obiektu stworzyc tablice VAO
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0,3, positions);
		storeDataInAttributeList(1,2, textureCoords);
		unbindVAO();
		return new RawModel(vaoID, indices.length);
	}
	
	public int loadTexture(String fileName){
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream("res/"+fileName+".png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int textureID = texture.getTextureID();
		textures.add(textureID);
		return textureID;
	}
	
	public void cleanUp(){
		for (int vao:vaos){
			GL30.glDeleteVertexArrays(vao);
		}
		for (int vbo:vbos){
			GL15.glDeleteBuffers(vbo);
		}
		for (int texture:textures){
			GL11.glDeleteTextures(texture);
		}
	}
	
	private int createVAO(){
		int vaoID = GL30.glGenVertexArrays();			//zglasza dla OpenGLa prosbe o stworzenie nowej tablicy VAO, tym samym zwraca jej id
		GL30.glBindVertexArray(vaoID);					//aktywuje VAO;
		vaos.add(vaoID);								//dodaje id VAO do tablicy
		return vaoID;
	}
	
	private void storeDataInAttributeList(int attrubuteNumber, int size, float[] data){
		int vboID = GL15.glGenBuffers();				//tworzy puste VBO, ktore bedzie jako jeden z atrybutow VAO
		vbos.add(vboID);								//dodaje id VBO do tablicy;
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(data);// wrzuca nasze wierzcholki do bufora
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);//do VBO mozna wrzucic wierzcholki tylko znajdujace sie w buferze
		//(GL15.GL_ARRAY_BUFFER) - typ bufora, (GL15.GL_STATIC_DRAW) - po co bedziemy go uzywac, w tym przypadku mowimy ze nigdy nie zedytujemy danych zawartych w VBO
		GL20.glVertexAttribPointer(attrubuteNumber, size, GL11.GL_FLOAT, false, 0,0);//wrzucenie VBO do VAO,
		//(pozycja w VAO, dlugosc kazdego wierzcholka, typ danych, czy dane sa znormalizowane, dystans pomiedzy kazdym wierzcholkiem, czyli czy mamy cos pomiedzy nimi, offset)
		unbindVBO();			//dezaktywuje VBO
	}
	
	private void unbindVBO(){
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);		//dezaktywuje VBO
	}
	
	private void unbindVAO(){
		GL30.glBindVertexArray(0);						//dezaktywuje VAO
	}
	
	private FloatBuffer storeDataInFloatBuffer(float[] data){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();  								//przygotowanie bufera, by mozna bylo z niego czytac
		return buffer;	
	}
	//////////////////////////////////////////////////////////////////////////////////////////
	private void bindIndicesBuffer(int[] indices){
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = storeDataInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		
	}
	
	private IntBuffer storeDataInIntBuffer(int[] data){
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
}
