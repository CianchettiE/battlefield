package battlefield;

public abstract class Robot implements Comparable<Robot> {
	private Position posizione;
	private int longevita;
	
	public Robot(Position p) {
		this.posizione = p;
		this.longevita = 0 ;
	}
	
	public Position getPosizione() {
		return this.posizione;
	}
	
	public int incrementaLongevita() {
		return ++this.longevita;
	}
	
	public int getLongevita() {
		return this.longevita;
	}
	
	public abstract void passo(Battlefield field);
	
	@Override
	public boolean equals(Object o) {
		if(o==null||this.getClass()!=o.getClass())return false;
		Robot that=(Robot)o;
		return this.getPosizione().equals(that.getPosizione())&&this.getLongevita()==that.getLongevita();
	}
	
	@Override
	public int compareTo(Robot that) {
		return this.getPosizione().compareTo(that.getPosizione());
	}
	
}
