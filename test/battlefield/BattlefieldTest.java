package battlefield;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/* Modificare la classe Position affinche' 
 * il primo test abbia successo (vedi DOMANDA 1) 
 */
public class BattlefieldTest {
	
	private Battlefield field;
	
	@Before
	public void setUp() throws Exception {
		this.field = new Battlefield(2);
	}

	@Test
	public void testAddWalker() {
		assertEquals(0, this.field.raggruppaRobotPerTipo().get(Walker.class).size());
		//assertEquals(0, this.field.getAllWalkers().size());
		this.field.addRobot(new Walker(new Position(0,0)));
		assertEquals(1, this.field.raggruppaRobotPerTipo().get(Walker.class).size());
	}
	
	@Test
	public void testRaggruppaRobotDiDueTipiDiversi() {
		this.field.addRobot(new Walker(new Position(0,0)));
		this.field.addRobot(new Chaser(new Position(1,1)));
		assertEquals(1, this.field.raggruppaRobotPerTipo().get(Walker.class).size());
		assertEquals(1, this.field.raggruppaRobotPerTipo().get(Chaser.class).size());
	}

}
