package porovnavanie;

import java.util.Comparator;

import zaklad.Hrac;
import zaklad.Liga;

public class BrankarByStats implements Comparator<Hrac> {
	Liga liga;
	public BrankarByStats(Liga liga){
		this.liga = liga;
	}
	public int compare(Hrac brankar1, Hrac brankar2) {
		if (brankar1.getPriemer(45)>brankar2.getPriemer(45)){
			return 1; 
		} else if(brankar1.getPriemer(45)==brankar2.getPriemer(45)){			
			if(brankar1.getOdchytMin()>brankar2.getOdchytMin()) {
				return 1;
			} else return -1;			
		} else return -1;
	}

}
