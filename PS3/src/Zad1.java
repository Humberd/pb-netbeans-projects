
public class Zad1 {
	private int temp;
	private int size;
	private int[] tempTab;
	private int n;
	
	public int[] iteracja1(int tab[]){
		size = tab.length-1;
		if (tab.length%2 == 1){
			for (int i = 0; i<(tab.length-1)/2;i++){
				temp = tab[i];
				tab[i] = tab[size];
				tab[size] = temp;
				size--;
			}
		} else {
			for (int i = 0; i<tab.length/2;i++){
				temp = tab[i];
				tab[i] = tab[size];
				tab[size] = temp;
				size--;
			}
		}
		return tab;
	}
	public int[] getTempTab(){
		return tempTab;
	}
	public void setN(int nn){
		n=nn;
	}
}
