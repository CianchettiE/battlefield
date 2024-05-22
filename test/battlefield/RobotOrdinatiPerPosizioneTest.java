package battlefield;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.*;

public class RobotOrdinatiPerPosizioneTest {
	
	// (vedi DOMANDA 5)
	// scrivere dei test-case minimali per il metodo
	// Battlefield.getRobotOrdinatiPerPosizione()
    private Battlefield field;
	
	private Position origine;
	private Position unitari;
	
	@Before
	public void setUp() throws Exception {
		this.field = new Battlefield(10);
		this.origine = new Position(0, 0);
		this.unitari = new Position(1, 1);
	}
	
	@Test
	public void testCampoVuoto() {
		List<Robot> listaTest=field.getRobotOrdinatiPerPosizione();
		assertEquals(0,listaTest.size());
	}
	
	@Test
	public void testCampoRobotSingolo() {
		this.field.addRobot(new Walker(unitari)); 
		List<Robot> listaTest=field.getRobotOrdinatiPerPosizione();
		assertEquals(1,listaTest.size());
		Iterator<Robot> iter=listaTest.iterator();
		assertTrue(iter.hasNext());
		assertEquals(new Walker(unitari),iter.next());
	}
	
	@Test
	public void test2RobotXDiversa() {
		this.field.addRobot(new Walker(unitari)); // n.b. longevità 0;
		this.field.addRobot(new Walker(origine)); // n.b. longevità 0;
		assertEquals(2, this.field.getRobotOrdinatiPerLongevita().size());
		List<Robot> listaTest=field.getRobotOrdinatiPerPosizione();
		Iterator<Robot> iter=listaTest.iterator();
		assertTrue(iter.hasNext());
		assertEquals(new Position(0,0),iter.next().getPosizione());
		assertTrue(iter.hasNext());
		assertEquals(new Position(1,1),iter.next().getPosizione());
	}
	
	@Test
	public void test2RobotXUguale() {
		this.field.addRobot(new Walker(new Position(1,5))); // n.b. longevità 0;
		this.field.addRobot(new Walker(new Position(1,2))); // n.b. longevità 0;
		assertEquals(2, this.field.getRobotOrdinatiPerLongevita().size());
		List<Robot> listaTest=field.getRobotOrdinatiPerPosizione();
		Iterator<Robot> iter=listaTest.iterator();
		assertTrue(iter.hasNext());
		assertEquals(new Walker(new Position(1,2)),iter.next());
		assertTrue(iter.hasNext());
		assertEquals(new Position(1,5),iter.next().getPosizione());
	}
	
	@Test
	public void test4Robot() {
		this.field.addRobot(new Walker(new Position(1,5))); // n.b. longevità 0;
		this.field.addRobot(new Walker(new Position(1,2))); // n.b. longevità 0;
		this.field.addRobot(new Walker(new Position(7,1))); // n.b. longevità 0;
		this.field.addRobot(new Walker(new Position(3,4))); // n.b. longevità 0;
		this.field.addRobot(new Walker(new Position(1,5))); // n.b. longevità 0;
		assertEquals(4, this.field.getRobotOrdinatiPerLongevita().size());
		List<Robot> listaTest=field.getRobotOrdinatiPerPosizione();
		Iterator<Robot> iter=listaTest.iterator();
		assertTrue(iter.hasNext());
		assertEquals(new Position(1,2),iter.next().getPosizione());
		assertTrue(iter.hasNext());
		assertEquals(new Walker(new Position(1,5)),iter.next());
		assertTrue(iter.hasNext());
		assertEquals(new Position(3,4),iter.next().getPosizione());
		assertTrue(iter.hasNext());
		assertEquals(new Position(7,1),iter.next().getPosizione());
	}
}

