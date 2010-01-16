import java.util.ArrayList;
import java.util.List;


public class Hrac {
	private int idHraca;
	private String meno;
	private List<Boolean> hralZapas;
	private List<Integer> goly;
	private List<Integer> asist;
	private List<Integer> trestMin;
	private List<Integer> odchytMin;
	private List<Integer> inkasGoly;
	public Hrac(int idHraca, String meno) {
		this.idHraca=idHraca;
		this.meno=meno;
		this.hralZapas=new ArrayList<Boolean>();
		this.goly=new ArrayList<Integer>();
		this.asist=new ArrayList<Integer>();
		this.trestMin=new ArrayList<Integer>();
		this.odchytMin=new ArrayList<Integer>();
		this.inkasGoly=new ArrayList<Integer>();		
	}
	public boolean jeBrankar() {
		int sucet=0;
		for (int odMin: odchytMin) {
			sucet+=odMin;
		}
		if(sucet>0) return true; else return false;
	}
	public int pocetOdohratychZapasov() {
		int sucet=0;
		for (boolean hral: hralZapas) {
			if(hral) sucet++;
		}
		return sucet;
	}
	public int getGoly() {
		int sucet=0;
		for (int i: goly) {
			sucet+=i;
		}
		return sucet;
	}
	public int getAsist() {
		int sucet=0;
		for (int i: asist) {
			sucet+=i;
		}
		return sucet;
	}
	public int getTrestMin() {
		int sucet=0;
		for (int i: trestMin ) {
			sucet+=i;
		}
		return sucet;
	}
	public int getInkasGolov() {
		//celkovy pocet inkasovanych golov
		int sucet=0;
		for (int i: inkasGoly) {
			sucet+=i;
		}
		return sucet;		
	}
	public int getOdchytMin() {
		//celkovy pocet odchytanych minut
		int sucet=0;
		for (int i: odchytMin) {
			sucet+=i;
		}
		return sucet;		
	}
	public double getPriemer(int dlzkaZapasu) {
		//priemer golu na zapas
		return getInkasGolov()/(getOdchytMin()/dlzkaZapasu);
	}
	public void nehralZapas(int idZapasu){
		hralZapas.add(idZapasu,false);
		goly.add(idZapasu,0);
		asist.add(idZapasu,0);
		trestMin.add(idZapasu,0);
		odchytMin.add(idZapasu,0);
	}
	public void addZapas(int idZapasu, int Goly, int Asist, int TrestMin, int OdchytMin, int InkGol) {
		hralZapas.add(idZapasu,true);
		goly.add(idZapasu,Goly);
		asist.add(idZapasu,Asist);
		trestMin.add(idZapasu,TrestMin);
		odchytMin.add(idZapasu,OdchytMin);
	}
	public boolean getHralZapas(int idZapasu) {
		return hralZapas.get(idZapasu);
	}
	public int getGoly(int idZapasu) {
		return goly.get(idZapasu);
	}
	public int getAsist(int idZapasu) {
		return asist.get(idZapasu);
	}
	public int getTrestMin(int idZapasu) {
		return trestMin.get(idZapasu);
	}
	public int getOdchytMin(int idZapasu) {
		return odchytMin.get(idZapasu);
	}
	public int getInkGol(int idZapasu) {
		return inkasGoly.get(idZapasu);
	}
	public String getMeno() {
		return this.meno;
	}
	public void setMeno(String noveMeno) {
		this.meno=noveMeno;
	}
	public void setHralZapas(int idZapasu,boolean hral) {
		hralZapas.set(idZapasu,hral);
	}
	public void setGoly(int idZapasu, int cislo) {
		goly.set(idZapasu,cislo);
	}
	public void setAsist(int idZapasu, int cislo) {
		asist.set(idZapasu,cislo);
	}
	public void setTrestMin(int idZapasu, int cislo) {
		trestMin.set(idZapasu,cislo);
	}
	public void setOdchytMin(int idZapasu, int cislo) {
		odchytMin.set(idZapasu,cislo);
	}
	public void setInkGol(int idZapasu, int cislo) {
		inkasGoly.set(idZapasu,cislo);
	}
		
}
