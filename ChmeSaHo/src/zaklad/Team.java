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
		int sucet=0;
		for (Hrac h : zoznamHracov) {
			sucet+=h.getGoly();
		}
		return sucet;
	}
	public int getInkasGoly() {
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
	
}
