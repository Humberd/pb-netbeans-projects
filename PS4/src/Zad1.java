

public class Zad1 {
	int opElem = 0;

	public int[] podciag(int[] ciag1, int[] ciag2){
		int[][] tab = new int[ciag1.length + 1][ciag2.length + 1];
		System.out.println("||"+ciag1.length + " " +ciag2.length +"");
		
		for (int i=0; i<ciag1.length+1;i++)
			tab[i][0] = 0;
		for (int i=0; i<ciag2.length+1;i++)
			tab[0][i] = 0;
		int x = 0, y =0;
		for (int i=1; i<ciag1.length+1;i++){
			for (int j=1; j<ciag2.length+1;j++){
				System.out.println(ciag1[x]+" "+ciag2[y]);
				if (ciag1[x]==ciag2[y])
					tab[i][j] = tab[i-1][j-1] +1;
				else if (tab[i-1][j]>tab[i][j-1])
					tab[i][j] = tab[i-1][j];
				else
					tab[i][j] = tab[i][j-1];
				y++;
			}
			x++;
			y=0;
		}
		System.out.println();
		for (int i=0;i<ciag1.length+1;i++){
			for (int j=0; j<ciag2.length+1; j++){
				System.out.print(tab[i][j]+" ");
			}
			System.out.println();
		}
		int[] wyn1 = new int[tab[ciag1.length][ciag2.length]];
		
		int i = ciag1.length, j = ciag2.length;
		int index = 0;
		
		while (i!=0 && j!=0){
			if (tab[i-1][j] == tab[i][j]){
				i--;
				opElem++;
			}
			else if (tab[i][j-1] == tab[i][j]){
				j--;
				opElem++;
			}
			else {
				wyn1[index++] = ciag1[i-1];
				opElem++;
				i--;
				j--;
			}
		}
		return wyn1;
	}
	public int getOpElem(){
		return opElem;
	}
}
