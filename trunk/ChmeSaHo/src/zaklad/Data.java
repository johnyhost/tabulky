package zaklad;
public class Data {
	Liga liga;
	public Data(Liga liga){
		this.liga=liga;
	}
	public Data(){
		this.liga= new Liga();
	}
	public Liga loadXML(String filename){
		XMLLoader loader = new XMLLoader(filename);
		liga=loader.result();
		return liga;
		
	}
	public void saveXML(String filename){
		XMLSaver saver = new XMLSaver(filename,liga);
		
		
	}
	public void exportHraciHTML(String filename){
		// TODO metoda pre export hracov do HTML, treba teda vytvorit subor filename
		// je zabezpecene aby subor obsahoval .htm resp. html, o to sa tu teda netreba starat
	}
	public void exportTeamyHTML(String filename){
		// TODO metoda pre export teamov do HTML, treba teda vytvorit subor filename
		// je zabezpecene aby subor obsahoval .htm resp. html, o to sa tu teda netreba starat
	}
	public void exportZapasyHTML(String filename){
		// TODO metoda pre export zapasov do HTML, treba teda vytvorit subor filename
		// je zabezpecene aby subor obsahoval .htm resp. html, o to sa tu teda netreba starat
	}
	public void exportSQL(String cesta){
		// TODO metoda pre export ligy do SQL, cesta vyzera nasledovne
		// X:\\Adresar\\ktory\\uzivatel\\zvolil\\nazovligy
		// treba teda vytvorit subory X:\\Adresar\\ktory\\uzivatel\\zvolil\\nazovligy_teamy.sql
		// X:\\Adresar\\ktory\\uzivatel\\zvolil\\nazovligy_hraci.sql
		// X:\\Adresar\\ktory\\uzivatel\\zvolil\\nazovligy_zapasy.sql
	}
}
