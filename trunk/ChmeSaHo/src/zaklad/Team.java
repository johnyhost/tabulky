package zaklad;
import java.util.ArrayList;
import java.util.List;


public class Team {
	private int idTeamu;
	private String nazov;
	private List<Hrac> zoznamHracov;
	public Team(String nazov, int idTeamu) {
		this.nazov=nazov;
		this.idTeamu=idTeamu;
		this.zoznamHracov=new ArrayList<Hrac>();
	}
	public Team() {
		this.zoznamHracov=new ArrayList<Hrac>();
	}
	public int getBody(List<Zapas> zapasy) {
		//ziskanie poctu bodov zo zoznamu zapasov
		int body=0;
		for (int i = 0; i < zapasy.size(); i++) {
			if(zapasy.get(i).getIdTeamu1()==idTeamu){
				if(zapasy.get(i).getVysledok()==0) {
					body+=1;
				}
				if(zapasy.get(i).getVysledok()==1) { // ak vyhral team 1
					body+=3;
				}
			}
			if(zapasy.get(i).getIdTeamu2()==idTeamu){
				if(zapasy.get(i).getVysledok()==0) {
					body+=1;
				}
				if(zapasy.get(i).getVysledok()==2) { // ak vyhral team 2
					body+=3;
				}
			}
			
		}
		return body;
	}
	public int getStrelGoly() {
		//ziskanie celkoveho poctu strelenych golov zo zoznamu hracov a spocitania kolko golov brankari dostali
		int sucet=0;
		for (Hrac h : zoznamHracov) {
			sucet+=h.getGoly();
		}
		return sucet;
	}
	public int getInkasGoly() {
		//ziskanie celkoveho poctu inkasovanych golov zo zoznamu hracov a spocitania kolko golov brankari dostali
		int sucet=0;
		for (Hrac h : zoznamHracov) {
			sucet+=h.getInkasGoly();
		}
		return sucet;
	}
	public String getNazov() {
		return nazov;
	}
	public void setNazov(String nazov) {
		this.nazov = nazov;
	}
	public int getIdTeamu() {
		return idTeamu;
	}
	public void setIdTeamu(int idTeamu) {
		this.idTeamu = idTeamu;
	}
	public List<Hrac> getZoznamHracov() {
		return zoznamHracov;
	}
	public int getPocetVyhier(List<Zapas> zapasy) {
		//ziskanie poctu vyhier teamu zo zoznamu zapasov
		int pocetVyhier = 0;
		for (Zapas z : zapasy) {
			if(z.getIdTeamu1()==idTeamu){
				if(z.getVysledok()==1) { // ak vyhral team 1
					pocetVyhier++;
				}
			}
			if(z.getIdTeamu2()==idTeamu){
				if(z.getVysledok()==2) { // ak vyhral team 2
					pocetVyhier++;
				}
			}
		}
		return pocetVyhier;
	}
	public int getPocetRemiz(List<Zapas> zapasy) {
		//ziskanie poctu remiz teamu zo zoznamu zapasov
		int pocetRemiz = 0;
		for (Zapas z : zapasy) {
			if(z.getVysledok()==0){
				pocetRemiz++;
			}
		}
		return pocetRemiz;
	}
	public int getPocetPrehier(List<Zapas> zapasy) {
		//ziskanie poctu prehier teamu zo zoznamu zapasov
		int pocetPrehier = 0;
		for (Zapas z : zapasy) {
			if(z.getIdTeamu1()==idTeamu){
				if(z.getVysledok()==2) { // ak vyhral team 1
					pocetPrehier++;
				}
			}
			if(z.getIdTeamu2()==idTeamu){
				if(z.getVysledok()==1) { // ak vyhral team 2
					pocetPrehier++;
				}
			}
		}
		return pocetPrehier;
	}
	public int getSkore(int idZapasu){
		//ziskanie poctu golov strelenych v konkretnom zapase
		int skore=0;
		for(int i=0;i<zoznamHracov.size();i++){
			if(zoznamHracov.get(i).existujeZapas(idZapasu)){
				skore+=zoznamHracov.get(i).getGoly(idZapasu);
			}
		}
		return skore;
	}
}
