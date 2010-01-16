import java.util.ArrayList;
import java.util.List;


public class Team {
	String nazov;
	int idTeamu;
	List<Hrac> zoznamHracov;
	List<Integer> vysledky; // -1 prehra, 0 remiza, 1 vyhra
	List<Zapas> zoznamZapasov;
	public Team(String nazov, int idTeamu) {
		this.nazov=nazov;
		this.idTeamu=idTeamu;
		this.zoznamHracov=new ArrayList<Hrac>();
		this.vysledky=new ArrayList<Integer>();
		this.zoznamZapasov=new ArrayList<Zapas>();
	}
	public int getBody() {
		int body=0;
		for (int i = 0; i < vysledky.size(); i++) {
			if(vysledky.get(i)==0) {
				body+=1;
			}
			if(vysledky.get(i)==1) {
				body+=3;
			}
		}
		return body;
	}
	public int getStrelGoly() {
		int sucet=0;
		for (Hrac h : zoznamHracov) {
			sucet+=h.getGoly();
		}
		return sucet;
	}
	public int getInkasGoly() {
		int sucet=0;
		for (Hrac h : zoznamHracov) {
			sucet+=h.getInkasGolov();
		}
		return sucet;
	}
}
