import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class PS4 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new FileReader("zad1.txt"));
		String[] string1 = read.readLine().split(" ");
		String[] string2 = read.readLine().split(" ");
		int[] ciag1 = new int[string1.length];
		int[] ciag2 = new int[string2.length];
		for (int i=0; i<string1.length;i++){
			ciag1[i] = Integer.parseInt(string1[i]);
		}
		for (int i=0; i<string2.length;i++){
		        ciag2[i] = Integer.parseInt(string2[i]);
		}
		read.close();
		for (int i=0; i<ciag1.length;i++)
			System.out.print(ciag1[i]+ " ");
		System.out.println();
		for (int i=0; i<ciag2.length;i++)
			System.out.print(ciag2[i]+ " ");
		
		Zad1 zad1 = new Zad1();
		int[] wyn1 = zad1.podciag(ciag1,ciag2);
		
		for (int i = wyn1.length-1;i>=0;i--)
			System.out.print(wyn1[i]+" ");
		PrintWriter pw = new PrintWriter ("odp1.txt");
		for (int i = 0; i<wyn1.length; i++){
			pw.print(wyn1[i]+ " ");
		}
		pw.println();
		pw.print(zad1.getOpElem());
		pw.close();
		System.out.println();
		///////////////////////ZAD2//////////////////////////
		/*BufferedReader reader = new BufferedReader (new FileReader ("zad2.txt"));
		int lines = 0;
		while (reader.readLine() != null){
			lines++;
		}
		reader.close();
		int [][] tab = new int[lines][2];
		Scanner sc = new Scanner (new File ("zad2.txt"));
		for (int i = 0; i<lines; i++){
			for (int j =0; j<2;j++){
				tab[i][j] = sc.nextInt();
			}
		}
		for (int i=0;i<lines;i++){
			for (int j=0;j<2;j++){
				System.out.print(tab[i][j]+" ");
			}
			System.out.println();
		}
		
		Zad2 zad2 = new Zad2();
		int[] wsp = zad2.wybor(tab);
		int godz = 0;
		tab=zad2.getAA();
		System.out.println("_________");
		for (int i=0 ;i<tab.length;i++){
			for (int j=0 ; j<wsp.length;j++){
				if (i==wsp[j]){
					System.out.println(tab[i][0]+" "+tab[i][1]);
					godz+= tab[i][1] - tab[i][0];
					break;
				}
			}
		}
		System.out.println(godz);
		
		PrintWriter pw2 = new PrintWriter("odp2.txt");
		pw2.println(godz);
		for (int i=0; i<wsp.length;i++){
			pw2.println(tab[i][0]+" "+tab[i][1]);
		}
		pw2.print(zad2.opElem);
		pw2.close();*/
	}

}
