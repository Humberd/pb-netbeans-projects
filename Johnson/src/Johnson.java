
public class Johnson {
	private int wezelJohnsona;
    private int liczbaWierzcholkow;
    private int powiekszonaMacierzWag[][];
    private int macierzBellmana[];
    private Bellman bellman;
    private Djikstra djikstra;
    public int[][] wYNIK;
 
    public static final int MAX = 999;
 
    public Johnson(int liczbaWierzcholkow){
        this.liczbaWierzcholkow = liczbaWierzcholkow;
        powiekszonaMacierzWag = new int[liczbaWierzcholkow + 2][liczbaWierzcholkow + 2];
        wezelJohnsona = liczbaWierzcholkow + 1;
        macierzBellmana = new int[liczbaWierzcholkow + 2];
        bellman = new Bellman(liczbaWierzcholkow + 1);
        djikstra = new Djikstra(liczbaWierzcholkow);
        wYNIK = new int[liczbaWierzcholkow + 1][liczbaWierzcholkow + 1];
    }
 
    public void johnsonsAlgorithms(int macierzWag[][]){
        zwiekszMacierzWag(macierzWag);
        ///////////BELLMAN///////////////
        //boolean czyDodatni = bellman.algorytmBellmana(wezelJohnsona, powiekszonaMacierzWag);
        bellman.algorytmBellmana(wezelJohnsona, powiekszonaMacierzWag);
        macierzBellmana = bellman.getOdleglosc();
        for (int i = 1;i<macierzBellmana.length;i++)
        	System.out.print(macierzBellmana[i]+"\t");
        System.out.println();
 
        int zwazonyGraf[][] = wazenieGrafu(macierzWag);
        ////////////DJIKSTRA/////////////////biere kazdy wierzcholek jako wezel////////////////
        for (int i = 1; i <= liczbaWierzcholkow; i++){
            djikstra.algorytmDjikstry(i, zwazonyGraf);
            int[] result = djikstra.getOdleglosc();
            for (int destination = 1; destination <= liczbaWierzcholkow; destination++){
                wYNIK[i][destination] = result[destination] + macierzBellmana[destination] - macierzBellmana[i];
            	//wYNIK[i][destination] = result[destination];
            }
        }
        
        
        
        /*for (int i = 0; i<6;i++){
        	Bellman hue = new Bellman(liczbaWierzcholkow);
        	hue.algorytmBellmana(i, powiekszonaMacierzWag);
        	int [] lol = hue.getOdleglosc();
        	for (int j = 1; j<lol.length;j++){
        		System.out.print(lol[i]+"\t");
        	}
        }*/
 
        //if (czyDodatni == true) {
			System.out.println();
			for (int i = 0; i <= liczbaWierzcholkow-1; i++) {
				System.out.print("\t" + i);
			}
			System.out.println();
			for (int source = 1; source <= liczbaWierzcholkow; source++) {
				System.out.print(source-1 + "\t");
				for (int destination = 1; destination <= liczbaWierzcholkow; destination++) {
					System.out.print(wYNIK[source][destination]+ "\t");
				}
				System.out.println();
			}
			//return wYNIK;
		//} 
        //else return null;
    }
 
    private void zwiekszMacierzWag(int macierzWag[][]){
        for (int i = 1; i <= liczbaWierzcholkow; i++){		//przepisuje macierze
            for (int j = 1; j <= liczbaWierzcholkow; j++){ 
                powiekszonaMacierzWag[i][j] = macierzWag[i][j];
            }
        }
        for (int i = 1; i <= liczbaWierzcholkow; i++){		//dodaje wezel Johnsona
            powiekszonaMacierzWag[wezelJohnsona][i] = 0;
        }
        //System.out.println("Powiekszonamacieszwag!!!!!!!!!!!");
        for (int i = 0; i<powiekszonaMacierzWag.length;i++){
        	//powiekszonaMacierzWag[i][liczbaWierzcholkow+1] = MAX; //<-----GOWNO!!!!!!!!!!!!!!!!!!!!!!
        	for (int j=0; j<powiekszonaMacierzWag.length;j++){
        		//System.out.print(powiekszonaMacierzWag[i][j]+"\t");
        	}
        	//System.out.println();
        }
    }
    
 
    private int[][] wazenieGrafu(int macierzWag[][]){
        int[][] zwazonyGraf = new int[liczbaWierzcholkow + 1][liczbaWierzcholkow + 1];
        //System.out.println("Zwazony Graf!");
        for (int i = 1; i <= liczbaWierzcholkow; i++){
            for (int j = 1; j <= liczbaWierzcholkow; j++){
                zwazonyGraf[i][j] = macierzWag[i][j] + macierzBellmana[i] - macierzBellmana[j];
                //System.out.print(zwazonyGraf[i][j]+"\t");
            }
            //System.out.println();
        }
        return zwazonyGraf;
    }
}
