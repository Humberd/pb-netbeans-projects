import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jogamp.opengl.GL2;

public class Map {
	private BufferedReader br;
	private Figure figure;
	private String currentLine;
	private GL2 gl;
	private static class Figure {
		static int nextId = 0;
		int id;
		int parentId;
		float[] translate = new float[3];
		float[] rotate = new float[3];
		float[] scale = new float[3];
		VBO vbo = new VBO();
		
		Figure () {
			nextId++;
			id = nextId;
		}
	}
	private List<Figure> figuresArray = new ArrayList<Figure>();
	private List<Integer> parentsArray = new ArrayList<Integer>();
	public Map (GL2 gl) {
		this.gl = gl;
	}
	public void createObject (String src) {
		try {
			br= new BufferedReader(new FileReader(src));
			while ((currentLine = br.readLine())!=null) {
				currentLine = currentLine.replaceAll("\\s+", "");
//				System.out.println(currentLine);
				if (currentLine.startsWith("<object>")) {
					figure = new Figure();
					figure.parentId = 0;
					readFigure();
				}
			}
			br.close();
//			System.out.println(Arrays.toString(figuresArray.get(0).translate));
//			System.out.println(Arrays.toString(figuresArray.get(0).rotate));
//			System.out.println(Arrays.toString(figuresArray.get(0).scale));
//			System.out.println(Arrays.toString(figuresArray.get(0).vbo.vertexArray.get(0)));
//			if (findParent(1) != null){
//				System.out.println("rozne");
//			} else {
//				System.out.println("null");
//			}
		} catch (IOException e) {
			System.err.println("Nie ma takiego pliku!");
		}
	}
	
	private void readFigure() throws IOException {
		while ((currentLine = br.readLine())!=null) {
			currentLine = currentLine.replaceAll("\\s+", "");
			if (currentLine.startsWith("translate")) {
				currentLine = currentLine.substring(10);
				String[] values = currentLine.split(",");
				int i=0;
				for (String value: values) {
					figure.translate[i] = Float.parseFloat(value);
					i++;
				}
				if (figure.parentId >0) {
					float childX =Float.parseFloat(values[0]);
					float childY = Float.parseFloat(values[1]);
					float childZ = Float.parseFloat(values[2]);
//					float s = (float) Math.sqrt(Math.pow(childZ, 2)+Math.pow(childY, 2));
//					float alphaToRad = (float) Math.toRadians(findParent(figure.parentId).rotate[0]);
//					float newY = (float) (s*Math.sin(alphaToRad));
//					float newZ = (float) Math.sqrt(Math.pow(s, 2)-Math.pow(newY, 2));
//					System.out.println("z: "+newZ+" y: "+newY);
//					
//					if (findParent(figure.parentId).rotate[0]%360 >=90 && findParent(figure.parentId).rotate[0]%360<=270) {
//						newZ = -newZ;
//					}
//					if (findParent(figure.parentId).rotate[0]%360 <=-90 && findParent(figure.parentId).rotate[0]%360>=-270) {
//						newZ = -newZ;
//					}
//					figure.translate[0] = figure.translate[0] + findParent(figure.parentId).translate[0];
//					figure.translate[1] =  findParent(figure.parentId).translate[1] - newY;
//					figure.translate[2] =  findParent(figure.parentId).translate[2] + newZ;
///////////////////
//					float alphaToRad = (float) Math.toRadians(findParent(figure.parentId).rotate[1]);
//					float betaToRad = (float) Math.toRadians(findParent(figure.parentId).rotate[0]);
//					float p = (float) Math.sqrt(Math.pow(childX, 2)+Math.pow(childZ, 2));
//					float s = (float) Math.sqrt(Math.pow(p, 2)+Math.pow(childY, 2));
//					System.out.println("Odleglosc: "+s);
//					float newY = (float) (s*Math.sin(alphaToRad));
//					float t = (float) Math.sqrt(Math.pow(s, 2)-Math.pow(newY, 2));
//					float newZ = (float) (t*Math.sin(betaToRad));
//					float newX = (float) Math.sqrt(Math.pow(t, 2)-Math.pow(newZ, 2));
//					System.out.println("x: "+newX+" y: "+(newY)+" z: "+newZ);
//					
//					if (findParent(figure.parentId).rotate[0]%360 >=90 && findParent(figure.parentId).rotate[0]%360<=270) {
//						newX = -newX;
//					}
//					if (findParent(figure.parentId).rotate[0]%360 <=-90 && findParent(figure.parentId).rotate[0]%360>=-270) {
//						newX = -newX;
//					}
//					if (findParent(figure.parentId).rotate[1]%360 >=90 && findParent(figure.parentId).rotate[1]%360<=270) {
//						newX = -newX;
//					}
//					if (findParent(figure.parentId).rotate[1]%360 <=-90 && findParent(figure.parentId).rotate[1]%360>=-270) {
//						newX = -newX;
//					}
//////////////////////////////////////////////
//					figure.translate[0] +=  findParent(figure.parentId).translate[0] + newY;
//					figure.translate[1] +=  findParent(figure.parentId).translate[1] - newZ;
//					figure.translate[2] +=  findParent(figure.parentId).translate[2] + newX;

//					float r = (float) Math.sqrt(Math.pow(childX, 2)+Math.pow(childY, 2)+Math.pow(childZ,2));
//					float alphaToRad = (float) Math.toRadians(findParent(figure.parentId).rotate[1]);
//					float betaToRad = (float) Math.toRadians(findParent(figure.parentId).rotate[0]);
//					float phi = (float) Math.atan(childY/childX) + alphaToRad;
//					float theta = (float) Math.asin(childZ/r) + betaToRad;
//					//phi - alfa, theta - beta
//					float newX = (float) (r*Math.cos(theta)*Math.cos(phi));
//					float newY = (float) (r*Math.cos(theta)*Math.sin(phi));
//					float newZ = (float) (r*Math.sin(theta));
//					System.out.println("newX: "+newX+" newY: "+(newY)+" newZ: "+newZ);
					
//					System.out.println("r: "+r+" phi: "+phi+" theta: "+theta);
					float odl = (float) Math.sqrt(Math.pow(childX, 2)+Math.pow(childZ, 2));
					float roznicaKatow = (float) (Math.toRadians(findParent(figure.parentId).rotate[1]) + Math.atan(Math.abs(childZ/childX)));
					if (childX < 0 && childZ >0)
						roznicaKatow += Math.PI/2;
					else if (childX< 0 && childZ <0)
						roznicaKatow += Math.PI;
					else if (childX > 0 && childZ <0)
						roznicaKatow += (3*Math.PI)/2;
					System.out.println(Math.toDegrees(roznicaKatow));
					float zTempAlfa = (float) (odl*Math.sin(roznicaKatow));
					float xTempAlfa = (float) Math.sqrt(Math.pow(odl, 2)- Math.pow(zTempAlfa, 2));
					if (roznicaKatow > Math.toRadians(90) && roznicaKatow < Math.toRadians(270) || roznicaKatow < Math.toRadians(-90) && roznicaKatow >Math.toRadians(-270)) {
						xTempAlfa = -xTempAlfa;
					}
					System.out.println("xTemp: "+xTempAlfa+" zTemp: "+zTempAlfa);
					figure.translate[0] =  findParent(figure.parentId).translate[0] + xTempAlfa;
					figure.translate[1] =  findParent(figure.parentId).translate[1] + childY;
					figure.translate[2] =  findParent(figure.parentId).translate[2] + zTempAlfa;
					
//					float childX =Float.parseFloat(values[0]);
//					float childY = Float.parseFloat(values[1]);
//					float childZ = Float.parseFloat(values[2]);
//					float parX = findParent(figure.parentId).translate[0];
//					float parY = findParent(figure.parentId).translate[1];
//					float parZ = findParent(figure.parentId).translate[2];
////					System.out.println(childX+" "+childY+ " "+childZ);
//					System.out.println("P"+parX+" "+parY+" "+parZ);
//					float odlX = childX;
//					float odlY = childY;
//					float odlZ = childZ;
//					System.out.println("X: "+odlX+" Y: "+odlY+ " Z: "+odlZ);
//					
//					float p = (float) Math.sqrt(Math.pow(odlX, 2)+Math.pow(odlZ, 2));
//					float odlTot = (float) Math.sqrt(Math.pow(p, 2)+Math.pow(odlY, 2));
////					System.out.println("OdlTot: "+odlTot);
//					
//					float alphaToRad = (float) Math.toRadians(findParent(figure.parentId).rotate[0]);
//					System.out.println(alphaToRad);
//					float yCoord = (float) (odlTot * Math.sin(alphaToRad));
//					float r = (float) Math.sqrt(Math.pow(yCoord, 2)+Math.pow(odlTot, 2));
//					float betaToRad =(float) Math.toRadians(findParent(figure.parentId).rotate[1]);
//					float zCoord = (float) (r * Math.sin(betaToRad));
//					float xCoord = (float) Math.sqrt(Math.pow(zCoord, 2)+Math.pow(r, 2));
////					System.out.println("X:" + xCoord+" Y: "+yCoord+" Z: "+zCoord);
//					
//					float s = (float) Math.sqrt(Math.pow(xCoord, 2)+Math.pow(yCoord, 2));
//					float gammaToRad = (float) Math.toRadians(findParent(figure.parentId).rotate[2]);
//					float h = (float) (s*Math.sin(gammaToRad));
//					float delta = (180-findParent(figure.parentId).rotate[2])/2;
//					float deltaToRad = (float) Math.toRadians(delta);
//					float a = (float) (h/Math.sin(deltaToRad));
//					float sx = (float) Math.sqrt(Math.pow(a, 2)+Math.pow(h, 2));
//					yCoord += h;
//					xCoord -= sx;
//					System.out.println("X:" + xCoord+" Y: "+yCoord+" Z: "+zCoord);
//					figure.translate[0] =  xCoord+parX+childX;
//					figure.translate[1] = yCoord+parY+childY;
//					figure.translate[2] =  zCoord+parZ+childZ;
					System.out.println("PX: "+findParent(figure.parentId).translate[0] + " PY: "+findParent(figure.parentId).translate[1]+" PZ: "+findParent(figure.parentId).translate[2]);
					System.out.println("X:" + figure.translate[0]+" Y: "+figure.translate[1]+" Z: "+figure.translate[2]);
					
				}
//				System.out.println(Arrays.toString(values));
			} else if (currentLine.startsWith("rotate")) {
				currentLine = currentLine.substring(7);
				String[] values = currentLine.split(",");
				int i=0;
				for (String value: values) {
					figure.rotate[i] = Float.parseFloat(value);
					if (figure.parentId>0) {
						figure.rotate[i] = figure.rotate[i] + findParent(figure.parentId).rotate[i];
					}
					i++;
				}
			} else if (currentLine.startsWith("scale")) {
				currentLine = currentLine.substring(6);
				String[] values = currentLine.split(",");
				int i=0;
				for (String value: values) {
					figure.scale[i] = Float.parseFloat(value);
					i++;
				}
			} else if (currentLine.startsWith("</object>")) {
				parentsArray.remove(parentsArray.size()-1);
				break;
			} else if (currentLine.startsWith("src")) {
				currentLine = currentLine.substring(4);
				currentLine = currentLine.replaceAll("\\\\","\\\\\\\\");
				OBJReader obj = new OBJReader(currentLine);
				VBO vbo = new VBO();
				vbo.createVBO(gl,obj);
				figure.vbo = vbo;
			} else if (currentLine.startsWith("<object>")) {
				figure = new Figure();
				figure.parentId = parentsArray.get(parentsArray.size()-1);
				readFigure();
			} else if (currentLine.startsWith("<end>")) {
				figuresArray.add(figure);
				parentsArray.add(figure.id);
			}
		}
	}
	
	public void renderFigures() {
		for (Figure figure: figuresArray) {
			gl.glPushMatrix();
			gl.glTranslatef(figure.translate[0], figure.translate[1], figure.translate[2]);
			gl.glRotatef(figure.rotate[0], 1, 0, 0);
			gl.glRotatef(figure.rotate[1], 0, 1, 0);
			gl.glRotatef(figure.rotate[2], 0, 0, 1);
			gl.glScalef(figure.scale[0], figure.scale[1], figure.scale[2]);
			figure.vbo.renderVBO(gl);
			gl.glPopMatrix();
		}
	}
	
	private Figure findParent(int value) {
		for (Figure figure: figuresArray){
			if (figure.id == value) {
				return figure;
			}
		}
		return null;
	}
}
