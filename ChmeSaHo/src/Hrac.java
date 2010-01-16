import java.util.ArrayList;
import java.util.List;


public class Hrac {
	int idHraca;
	String meno;
	List<Boolean> zapasy;
	List<Integer> goly;
	List<Integer> asist;
	List<Integer> trestmin;
	List<Integer> odchytMin;
	List<Integer> inkasGoly;
	public Hrac(int idHraca, String meno) {
		this.idHraca=idHraca;
		this.meno=meno;
		this.zapasy=new ArrayList<Boolean>();
		this.goly=new ArrayList<Integer>();
		this.asist=new ArrayList<Integer>();
		this.trestmin=new ArrayList<Integer>();
		this.odchytMin=new ArrayList<Integer>();
		this.inkasGoly=new ArrayList<Integer>();		
	}
	public boolean jeBrankar() {
		int sucet=0;
		for (int i = 0; i < odchytMin.size(); i++) {
			sucet+=odchytMin.get(i);
		}
		if(sucet>0) return true; else return false;
	}
	public int getZapasy() {
		int sucet=0;
		for (int i = 0; i < zapasy.size(); i++) {
			if(zapasy.get(i)) sucet++;
		}
		return sucet;
	}
	public int getGoly() {
		int sucet=0;
		for (int i = 0; i < goly.size(); i++) {
			sucet+=goly.get(i);
		}
		return sucet;
	}
	public int getAsist() {
		int sucet=0;
		for (int i = 0; i < asist.size(); i++) {
			sucet+=asist.get(i);
		}
		return sucet;
	}
	public int getTrestMin() {
		int sucet=0;
		for (int i = 0; i < trestmin.size(); i++) {
			sucet+=trestmin.get(i);
		}
		return sucet;
	}
	public int getInkasGolov() {
		//celkovy pocet inkasovanych golov
		int sucet=0;
		for (int i = 0; i < inkasGoly.size(); i++) {
			sucet+=inkasGoly.get(i);
		}
		return sucet;		
	}
	public int getOdchytMin() {
		//celkovy pocet odchytanych minut
		int sucet=0;
		for (int i = 0; i < odchytMin.size(); i++) {
			sucet+=odchytMin.get(i);
		}
		return sucet;		
	}
	public double getPriemer(int dlzkaZapasu) {
		//priemer golu na zapas
		return getInkasGolov()/(getOdchytMin()/dlzkaZapasu);
	}
	
	public void addZapas(boolean z) {
		zapasy.add(z);
	}
	public void addGoly(int g) {
		goly.add(g);
	}
	public void addAsist(int asists) {
		asist.add(asists);
	}
	public void addTM(int tm) {
		trestmin.add(tm);
	}
	public void addOdchMin(int om) {
		odchytMin.add(om);
	}
	public void addInkGol(int ig) {
		inkasGoly.add(ig);
	}
	
	
}
