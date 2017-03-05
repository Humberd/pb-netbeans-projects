import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;



public class Folder {
	private String sciezkaZrodlowa;
	private String sciezkaDocelowa;
	private String szukana;
	private Thread thread;
	private ArrayList listaPlikow = new ArrayList();
	
	public Folder(String sciezkaZrodlowa, String sciezkaDocelowa){
		this.sciezkaZrodlowa = sciezkaZrodlowa;
		this.sciezkaDocelowa = sciezkaDocelowa;
	}
	public void wlacz () throws IOException{
		thread = new Thread(new Runnable(){
			public void run(){
				try {
					int i = 1;
					while (!Thread.currentThread().isInterrupted()){		/// TU WRZUCAM CO MA SIE DZIAC CO JAKIS OKRES (CZASU) :D
						kopiowanieFolderow("dupa"+i);
						System.out.println("dupa"+i);
						Thread.sleep(5000);
						i++;
					}
					
				} catch (InterruptedException e){
					Thread.currentThread().interrupt();
				}
			}
		});
		thread.start();
        

	}
	public void wylacz (){
		thread.interrupt();
	}
	///////////////////////////////////////////////
	public void kopiowanieFolderow(String nazwa){
		File zrodlowy = new File(sciezkaZrodlowa);
		File docelowy = new File(sciezkaDocelowa+"\\"+nazwa);
		docelowy.mkdir();
		
		try {
			FileUtils.copyDirectory(zrodlowy,docelowy);
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////
	public String szukaj(String nazwaPliku) throws ZlaSciezkaException{
		this.szukana = nazwaPliku;
		return szukajPliku(sciezkaZrodlowa);
	}
	private String szukajPliku(String sciezka) throws ZlaSciezkaException{
		File[] pulaWyszukan = getListaSciezek(sciezka);
		for (File s: pulaWyszukan){
			System.out.println(s.getName()+ "  "+szukana);
			if (s.getName().equals(szukana))
				return s.getPath();
			else if (s.isDirectory())
				if (szukajPliku(s.getPath()) != null)
					return szukajPliku(s.getPath());
		}
		return null;
	}
	////////////////////////////////////////////////
	public void showListaPlikowSciezkiZrodlowej(){
		try {
			System.out.println(getListaPlikowSciezkiZrodlowej());
		} catch (ZlaSciezkaException zse){
			System.out.println("Wprowadzona sciezka: ["+ zse.getSciezka()+"] nie istnieje!");
		}
	}
	public void showListaPlikowSciezkiDocelowej(){
		try {
			System.out.println(getListaPlikowSciezkiDocelowej());
		} catch (ZlaSciezkaException zse){
			System.out.println("Wprowadzona sciezka: ["+ zse.getSciezka()+"] nie istnieje!");
		}
	}
	
	public ArrayList<String> getListaPlikowSciezkiZrodlowej() throws ZlaSciezkaException{
		listaPlikow.clear();
		return getListaPlikow(getListaSciezek(sciezkaZrodlowa),listaPlikow);
	}
	public ArrayList<String> getListaPlikowSciezkiDocelowej() throws ZlaSciezkaException{
		listaPlikow.clear();
		return getListaPlikow(getListaSciezek(sciezkaDocelowa), listaPlikow);
	}
	//////////////////
	private File[] getListaSciezek(String sciezka) throws ZlaSciezkaException{
		if (new File(sciezka).exists()){
			File folder = new File(sciezka);
			return folder.listFiles();
		}
		throw new ZlaSciezkaException("Lol",sciezka);
	}
	private ArrayList<String> getListaPlikow(File[] listaSciezek, ArrayList<String> list) throws ZlaSciezkaException{
		for (File s: listaSciezek){
			list.add(s.getName());
			if (s.isDirectory()){
				ArrayList<String> lista = new ArrayList<String>();
				getListaPlikow(getListaSciezek(s.getPath()),lista);
				list.addAll(lista);
			}
		}
		return list;
	}
	
	//////////////////
	public String getSciezkaZrodlowa(){
		return sciezkaZrodlowa;
	}
	public String getSciezkaDocelowa(){
		return sciezkaDocelowa;
	}
	public void setSciezkaZrodlowa(String sciezkaZrodlowa){
		this.sciezkaZrodlowa = sciezkaZrodlowa;
	}
	public void setSciezkaDocelowa(String sciezkaDocelowa){
		this.sciezkaDocelowa = sciezkaDocelowa;
	}
}
