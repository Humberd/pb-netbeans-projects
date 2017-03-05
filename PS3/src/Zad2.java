
public class Zad2 {
	private float dist;
	private float temp;
	private int wsp1;
	private int wsp2;
	private int opElem=0;
	public float iteracja2(int[] tabX, int[] tabY){
		
		for (int i = 0; i<tabX.length-1; i++){
			for (int j = i+1; j<tabY.length; j++){
				temp = (float) Math.sqrt(Math.pow(tabX[i]-tabX[j],2)+Math.pow(tabY[i]-tabY[j],2));
				System.out.println("("+tabX[i]+","+tabY[i]+") i "+"("+tabX[j]+","+tabY[j]+") = "+temp);
				opElem++;
				if (i==0 && j==1) {
					dist = temp;
					wsp1=i;
					wsp2=j;
				}
				else if (temp < dist){
					dist = temp;
					wsp1=i;
					wsp2=j;
				}
			}
		}
		return dist;
	}
	public int getwsp1 (){
		return wsp1;
	}
	public int getwsp2 (){
		return wsp2;
	}
	public int getOpElem (){
		return opElem;
	}
}
