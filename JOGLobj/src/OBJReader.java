import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OBJReader {
	BufferedReader br;
	private List<float[]> vertexArray = new ArrayList<float[]>();
	private List<int[]> facesArray = new ArrayList<int[]>();
	public OBJReader(String src) {
		try {
			br = new BufferedReader(new FileReader(src));
			String currentLine;
			String[] vertices;
			float[] parsedVertices = new float[3];
			int [] parsedFaces = new int [3];
					
			while ((currentLine = br.readLine())!=null) {
				//sprawdzam czy zaczynaja sie wierzcholki
				if (currentLine.startsWith("v ")) {
					currentLine = currentLine.substring(2);
					vertices = currentLine.split(" ");
					int i = 0;
					for (String value : vertices) {
						parsedVertices[i] = Float.parseFloat(value);
						i++;
					}
					getVertexArray().add(new float[] {parsedVertices[0],parsedVertices[1],parsedVertices[2]});
				} else if (currentLine.startsWith("f ")) {
					currentLine = currentLine.substring(2);
					vertices = currentLine.split(" ");
					int i = 0;
					for (String value : vertices) {
						parsedFaces[i] = Integer.parseInt(value)-1;
						i++;
					}
					facesArray.add(new int [] {parsedFaces[0], parsedFaces[1], parsedFaces[2]});
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Nie ma takiego pliku!");
		}
		for (float[] value : getVertexArray()) {
			System.out.println(Arrays.toString(value));
		}
		for (int[] value : facesArray) {
			System.out.println(Arrays.toString(value));
		}
	}
	public List<float[]> getVertexArray() {
		return vertexArray;
	}
	public List<int[]> getFacesArray() {
		return facesArray;
	}
}
