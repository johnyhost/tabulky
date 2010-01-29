package zaklad;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		BufferedWriter bwout;
		try {
			bwout = new BufferedWriter(new FileWriter(filename));
		List<Hrac> zznmHracov = new ArrayList<Hrac>();
		for (Team t : liga.getZoznamTeamov()) {
			for (Hrac hrac : t.getZoznamHracov()) {
				zznmHracov.add(hrac);
			}
		}
		zznmHracov = liga.sortStatsHraci(zznmHracov);
		String output ="<table border='1' cellspacing='0' cellpadding='0' bordercolor='#FED8C0' width='400'>";
		output+="<tr><td style='text-align: center;' colspan='8'>"+liga.getNazovLigy()+"</td></tr>";
		output+="<tr><td style='text-align: center;'>#</td><td>&nbsp;Meno</td><td style='text-align: center;'>Team</td><td style='text-align: center;'>Z</td><td style='text-align: center;'>G</td><td style='text-align: center;'>A</td><td style='text-align: center;'>B</td><td style='text-align: center;'>TM</td></tr>";
		for (int i=0; i<zznmHracov.size();i++) {
			Hrac h = zznmHracov.get(i);
			output+="<tr>";
			output+="<td style='text-align: center;'>"+(int)(i+1)+"</td><td>&nbsp;"+h.getMeno()+"</td><td style='text-align: center;'>TEAM</td><td style='text-align: center;'>"+h.getOdohratychZapasov()+"</td><td style='text-align: center;'>"+h.getGoly();
			output+="</td><td style='text-align: center;'>"+h.getAsist()+"</td><td style='text-align: center;'>"+(h.getGoly()+h.getAsist())+"</td><td style='text-align: center;'>"+h.getTrestMin()+"</td>";
			output+="</tr>";
		}
		output+="</table><br /><br />";
		bwout.write(output);
					
		//vytvorenie zoznamu brankarov
		List<Hrac> zoznamBrankarov = new ArrayList<Hrac>();
		for (Hrac hrac: zznmHracov) {
			if(hrac.jeBrankar()) {
				zoznamBrankarov.add(hrac);
			}
		}
		zoznamBrankarov = liga.sortStatsBrankari(zoznamBrankarov);
		output = "<table border='1' cellspacing='0' cellpadding='0' bordercolor='#FED8C0' width='400'>";
		output += "<tr><td style='text-align: center;'>#</td><td>&nbsp;Meno</td><td style='text-align: center;'>Team</td><td style='text-align: center;'>OdchMin</td><td style='text-align: center;'>InkGol</td><td style='text-align: center;'>Priem</td></tr>";
		for (int i = 0; i < zoznamBrankarov.size(); i++) {
			Hrac h = zoznamBrankarov.get(i);
			output+="<tr>";
			output+="<td style='text-align: center;'>"+(int)(i+1)+".</td><td>&nbsp;"+h.getMeno()+"</td><td style='text-align: center;'>TEAM</td><td style='text-align: center;'>"+h.getOdchytMin()+"</td><td style='text-align: center;'>"+h.getInkasGoly()+"</td><td style='text-align: center;'>"+h.getPriemer(45)+"</td>";
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
	public void exportTeamyHTML(String filename) {
		// TODO metoda pre export tabulky teamov do HTML, treba teda vytvorit subor filename
		// je zabezpecene aby subor obsahoval .htm resp. html, o to sa tu teda netreba starat
		BufferedWriter bwout;
		try {
			bwout = new BufferedWriter(new FileWriter(filename));
		
		String output ="<table border='1' cellspacing='0' cellpadding='0' bordercolor='#FED8C0' width='400'>";
		output+="<tr><td style='text-align: center;' colspan='8'>"+liga.getNazovLigy()+"</td></tr>";
		output+="<tr><td style='text-align: center;'>#</td><td>&nbsp;Team Name</td><td style='text-align: center;'>Z</td><td style='text-align: center;'>V</td><td style='text-align: center;'>R</td><td style='text-align: center;'>P</td>";
		output+="<td style='text-align: center;'>Skore</td><td style='text-align: center;'>B</td></tr>";
		List<Team> zoznamT = liga.sortTeamy(liga.getZoznamTeamov());
		for (int i = 0; i<zoznamT.size() ; i++) {
			Team t = zoznamT.get(i);
			output+="<tr>";
			output+="<td style='text-align: center;'>"+(int)(i+1)+".</td><td>&nbsp;"+t.getNazov()+"</td><td style='text-align: center;'>"+liga.getZoznamZapasovTeamu(t.getIdTeamu()).size()+"</td>";
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
		
			String output ="<table border='1' cellspacing='0' cellpadding='0' bordercolor='#FED8C0' width='400'>";
			output+="<tr><td style='text-align: center;' colspan='4'>"+liga.getNazovLigy()+"</td></tr>"; 
		for (int i = 0; i < liga.getZoznamZapasov().size(); i++) {
			Zapas zapas = liga.getZoznamZapasov().get(i);
			Team team1 =  liga.getTeam(zapas.getIdTeamu1());
			Team team2 = liga.getTeam(zapas.getIdTeamu2());
			int gol1 = 0;
			int gol2 = 0;
			for (Hrac h : team1.getZoznamHracov()) {
				gol1 += h.getGoly(zapas.getIdZapasu());
			}
			for (Hrac h : team2.getZoznamHracov()) {
				gol2 += h.getGoly(zapas.getIdZapasu());
			}
			output+="<tr>";
			output+="<td>&nbsp;"+team1.getNazov()+"</td><td style='text-align: center;'>&nbsp;vs.&nbsp;</td><td>&nbsp;"+team2.getNazov()+"</td>";
			output+="<td style='text-align: center;'>"+gol1+"&nbsp;:&nbsp;"+gol2+"</td>";
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
