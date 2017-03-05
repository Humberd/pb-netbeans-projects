
public class Projektor {
	int name;

	public int getName() {
		return name;
	}


	public void setName(int name) {
		this.name = name;
	}


	public int dodaj( int a, int b ){
		int c = a + b;
		sukces();
		return c;

	}


	public int odejmij( int a, int b ){
		int c = a - b;
		sukces();
		return c;

	}


	private void sukces() {
		System.out.println( "Sukces" );
	}



	public static void main(String[] args) {

		int a = 1, b = 2, c = 3, d = 4, e = 5;
		System.out.println( "Hello World" );


		if( a == 1 )
			if( d == 4 )
				b = 3;
			else
				c = 4;	
	}

}