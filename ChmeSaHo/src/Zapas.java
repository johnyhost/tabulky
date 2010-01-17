
public class Zapas {
	private int idZapasu;
	private int idTeamu1;
	private int idTeamu2;
	private int vysledok; // 0 remiza, 1 vyhral idTeam1, 2 vyhral idTeam2
	public Zapas(int idZapasu, int idTeam1, int idTeam2) {
		this.idZapasu = idZapasu;
		this.idTeamu1 = idTeam1;
		this.idTeamu2 = idTeam2;
	}
	public Zapas(){
		
	}
	public int getIdZapasu() {
		return idZapasu;
	}
	public void setIdZapasu(int idZapasu) {
		this.idZapasu = idZapasu;
	}
	public int getIdTeamu1() {
		return idTeamu1;
	}
	public void setIdTeamu1(int idTeamu1) {
		this.idTeamu1 = idTeamu1;
	}
	public int getIdTeamu2() {
		return idTeamu2;
	}
	public void setIdTeamu2(int idTeamu2) {
		this.idTeamu2 = idTeamu2;
	}
	public int getVysledok() {
		return vysledok;
	}
	public void setVysledok(int vysledok) {
		this.vysledok = vysledok;
	}
	
}
