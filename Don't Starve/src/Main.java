import java.io.IOException;


public class Main {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws ZlaSciezkaException, IOException {
		String sciezkaZrodlowa = "F:\\Steam\\userdata";
		String sciezkaDocelowa = "D:\\Programy\\GoogleDrive";
		String[] nazwy = {"userdata","219740","remote"};
		Folder folder = new Folder(sciezkaZrodlowa,sciezkaDocelowa);
		
		String zmienna = null;
		for (String s: nazwy){
			zmienna= folder.szukaj(s);
			System.out.println(zmienna);
			if (zmienna!=null)
				folder.setSciezkaZrodlowa(zmienna);
		}
		//folder.kopiowanieFolderow("dupa");
        folder.wlacz();
        System.in.read(); ////////    WYLACZANIE POPRZEZ NAPISANIE CZEGOKOLWIEK
        folder.wylacz();
}
}