import java.util.ArrayList;



public class Zad2 {
	private int[][] AA;
	int opElem=0;
	
	public int[] wybor (int[][] A){
		AA=sortowanie(A);
		ArrayList<Integer> tab = new ArrayList<Integer>();
		tab.add(0);
		int j = 0;
		System.out.println("____________");
		for (int i=1; i<A.length;i++){
			opElem++;
			if (A[i][0]>=A[j][1]){
				opElem++;
				tab.add(i);
				j=i;
			}
		}
		int[] wsp = new int[tab.size()];
		for (int i=0; i<tab.size();i++){
			wsp[i] = tab.get(i);
			System.out.println(wsp[i]);
		}
		return wsp;
	}
	public int[][] dynamicznie(int[][]A){
		AA=sortowanie(A);
		
		return A;
	}
	public int[][] sortowanie(int[][]B){
		/*int min=24;
		int max=0;
		for (int i=0; i<B.length;i++){
			if (B[i][1]> max)
				max = B[i][1];
			if (B[i][1]<min){
				min = B[i][1];
			}
		}
		//////////sortowanie przez zliczanie WTF///////////
		int[][] count = new int[max-min+1][2];
		for(int i = 0;i< B.length;i++){
			count[B[i][1]-min][1]++;
			//count[B[i][1]-min][1] = B[i][1];
		}
		int z=0;
		for (int i=min;i<=max;i++){
			while(count[i-min][1]>0){
				B[z][1] = i;
				z++;
				count[i-min][1]--;
			}
		}*/
		for (int i=0;i<B.length;i++){
			for (int j=0; j<B.length-1;j++){
				if (B[j][1]>B[j+1][1]){
					int temp;
					int temp1;
					temp = B[j+1][1];
					temp1 = B[j+1][0];
					B[j+1][1] = B[j][1];
					B[j+1][0] = B[j][0];
					B[j][1] = temp;
					B[j][0] = temp1;
				}
			}
		}
		System.out.println("______________");
		for (int i=0;i<B.length;i++){
			for (int j=0;j<2;j++){
				System.out.print(B[i][j]+" ");
			}
			System.out.println();
		}
		return B;
	}
	public int[][] getAA(){
		return AA;
	}

}
