import java.util.Random;
import java.util.Scanner;


public class mojMain {
	private static int losuj(int min, int max){//min/max przedzial losowania
		return new Random().nextInt(max-min+1)+min;
	}
	
	private static boolean czyWygral(int wylosowana, int wybor){
		return wylosowana == wybor;
	}

	public static void main(String[] args) {
		System.out.println("Zgadnij liczbe: ");
		Scanner scanner = new Scanner(System.in);
		
		if (czyWygral(losuj(0,100),scanner.nextInt()))
			System.out.println("Gratulacje! Wygra�e�!");
		else {
			System.out.println("Niestety, przegra�es!");
			System.out.println("Spr�buj jeszcze raz!");
			main(null);
		}
			

	}

}
