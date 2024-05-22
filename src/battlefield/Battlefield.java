package battlefield;

import java.util.*;


public class Battlefield {

	static final private Class<?>[] TIPOLOGIE = { Walker.class, Chaser.class } ;

	static final private int NUMERO_TIPOLOGIE = TIPOLOGIE.length;

	static final private double OCCUPAZIONE_INIZIALE = 0.01d; // 1%

	private int dim;

	private Random random;

	//private Map<Position, Walker> posizione2walker;
	//private Map<Position, Chaser> posizione2chaser;
	private Map<Position, Robot> posizioneRobot;

	public Battlefield(int dimensione) {
		this.dim = dimensione;
		//this.posizione2walker = new HashMap<>();
		//this.posizione2chaser = new HashMap<>();
		this.posizioneRobot=new HashMap<>();
		this.random = new Random();
	}

	/*public void addWalker(Walker w) {
		this.posizione2walker.put(w.getPosizione(), w);
	}

	public void addChaser(Chaser c) {
		this.posizione2chaser.put(c.getPosizione(), c);
	}*/

	public void addRobot(Robot r) {
		this.posizioneRobot.put(r.getPosizione(), r);
	}

	public Robot getWalker(Position p) {
		Robot r=posizioneRobot.get(p);
		if(r!=null&&r.getClass()==Walker.class) {
			return r;
		}
		else return null;
	}

	public Robot getChaser(Position p) {
		Robot r=posizioneRobot.get(p);
		if(r!=null&&r.getClass()==Chaser.class) {
			return r;
		}
		else return null;
	}

	public Robot getRobot(Position p) {
		return posizioneRobot.get(p);
	}

	public Collection<Robot> getAllWalkers() {
		List<Robot> collection=new ArrayList<>();
		for(Map.Entry<Position, Robot> entry:posizioneRobot.entrySet()) {
			if(entry.getValue().getClass()==Walker.class) {
				collection.add(entry.getValue());
			}
		}
		return collection;
	}

	public Collection<Robot> getAllChasers() {
		List<Robot> collection=new ArrayList<>();
		for(Map.Entry<Position, Robot> entry:posizioneRobot.entrySet()) {
			if(entry.getValue().getClass()==Chaser.class) {
				collection.add(entry.getValue());
			}
		}
		return collection;
	}

	public Collection<Robot> getAllRobots() {
		return this.posizioneRobot.values();
	}

	@SuppressWarnings("rawtypes")
	public Map<Class, Set<Robot>> raggruppaRobotPerTipo() {
		Map<Class,Set<Robot>> map=new HashMap<>();
		for(Robot r:posizioneRobot.values()) {
			if(!map.containsKey(r.getClass())) {
				map.put(r.getClass(),new HashSet<>());
			}
			map.get(r.getClass()).add(r);
		}
		return map;
	}

	public List<Robot> getRobotOrdinatiPerPosizione() {
		List<Robot> list=new ArrayList<>(posizioneRobot.values());
		ComparatorePerPosizione cmp=new ComparatorePerPosizione();
		Collections.sort(list,cmp);		
		return list;
	}

	public SortedSet<Robot> getRobotOrdinatiPerLongevita() {
		SortedSet<Robot> set=new TreeSet<>();
		set.addAll(posizioneRobot.values());
		return set;
	}

	public List<Position> adiacenti(Position perno) {
		final List<Position> adiacenti = new LinkedList<>();
		int x = perno.getX();
		int y = perno.getY();

		for(int i = -1; i<2; i++) {
			for(int j = -1; j<2; j++) {
				Position adiacente = new Position(x+i, y+j);
				if (inCampo(adiacente))
					adiacenti.add(adiacente);
			}
		}
		adiacenti.remove(perno);  // coincidono: quindi non sono adiacenti

		Collections.shuffle(adiacenti); /* ordine casuale */
		return adiacenti;
	}

	private boolean inCampo(Position p) {
		return  p.getX()>=0 && p.getX()<this.dim && 
				p.getY()>=0 && p.getY()<this.dim  ;

	} 

	public Position posizioneLiberaVicino(Position posizione) {
		for(Position p : this.adiacenti(posizione)) {
			if (this.isLibera(p)) {
				return p;
			}
		}
		return null;
	}

	public boolean isLibera(Position posizione) {
		return ( this.getRobot(posizione)==null);
	}

	public int getDimensione() {
		return this.dim;
	}

	public void riempi() {
		long numeroIniziale = Math.round(OCCUPAZIONE_INIZIALE * dim * dim);
		for(int i=0 ; i<numeroIniziale; i++) {
			int x = this.random.nextInt(this.dim);
			int y = this.random.nextInt(this.dim);
			Position posizione = new Position(x, y);
			if (this.isLibera(posizione)) {
				switch (this.random.nextInt(NUMERO_TIPOLOGIE)) {
				case 0: Chaser chaser = new Chaser(posizione);
				this.addRobot(chaser);
				break;
				case 1: Walker walker = new Walker(posizione);
				this.addRobot(walker);
				break;
				//case: NUMERO_TIPOLOGIE-1...
				default: throw new IllegalStateException();
				} 
			}
		}
	}

}
