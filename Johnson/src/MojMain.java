import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class MojMain 
{
    public static void main(String... arg) throws IOException{
    	int macierzSasiedztwa[][];
    	final int MAX = 999;
        int liczbaWierzcholkow;
        Scanner scan = new Scanner(System.in);
 
        try {
            System.out.println("Enter the number of vertices");
            liczbaWierzcholkow = scan.nextInt();
            macierzSasiedztwa = new int[liczbaWierzcholkow + 1][liczbaWierzcholkow + 1];
 
            System.out.println("Enter the Weighted Matrix for the graph");
            for (int i = 1; i <= liczbaWierzcholkow; i++){
                for (int j = 1; j <= liczbaWierzcholkow; j++){
                    macierzSasiedztwa[i][j] = scan.nextInt();
                    if (i == j) {
                        macierzSasiedztwa[i][j] = 0;
                        continue;
                    }
                    if (macierzSasiedztwa[i][j] == 0)
                        macierzSasiedztwa[i][j] = MAX;
                }
            }
 
            Johnson johnsonsAlgorithm = new Johnson(liczbaWierzcholkow);
            johnsonsAlgorithm.johnsonsAlgorithms(macierzSasiedztwa);
        } catch (InputMismatchException ime)
        {
            System.out.println("Wrong Input Format");
        }
        scan.close();
        //GUI gui = new GUI();
        
        
    }
}
 

 