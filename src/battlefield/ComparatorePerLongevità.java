package battlefield;

import java.util.Comparator;

public class ComparatorePerLongevità implements Comparator<Robot>{

	@Override
	public int compare(Robot r1, Robot r2) {
		int cmp=r1.getLongevita()-r2.getLongevita();
		if(cmp==0) {
			cmp=r1.getPosizione().hashCode()-r2.getPosizione().hashCode();
		}
		return cmp;
	}

}
