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
			output+="<td style='text-align: center;'>"+(int)(i+1)+"</td><td>&nbsp;"+h.getMeno()+"</td><td style='text-align: center;'>"+liga.getTeamHraca(h.getIdHraca()).getNazov()+"</td><td style='text-align: center;'>"+h.getOdohratychZapasov()+"</td><td style='text-align: center;'>"+h.getGoly();
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
			output+="<td style='text-align: center;'>"+(int)(i+1)+".</td><td>&nbsp;"+h.getMeno()+"</td><td style='text-align: center;'>"+liga.getTeamHraca(h.getIdHraca()).getNazov()+"</td><td style='text-align: center;'>"+h.getOdchytMin()+"</td><td style='text-align: center;'>"+h.getInkasGoly()+"</td><td style='text-align: center;'>"+h.getPriemer(45)+"</td>";
			output+="</tr>";
		}
		output+="</table>";
		bwout.write(output);
		bwout.close();
		System.out.println("export hracov do html ukonceny");
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
		System.out.println("export teamov do html ukonceny");
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
		System.out.println("export zapasov do html ukonceny");
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
		
		try {
			BufferedWriter bwout = new BufferedWriter(new FileWriter(cesta));
			//vytvorenie databazy
			List<String> zoznamNaVypis = new ArrayList<String>();
			zoznamNaVypis.add("IF DB_ID ('Tabulkator') IS NOT NULL DROP DATABASE Tabulkator");
			zoznamNaVypis.add("GO");
			zoznamNaVypis.add("CREATE DATABASE Tabulkator");
			zoznamNaVypis.add("GO");
			zoznamNaVypis.add("USE Tabulkator");
			zoznamNaVypis.add("GO");
			zoznamNaVypis.add("");
			//vytvorenie tabulky s udajmi o teamoch
			zoznamNaVypis.add("IF OBJECT_ID ('dbo.Teamy') IS NOT NULL DROP TABLE dbo.Teamy");
			zoznamNaVypis.add("GO");
			zoznamNaVypis.add("CREATE TABLE Teamy (id INT NOT NULL PRIMARY KEY, nazov VARCHAR(20), z INT, v INT, r INT, p INT, golyPlus INT, golyMinus INT, body INT)");
			zoznamNaVypis.add("GO");
			for (Team team: liga.getZoznamTeamov()) {
				String riadok = "INSERT Teamy VALUES ("+team.getIdTeamu()+", '"+team.getNazov()+"',"+liga.getZoznamZapasovTeamu(team.getIdTeamu()).size()+", ";
				riadok+=team.getPocetVyhier(liga.getZoznamZapasovTeamu(team.getIdTeamu()))+", "+team.getPocetRemiz(liga.getZoznamZapasovTeamu(team.getIdTeamu()))+", "+team.getPocetPrehier(liga.getZoznamZapasovTeamu(team.getIdTeamu()))+", "+team.getStrelGoly()+", "+team.getInkasGoly()+", "+team.getBody(liga.getZoznamZapasovTeamu(team.getIdTeamu()))+"); ";
				zoznamNaVypis.add(riadok);
			}
			for (String line : zoznamNaVypis) {
				bwout.write(line);
				bwout.newLine();
			}
			zoznamNaVypis.clear();
			bwout.newLine();
			//vytvorenie tabulky s udajmi o hracoch
			zoznamNaVypis.add("IF OBJECT_ID ('dbo.Hraci') IS NOT NULL DROP TABLE dbo.Hraci");
			zoznamNaVypis.add("GO");
			zoznamNaVypis.add("CREATE TABLE Hraci (id INT NOT NULL PRIMARY KEY, meno VARCHAR(30), idTeamu INT NOT NULL, z INT, g INT, a INT, b INT, tm INT)");
			zoznamNaVypis.add("GO");
			int id=1;
			for (Team team : liga.getZoznamTeamov()) {
				for (Hrac hrac : team.getZoznamHracov()) {
					String riadok ="INSERT Hraci VALUES ("+id+", '"+hrac.getMeno()+"', "+team.getIdTeamu()+", "+hrac.getOdohratychZapasov()+", "+hrac.getGoly();
					riadok+=", "+hrac.getAsist()+", "+(hrac.getGoly()+hrac.getAsist())+", "+hrac.getTrestMin()+"); ";
					zoznamNaVypis.add(riadok);
					id++;
				}
			}
			for (String line : zoznamNaVypis) {
				bwout.write(line);
				bwout.newLine();
			}
			zoznamNaVypis.clear();
			bwout.newLine();
			//vytvorenie tabulky s udajmi o brankaroch
			zoznamNaVypis.add("IF OBJECT_ID ('dbo.Brankari') IS NOT NULL DROP TABLE dbo.Brankari");
			zoznamNaVypis.add("GO");
			zoznamNaVypis.add("CREATE TABLE Brankari (id INT NOT NULL PRIMARY KEY, meno VARCHAR(30), idTeamu INT NOT NULL, odchytMin INT, inkasGol INT, priemer REAL)");
			zoznamNaVypis.add("GO");
			id=1;
			for (Team team : liga.getZoznamTeamov()) {
				for (Hrac hrac : team.getZoznamHracov()) {
					if(hrac.jeBrankar()) {
						zoznamNaVypis.add("INSERT Brankari VALUES ("+id+", '"+hrac.getMeno()+"', "+team.getIdTeamu()+", "+hrac.getOdchytMin()+", "+hrac.getInkasGoly()+", "+hrac.getPriemer(liga.getDlzkaZapasu())+" ); ");
						id++;
					}
				}
			}
			for (String line : zoznamNaVypis) {
				bwout.write(line);
				bwout.newLine();
			}
			zoznamNaVypis.clear();
			//vytvorenie tabulky so zapasmi
			zoznamNaVypis.add("IF OBJECT_ID ('dbo.Zapasy') IS NOT NULL DROP TABLE dbo.Zapasy");
			zoznamNaVypis.add("GO");
			zoznamNaVypis.add("CREATE TABLE Zapasy (id INT NOT NULL PRIMARY KEY, idTeam1 INT, idTeam2 INT, golyTeam1 INT, golyTeam2 INT)");
			zoznamNaVypis.add("GO");
			for (Zapas zapas : liga.getZoznamZapasov()) {
				String riadok ="INSERT Zapasy VALUES ( "+zapas.getIdZapasu()+", "+zapas.getIdTeamu1()+", "+zapas.getIdTeamu2()+", ";
				int gol1 = 0;
				int gol2 = 0;
				for (Hrac h : liga.getTeam(zapas.getIdTeamu1()).getZoznamHracov()) {
					gol1 += h.getGoly(zapas.getIdZapasu());
				}
				for (Hrac h : liga.getTeam(zapas.getIdTeamu2()).getZoznamHracov()) {
					gol2 += h.getGoly(zapas.getIdZapasu());
				}
				riadok+=gol1+", "+gol2+"); ";
				zoznamNaVypis.add(riadok);
			}
			for (String line : zoznamNaVypis) {
				bwout.write(line);
				bwout.newLine();
			}
			zoznamNaVypis.clear();
			//uzatvorenie
			bwout.close();
			System.out.println("export do sql ukonceny");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
