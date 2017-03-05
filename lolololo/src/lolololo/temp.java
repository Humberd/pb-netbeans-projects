package lolololo;

public class temp {
	private static class dupa {
		static int counter = 0;
		public int hi = 0;
		
		public dupa() {
			counter++;
		}
		public void setHi(int i) {
			hi = i;
		}
	}
	public temp (){
		dupa a = new dupa();
		System.out.println(a.hi);
		
		dupa b = new dupa();
		b.setHi(3);
		System.out.println(a.hi+ " " + b.hi);
	}
}
