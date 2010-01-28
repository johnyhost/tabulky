package zaklad;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
	public void exportTeamyHTML(String filename) {
		// TODO metoda pre export tabulky teamov do HTML, treba teda vytvorit subor filename
		// je zabezpecene aby subor obsahoval .htm resp. html, o to sa tu teda netreba starat
		BufferedWriter bwout;
		try {
			bwout = new BufferedWriter(new FileWriter(filename));
		
		String output ="<table border='1' cellspacing='0' cellpadding='0' bordercolor='#FED8C0' width='400'>";
		output+="<tr><td style='text-align: center;'>#</td><td> Team Name</td><td style='text-align: center;'>Z</td><td style='text-align: center;'>V</td><td style='text-align: center;'>R</td><td style='text-align: center;'>P</td>";
		output+="<td style='text-align: center;'>Skore</td><td style='text-align: center;'>B</td></tr>";
		
		for (int i = 0; i < liga.getZoznamTeamov().size(); i++) {
			Team t = liga.getZoznamTeamov().get(i);
			output+="<tr>";
			output+="<td style='text-align: center;'>"+(int)(i+1)+".</td><td>"+t.getNazov()+"</td><td style='text-align: center;'>"+liga.getZoznamZapasovTeamu(t.getIdTeamu()).size()+"</td>";
			output+="<td style='text-align: center;'>"+t.getPocetVyhier(liga.getZoznamZapasovTeamu(t.getIdTeamu()))+"</td><td style='text-align: center;'>"+t.getPocetRemiz(liga.getZoznamZapasovTeamu(t.getIdTeamu()))+"</td><td style='text-align: center;'>"+t.getPocetPrehier(liga.getZoznamZapasovTeamu(t.getIdTeamu()))+"</td><td style='text-align: center;'>"+t.getStrelGoly()+":"+t.getInkasGoly()+"</td><td style='text-align: center;'>"+t.getBody(liga.getZoznamZapasovTeamu(t.getIdTeamu()))+"</td>";
			output+="</tr>";
		}
		output+="</table>";
		bwout.write(output);
		bwout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void exportZapasyHTML(String filename) {
		// TODO metoda pre export vysledkov zapasov do HTML, treba teda vytvorit subor filename
		// je zabezpecene aby subor obsahoval .htm resp. html, o to sa tu teda netreba starat
		BufferedWriter bwout;
		try {
			bwout = new BufferedWriter(new FileWriter(filename));
		
		String output = "<table width='400'>";
		
		for (int i = 0; i < liga.getZoznamZapasov().size(); i++) {
			Zapas zapas = liga.getZoznamZapasov().get(i);
			Team team1 =  liga.getTeam(zapas.getIdTeamu1());
			Team team2 = liga.getTeam(zapas.getIdTeamu2());
			output+="<tr>";
			output+="<td>"+team1.getNazov()+"</td><td>&nbsp;vs.&nbsp;</td><td>"+team2.getNazov()+"</td>";
			output+="<td>"+"</td><td>:</td><td>"+"</td>";
			output+="</tr>";
		}
		output+="</table>";
		bwout.write(output);
		bwout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void exportSQL(String cesta){
		// TODO metoda pre export ligy do SQL, cesta vyzera nasledovne
		// X:\\Adresar\\ktory\\uzivatel\\zvolil\\nazovligy
		// treba teda vytvorit subory X:\\Adresar\\ktory\\uzivatel\\zvolil\\nazovligy_teamy.sql
		// X:\\Adresar\\ktory\\uzivatel\\zvolil\\nazovligy_hraci.sql
		// X:\\Adresar\\ktory\\uzivatel\\zvolil\\nazovligy_zapasy.sql
	}
}