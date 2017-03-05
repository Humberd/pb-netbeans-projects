package nothing;

public class testowo {
	public int szukanie(int n){
		if (n==30)
			return 0;
		else{
		if (n<20)
			szukanie(++n);
		else if (n>20)
			szukanie(--n);
		else if (n==20);
			return 2;}
	}
}
