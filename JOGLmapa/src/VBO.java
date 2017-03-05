import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;

import static com.jogamp.opengl.GL.*;  // GL constants
import static com.jogamp.opengl.GL2.*; // GL2 constants

public class VBO {
	int vertices;
	FloatBuffer vertex_data;
	FloatBuffer color_data;
	OBJReader obj;
	List<float[]> vertexArray;
	List<int[]> facesArray;
	int vbo;
//	int vertexStride;
//	int colorPointer;
//	int vertexPointer;
	int bytesPerFloat = Float.SIZE /Byte.SIZE;
	
	public void createVBO(GL2 gl, OBJReader obj) {
		this.obj = obj;
		this.vertexArray = this.obj.getVertexArray();
		this.facesArray = this.obj.getFacesArray();
		this.vertices = this.facesArray.size() * 3;
		
		IntBuffer buf = Buffers.newDirectIntBuffer(1);
		gl.glGenBuffers(1, buf);
		vbo = buf.get();
		
		float[][] colorArray= {{0.7f,0.7f,1f},{1,0,0.3f},{0.3f,1f, 0.3f}};
		vertex_data = Buffers.newDirectFloatBuffer(this.facesArray.size()*18);
		for (int i = 0 ; i<this.facesArray.size(); i++) {
			for (int j = 0; j < 3; j++) {
				vertex_data.put(colorArray[i%3]);
				vertex_data.put(this.vertexArray.get(this.facesArray.get(i)[j]));
			}
		}
		vertex_data.rewind();
		
//		color_data = Buffers.newDirectFloatBuffer(this.facesArray.size()*9);
		
//		int bytesPerFloat = Float.SIZE /Byte.SIZE;
		
		int numBytes = vertex_data.capacity() * bytesPerFloat;
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vbo);
		gl.glBufferData(GL.GL_ARRAY_BUFFER, numBytes, vertex_data, GL.GL_STATIC_DRAW);
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, 0);
		
//		vertexStride = 6*bytesPerFloat;
//		vertexPointer = 3*bytesPerFloat;
//		colorPointer = 0;
		
		//VERTEX ARRAY//
//		this.obj = obj;
//		this.vertexArray = this.obj.getVertexArray();
//		this.facesArray = this.obj.getFacesArray();
//		System.out.println(this.facesArray.size());
//		this.vertices = this.facesArray.size() * 3;
//		
//		vertex_data = Buffers.newDirectFloatBuffer(this.facesArray.size()*9);
//		
//		for (int i = 0 ; i<this.facesArray.size(); i++) {
//			for (int j = 0; j < 3; j++) {
//				vertex_data.put(this.vertexArray.get(this.facesArray.get(i)[j]));
//			}
//		}
//		vertex_data.rewind();
		
	}
	public void renderVBO(GL2 gl) {
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vbo);
		gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL2.GL_COLOR_ARRAY);
		
		gl.glColorPointer(3,GL.GL_FLOAT,6*bytesPerFloat, 0);
		gl.glVertexPointer(3, GL.GL_FLOAT, 6*bytesPerFloat, 3*bytesPerFloat);
		
		gl.glDrawArrays(GL.GL_TRIANGLES,0,vertices);
		
		gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL2.GL_COLOR_ARRAY);
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, 0);
		
		//VERTEX ARRAY//
//		gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
//		
//		gl.glVertexPointer(3, GL.GL_FLOAT, 0, vertex_data);
//		gl.glColor3f(255, 0, 0);
//		gl.glDrawArrays(GL.GL_TRIANGLES,0,this.facesArray.size()*3);
//		
//		gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
	}
}
