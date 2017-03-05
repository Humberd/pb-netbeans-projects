
public class ZlaSciezkaException extends DontStarveException{
	private String sciezka;
	public ZlaSciezkaException(String msg, String sciezka){
		super(msg);
		this.sciezka = sciezka;
	}
	public String getSciezka(){
		return sciezka;
	}
}
