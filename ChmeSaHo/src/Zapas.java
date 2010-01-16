
public class Zapas {
	private int idZapasu;
	private int idTeam1;
	private int idTeam2;
	private int vysledok; // 0 remiza, 1 vyhral idTeam1, 2 vyhral idTeam2
	public Zapas(int idZapasu, int idTeam1, int idTeam2) {
		this.idZapasu = idZapasu;
		this.idTeam1 = idTeam1;
		this.idTeam2 = idTeam2;
	}
	public int getIdZapasu() {
		return idZapasu;
	}
	public void setIdZapasu(int idZapasu) {
		this.idZapasu = idZapasu;
	}
	public int getIdTeam1() {
		return idTeam1;
	}
	public void setIdTeam1(int idTeam1) {
		this.idTeam1 = idTeam1;
	}
	public int getIdTeam2() {
		return idTeam2;
	}
	public void setIdTeam2(int idTeam2) {
		this.idTeam2 = idTeam2;
	}
	public int getVysledok() {
		return vysledok;
	}
	public void setVysledok(int vysledok) {
		this.vysledok = vysledok;
	}
	
}
