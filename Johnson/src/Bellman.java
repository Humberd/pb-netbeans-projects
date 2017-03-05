
public class Bellman {
	private int odleglosc[];
    private int liczbaWierzcholkow;
 
    public static final int MAX = 999;
 
    public Bellman(int liczbaWierzcholkow)  {
        this.liczbaWierzcholkow = liczbaWierzcholkow;
        odleglosc = new int[liczbaWierzcholkow + 1];
    }
 
    public void algorytmBellmana(int wezel, int macierzWag[][]){
    	int[] mojaOdleglosc = new int[odleglosc.length];
        for (int i = 1; i <= liczbaWierzcholkow; i++){
        	mojaOdleglosc[i] = MAX;
        }
        mojaOdleglosc[wezel] = 0;
        //for (int i=0;i<macierzWag.length;i++){
        //	for (int j=0)
        //}
 
        for (int i = 1; i<=liczbaWierzcholkow-1; i++){				//iteracji o 1 mniej niz jest wierzcholkow
            for (int tempSource = 1; tempSource <= liczbaWierzcholkow; tempSource++){	//ustawia kazdy po kolei jako source
                for (int kandydat = 1; kandydat <= liczbaWierzcholkow; kandydat++){	//porownoje kazdy wierzcholek z source
                    if (macierzWag[tempSource][kandydat] != MAX){		//sprawdza czy ma polaczenie z source
                        if (mojaOdleglosc[kandydat] > mojaOdleglosc[tempSource] + macierzWag[tempSource][kandydat]){
                        	mojaOdleglosc[kandydat] = mojaOdleglosc[tempSource]+ macierzWag[tempSource][kandydat];
                        }
                    }
                }
            }
        }
        /*System.out.println("odleglosc");
        for (int i = 1; i<mojaOdleglosc.length; i++)
        	System.out.print(mojaOdleglosc[i]+ "\t");*/
        System.out.println();
        //to samo co wyzej, jak sie cos zmieni to WIEDZ ZE COS SIE DZIEJE!!!
        for (int tempSource = 1; tempSource <= liczbaWierzcholkow; tempSource++){
            for (int kandydat = 1; kandydat <= liczbaWierzcholkow; kandydat++){
                if (macierzWag[tempSource][kandydat] != MAX){
                    if (mojaOdleglosc[kandydat] > mojaOdleglosc[tempSource] + macierzWag[tempSource][kandydat]){
                        System.out.println("Graf zawiera cykl o ujemnej wadze!");
                        //return false;
                    }
                }
            }
        }
        this.odleglosc = mojaOdleglosc;
        //return true;
    }
 
    public int[] getOdleglosc(){
        return odleglosc;
    }
}
