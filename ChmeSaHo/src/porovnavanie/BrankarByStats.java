package porovnavanie;

import java.util.Comparator;

import zaklad.Hrac;
import zaklad.Liga;

// komparator na zoradovanie brankarov
public class BrankarByStats implements Comparator<Hrac> {
	Liga liga;
	public BrankarByStats(Liga liga){
		this.liga = liga;
	}
	public int compare(Hrac brankar1, Hrac brankar2) {
		if (brankar1.getPriemer(liga.getDlzkaZapasu())>brankar2.getPriemer(liga.getDlzkaZapasu())){
			return 1; 
		} else if(brankar1.getPriemer(liga.getDlzkaZapasu())==brankar2.getPriemer(liga.getDlzkaZapasu())){			
			if(brankar1.getOdchytMin()>brankar2.getOdchytMin()) {
				return 1;
			} else return -1;			
		} else return -1;
	}

}
