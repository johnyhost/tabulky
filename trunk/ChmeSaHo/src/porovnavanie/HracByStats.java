package porovnavanie;

import java.util.Comparator;

import zaklad.Hrac;
import zaklad.Liga;

public class HracByStats implements Comparator<Hrac> {
	Liga liga;
	public HracByStats(Liga liga){
		this.liga = liga;
	}
	public int compare(Hrac hrac1, Hrac hrac2) {
		if (hrac1.getGoly()+hrac1.getAsist()>hrac2.getGoly()+hrac2.getAsist()){
			return 1; 
		} else if(hrac1.getGoly()+hrac1.getAsist()==hrac2.getGoly()+hrac2.getAsist()){			
			if(hrac1.getGoly()>hrac2.getGoly()) {
				return 1;
			} else if(hrac1.getOdohratychZapasov()<hrac2.getOdohratychZapasov()) {
					return 1;
			} else return -1;
			
		} else return -1;
	}

}
