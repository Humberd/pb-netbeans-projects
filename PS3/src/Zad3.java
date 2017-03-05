
public class Zad3 {
	private int rozwRek=0;
	private int opElem = 0;
	public void rekurencyjne3 (int n){
		if (n<0) return;
		else if (n==0) {
			rozwRek++;
			return;
		}
		else if (n>0) {
			opElem++;
			rekurencyjne3(n-1);
			rekurencyjne3(n-2);
			rekurencyjne3(n-3);
			rekurencyjne3(n-4);
			rekurencyjne3(n-5);
			rekurencyjne3(n-6);
		}
	}
	public int getRozw(){
		return rozwRek;
	}
	public int getOpElem(){
		return opElem;
	}
	public void iteracyjnie3(int n){
		for (int i=1; i<=n; i++){
			
		}
	}
}
