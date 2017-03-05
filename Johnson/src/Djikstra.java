
public class Djikstra {
	private boolean zaklepane[];
    private boolean niezaklepane[];
    private int odleglosc[];
    private int macierzWag[][];
    private int liczbaWierzcholkow;
 
    public static final int MAX = 999;
 
    public Djikstra(int liczbaWierzcholkow){
        this.liczbaWierzcholkow = liczbaWierzcholkow;
    }
 
    public void algorytmDjikstry(int wezel, int macierzWag[][]){
        this.zaklepane = new boolean[liczbaWierzcholkow + 1];
        this.niezaklepane = new boolean[liczbaWierzcholkow + 1];
        this.odleglosc = new int[liczbaWierzcholkow + 1];
        this.macierzWag = new int[liczbaWierzcholkow + 1][liczbaWierzcholkow + 1];
 
        for (int i=1; i<= liczbaWierzcholkow; i++){
            odleglosc[i] = MAX;
        }
        odleglosc[wezel] = 0;
        niezaklepane[wezel] = true;
 
        for (int i = 1; i <= liczbaWierzcholkow; i++){
            for (int j = 1; j <= liczbaWierzcholkow; j++){
                this.macierzWag[i][j] = macierzWag[i][j];
            }
        }
 
        int temp;
        while (liczNiezaklepane(niezaklepane) != 0){
            temp = najblizszyNajkrotszyWierzcholek(niezaklepane);
            niezaklepane[temp] = false;
            zaklepane[temp] = true;
            evaluateNeighbours(temp);
        }
    } 
 
    public int liczNiezaklepane(boolean niezaklepane[]){
        int count = 0;
        for (int i = 1; i <= liczbaWierzcholkow; i++){
            if (niezaklepane[i] == true)
                count++;
        }
        return count;
    }
 
    public int najblizszyNajkrotszyWierzcholek(boolean niezaklepane[]){
        int min = MAX;
        int node = 0;
        for (int i = 1; i <= liczbaWierzcholkow; i++){
            if (niezaklepane[i] == true && odleglosc[i] < min){
                node = i;
                min = odleglosc[i];
            }
        }
        return node;
    }
 
    public void evaluateNeighbours(int evaluationNode){
        int edgeDistance = -1;
        int newDistance = -1;
 
        for (int i = 1; i <= liczbaWierzcholkow; i++){
            if (zaklepane[i] == false){
                if (macierzWag[evaluationNode][i] != MAX){
                    edgeDistance = macierzWag[evaluationNode][i];
                    newDistance = odleglosc[evaluationNode] + edgeDistance;
                    if (newDistance < odleglosc[i]){
                        odleglosc[i] = newDistance;
                    }
                    niezaklepane[i] = true;
                }
            }
        }
    }
 
    public int[] getOdleglosc(){
        return odleglosc;
    }
}
