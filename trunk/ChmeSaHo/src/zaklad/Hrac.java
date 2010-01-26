package zaklad;
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
	private List<Integer> idZapasov; // pouzite na prevod zapasu z ligy pre konkretneho hraca
	public Hrac(int idHraca, String meno) {
		this.idHraca=idHraca;
		this.meno=meno;
		this.hralZapas=new ArrayList<Boolean>();
		this.goly=new ArrayList<Integer>();
		this.asist=new ArrayList<Integer>();
		this.trestMin=new ArrayList<Integer>();
		this.odchytMin=new ArrayList<Integer>();
		this.inkasGoly=new ArrayList<Integer>();	
		this.idZapasov=new ArrayList<Integer>();	
	}
	public Hrac() {
		this.hralZapas=new ArrayList<Boolean>();
		this.goly=new ArrayList<Integer>();
		this.asist=new ArrayList<Integer>();
		this.trestMin=new ArrayList<Integer>();
		this.odchytMin=new ArrayList<Integer>();
		this.inkasGoly=new ArrayList<Integer>();
		this.idZapasov=new ArrayList<Integer>();	
	}
	private int prevodIdZapasu(int idZapasu){
		for(int i=0;i< idZapasov.size();i++){
			if(idZapasu==idZapasov.get(i)) return i;
		}
		System.err.println("CHYBA: Hraca sa netyka zapas s takymto ligovym ID:"+idZapasu);
		return 0;
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
	public int getInkasGoly() {
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
		return getInkasGoly()/(getOdchytMin()/dlzkaZapasu);
	}
	public void nehralZapas(int idZapasu){
		idZapasov.add(idZapasu);
		hralZapas.add(prevodIdZapasu(idZapasu),false);
		goly.add(prevodIdZapasu(idZapasu),0);
		asist.add(prevodIdZapasu(idZapasu),0);
		trestMin.add(prevodIdZapasu(idZapasu),0);
		odchytMin.add(prevodIdZapasu(idZapasu),0);
	}
	public void addZapas(int idZapasu, int Goly, int Asist, int TrestMin, int OdchytMin, int InkGol) {
		idZapasov.add(idZapasu);
		hralZapas.add(prevodIdZapasu(idZapasu),true);
		goly.add(prevodIdZapasu(idZapasu),Goly);
		asist.add(prevodIdZapasu(idZapasu),Asist);
		trestMin.add(prevodIdZapasu(idZapasu),TrestMin);
		odchytMin.add(prevodIdZapasu(idZapasu),OdchytMin);
	}
	public boolean getHralZapas(int idZapasu) {
		return hralZapas.get(prevodIdZapasu(idZapasu));
	}
	public int getGoly(int idZapasu) {
		return goly.get(prevodIdZapasu(idZapasu));
	}
	public int getAsist(int idZapasu) {
		return asist.get(prevodIdZapasu(idZapasu));
	}
	public int getTrestMin(int idZapasu) {
		return trestMin.get(prevodIdZapasu(idZapasu));
	}
	public int getOdchytMin(int idZapasu) {
		return odchytMin.get(prevodIdZapasu(idZapasu));
	}
	public int getInkasGoly(int idZapasu) {
		return inkasGoly.get(prevodIdZapasu(idZapasu));
	}
	public String getMeno() {
		return this.meno;
	}
	public void setMeno(String noveMeno) {
		this.meno=noveMeno;
	}	
	public int getIdHraca() {
		return idHraca;
	}
	public void setIdHraca(int idHraca) {
		this.idHraca = idHraca;
	}
	public void setHralZapas(int idZapasu,boolean hral) {
		hralZapas.set(prevodIdZapasu(idZapasu),hral);
	}
	public void setGoly(int idZapasu, int cislo) {
		goly.set(prevodIdZapasu(idZapasu),cislo);
	}
	public void setAsist(int idZapasu, int cislo) {
		asist.set(prevodIdZapasu(idZapasu),cislo);
	}
	public void setTrestMin(int idZapasu, int cislo) {
		trestMin.set(prevodIdZapasu(idZapasu),cislo);
	}
	public void setOdchytMin(int idZapasu, int cislo) {
		odchytMin.set(prevodIdZapasu(idZapasu),cislo);
	}
	public void setInkasGoly(int idZapasu, int cislo) {
		inkasGoly.set(prevodIdZapasu(idZapasu),cislo);
	}
	
	// metody dolezite pre triedu Data, konkretne nacitavanie
	public void addHralZapas(int idZapasu,boolean hral) {
		idZapasov.add(idZapasu);		
		hralZapas.add(prevodIdZapasu(idZapasu),hral);
	}
	public void addGoly(int cislo) {
		goly.add(cislo);
	}
	public void addAsist(int cislo) {
		asist.add(cislo);
	}
	public void addTrestMin(int cislo) {
		trestMin.add(cislo);
	}
	public void addOdchytMin(int cislo) {
		odchytMin.add(cislo);
	}
	public void addInkasGoly(int cislo) {
		inkasGoly.add(cislo);
	}
	public List<Integer> getIdZapasov() {
		return idZapasov;
	}
	
		
}
