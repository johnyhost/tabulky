import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class Liga {
	List<Team> zoznamTeamov;
	List<Zapas> zoznamZapasov;
	public Liga() {
		this.zoznamTeamov=new ArrayList<Team>();
		this.zoznamZapasov=new ArrayList<Zapas>();
	}
	public void generateZapasy(int pocetTeamov, int pocetOdviet) {
		if(pocetTeamov%2==1) {
			pocetTeamov++; //ak je pocet teamov neparny, zvys sa o jedna aby bol parny 
		}
		File f = new File("berger//"+pocetTeamov+".txt");
		try {
			Scanner scFile = new Scanner(f);
			int i = 1; //pocitadlo poctu riadkov a id zapasov
			while (scFile.hasNextLine()) {
				String riadok = scFile.nextLine();
				Scanner scRiadok = new Scanner(riadok);
				int id1=scRiadok.nextInt();
				int id2=scRiadok.nextInt();
				zoznamZapasov.add(new Zapas(i,id1,id2));
				i++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("error: berger's file not found");
			e.printStackTrace();
		}
		//v pripade ze sa hra liga systemom doma-vonku prip. viackrat, raz uz zbehlo generovanie zapasov
		int pocetZapasov = zoznamZapasov.size();
		for (int i = 1; i <= pocetOdviet; i++) {
			for (int j = 0; j < pocetZapasov; j++) {
				if(i%2==1) {
					zoznamZapasov.add(new Zapas(i*pocetZapasov+j+1,zoznamZapasov.get(j).getIdTeamu2(),zoznamZapasov.get(j).getIdTeamu1()));
				} else {
					zoznamZapasov.add(new Zapas(i*pocetZapasov+j+1,zoznamZapasov.get(j).getIdTeamu1(),zoznamZapasov.get(j).getIdTeamu2()));
				}
			}
		}
		for (Zapas z : zoznamZapasov) {
			System.out.println("Zapas s id "+z.getIdZapasu()+" pridany: "+z.getIdTeamu1()+" vs. "+z.getIdTeamu2());
		}
			
		
	}
	public Team getTeam(int idTeamu){
		for (int i = 0; i < zoznamTeamov.size(); i++) {
			if(zoznamTeamov.get(i).getIdTeamu()==idTeamu) return zoznamTeamov.get(i);
		} 
		return null;
	}
	public Zapas getZapas(int idZapasu){
		for (int i = 0; i < zoznamZapasov.size(); i++) {
			if(zoznamZapasov.get(i).getIdZapasu()==idZapasu) return zoznamZapasov.get(i);
		} 
		return null;
	}
	public Team getTeam(String nazovTeamu){
		for (int i = 0; i < zoznamTeamov.size(); i++) {
			if(zoznamTeamov.get(i).getNazov()==nazovTeamu) return zoznamTeamov.get(i);
		} 
		return null;
	}
	public Hrac getHrac(int idHraca){
		for (int i = 0; i < zoznamTeamov.size(); i++) {
			for(int j=0;j<zoznamTeamov.get(i).zoznamHracov.size();j++){
				if(zoznamTeamov.get(i).zoznamHracov.get(j).getIdHraca()==idHraca) return zoznamTeamov.get(i).zoznamHracov.get(j);
			}
			
		} 
		return null;
	}
	public List<Zapas> getZoznamZapasovTeamu(int id) {
		List<Zapas> zznm = new ArrayList<Zapas>();
		for (Zapas zapas : zoznamZapasov) {
			if(zapas.getIdTeamu1()==id||zapas.getIdTeamu2()==id) zznm.add(zapas);
		}
		return zznm;
	}
	public Hrac getHrac(String menoHraca){
		for (int i = 0; i < zoznamTeamov.size(); i++) {
			for(int j=0;j<zoznamTeamov.get(i).zoznamHracov.size();j++){
				if(zoznamTeamov.get(i).zoznamHracov.get(j).getMeno()==menoHraca) return zoznamTeamov.get(i).zoznamHracov.get(j);
			}
			
		} 
		return null;
	}
	public void exportHtmlTabulka(String s) throws IOException {
		//s je cesta k suboru
		if(!s.contains(".htm")) {
			s+=".html";
		}
		BufferedWriter bwout = new BufferedWriter(new FileWriter(s));
		String output = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"><html>  <head>  <meta http-equiv=\"content-type\" content=\"text/html; charset=utf8\">  <meta name=\"generator\" content=\"PSPad editor, www.pspad.com\">  <title></title>  </head><body>";
		output+="<table border='1' cellspacing='0' cellpadding='0' bordercolor='#FED8C0' width='400'>";
		output+="<tr><td style='text-align: center;'>#</td><td> Team Name</td><td style='text-align: center;'>Z</td><td style='text-align: center;'>V</td><td style='text-align: center;'>R</td><td style='text-align: center;'>P</td>";
		output+="<td style='text-align: center;'>Skore</td><td style='text-align: center;'>B</td></tr>";
		for (int i = 0; i < zoznamTeamov.size(); i++) {
			Team t = zoznamTeamov.get(i);
			output+="<tr>";
			output+="<td style='text-align: center;'>"+(int)(i+1)+".</td><td>"+t.getNazov()+"</td><td style='text-align: center;'>"+getZoznamZapasovTeamu(t.getIdTeamu()).size()+"</td>";
			output+="<td style='text-align: center;'>v</td><td style='text-align: center;'>r</td><td style='text-align: center;'>p</td><td style='text-align: center;'>"+t.getStrelGoly()+":"+t.getInkasGoly()+"</td><td style='text-align: center;'>"+t.getBody(getZoznamZapasovTeamu(t.getIdTeamu()))+"</td>";
			output+="</tr>";
		}
		
		output+="</body></html>";
		bwout.write(output);
		bwout.close();
	}
}
