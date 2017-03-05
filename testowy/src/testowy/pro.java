package testowy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Random;

public class pro {

	public static void main(String[] args) throws FileNotFoundException {
		/////////////////////////////ZADANIE 1/////////////////////////////
		Scanner odczyt1 = new Scanner(new File("wej1.txt"));
		int x = odczyt1.nextInt();
		int[] tab1 = new int[4];
		int wynik = 0;
		int ileRazy = 0;
		
		while(odczyt1.hasNext()){
			for (int i=0; i<4; i++){
				tab1[i] = odczyt1.nextInt();
			}
		}
		for (int i=0; i<tab1.length; i++){			//rz퉐 n^2
			int potegowanie = 1;
			for (int j=0; j<i; j++){
				potegowanie = potegowanie * x;
				ileRazy++;
			}
			wynik = wynik + tab1[i] * potegowanie;
			ileRazy=ileRazy+2;
		}
		
		PrintWriter plik1a = new PrintWriter (new File ("wyj1a.txt"));
		plik1a.println(wynik);
		plik1a.println(ileRazy);
		odczyt1.close();
		plik1a.close();
		
		//////////////////////////////ZADANIE 1///////////////////////////
		
		wynik=tab1[tab1.length-1];
		ileRazy=0;
		for (int i=tab1.length-2; i>-1; i--){	//rz퉐 n
			wynik=x*wynik+tab1[i];
			ileRazy=ileRazy+2;
		}
		PrintWriter plik1b = new PrintWriter (new File ("wyj1b.txt"));
		plik1b.println(wynik);
		plik1b.println(ileRazy);
		plik1b.close();
		
		//////////////////////////////ZADANIE 2//////////////////////////
		
		Scanner odczyt2 = new Scanner(new File("wej2.txt"));
		int y = odczyt2.nextInt();
		int[] tab2 = new int[10];
		PrintWriter plik2a = new PrintWriter(new File("wyj2a.txt"));
		ileRazy = 0;
		boolean czyByl = false;
		
		for(int i=0; i<10; i++){
			tab2[i]= odczyt2.nextInt();
			
		}
		for (int i=0; czyByl==false && i<10; i++){		//rz퉐 n
			if (y==tab2[i]){
				plik2a.print(i);
				czyByl = true;
			}
			ileRazy++;
		}
		if(czyByl == false){
			plik2a.println(-1);
		} else {
			plik2a.println();
		}
		plik2a.print(ileRazy);
		odczyt2.close();
		plik2a.close();
		
		/////////////////////////////ZADANIE 2/////////////////////////
		Random los = new Random();
		PrintWriter plik2b = new PrintWriter(new File ("wyj2b.txt"));
		czyByl = false;
		ileRazy = 0;
		
		for (int i = 0; czyByl == false && i<tab2.length; i++){
			if (y==tab2[los.nextInt(tab2.length)]){
				plik2b.print(i);
				czyByl = true;
			}
			ileRazy++;
		}
		if(czyByl == false){
			plik2b.println(-1);
		} else {
			plik2b.println();
		}
		plik2b.print(ileRazy);
		odczyt2.close();
		plik2b.close();
		/////////////////////////////ZADANIE 3/////////////////////////
		Scanner odczyt3 = new Scanner(new File("wej3.txt"));
		int n = odczyt3.nextInt();
		int k = odczyt3.nextInt();
		wynik = 0;
		int silniaN = 1;
		int silniaK = 1;
		int silniaR = 1;
		ileRazy = 0;
		
		for (int i=1; i<n+1; i++){			//rz퉐 n
			silniaN = silniaN*i;
			ileRazy++;
		}
		for (int i=1; i<k+1; i++){
			silniaK = silniaK*i;
			ileRazy++;
		}
		for (int i=1; i<(n-k)+1; i++){
			silniaR = silniaR*i;
			ileRazy++;
		}

		wynik = silniaN/(silniaK*silniaR);
		
		PrintWriter plik3a = new PrintWriter (new File ("wyj3a.txt"));
		plik3a.println(wynik);
		plik3a.print(ileRazy+1);
		
		odczyt3.close();
		plik3a.close();
		
		///////////////////////////ZADANIE 3////////////////////////
		
		wynik = 1;
		ileRazy = 0;
		silniaR = n;
		
		for (int i=1; i<(k+1); i++){		//rz퉐 n
			wynik = wynik * (n-i+1)/i;
			ileRazy++;
		}
		
		PrintWriter plik3b = new PrintWriter (new File ("wyj3b.txt"));
		plik3b.println(wynik);
		plik3b.println(ileRazy);
		plik3b.close();
	}
	

}
