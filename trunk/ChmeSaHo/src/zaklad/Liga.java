package zaklad;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Liga {
	private List<Team> zoznamTeamov;
	private List<Zapas> zoznamZapasov;
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
			if(zoznamTeamov.get(i).getNazov().equals(nazovTeamu)) return zoznamTeamov.get(i);
		} 
		return null;
	}
	public Hrac getHrac(int idHraca){
		for (int i = 0; i < zoznamTeamov.size(); i++) {
			for(int j=0;j<zoznamTeamov.get(i).getZoznamHracov().size();j++){
				if(zoznamTeamov.get(i).getZoznamHracov().get(j).getIdHraca()==idHraca) return zoznamTeamov.get(i).getZoznamHracov().get(j);
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
			for(int j=0;j<zoznamTeamov.get(i).getZoznamHracov().size();j++){
				if(zoznamTeamov.get(i).getZoznamHracov().get(j).getMeno()==menoHraca) return zoznamTeamov.get(i).getZoznamHracov().get(j);
			}
			
		} 
		return null;
	}	
	public List<Team> getZoznamTeamov() {
		return zoznamTeamov;
	}
	public List<Zapas> getZoznamZapasov() {
		return zoznamZapasov;
	}
	public void addTeamZoznamTeamov(Team t) {
		zoznamTeamov.add(t);
	}
	public void addZapasZoznamZapasov(Zapas z) {
		zoznamZapasov.add(z);
	}
	public List<Team> sortTeamy(List<Team> teamy) {
		List<Team> zznmsrtd = new ArrayList<Team>();
		while(!teamy.isEmpty()) {
			Team teamK = teamy.get(0);
			for (int i = 1; i < teamy.size(); i++) {
				Team t = teamy.get(i);
				if (t.getBody(getZoznamZapasovTeamu(t.getIdTeamu()))>=teamK.getBody(getZoznamZapasovTeamu(teamK.getIdTeamu()))) {
					if (t.getBody(getZoznamZapasovTeamu(t.getIdTeamu()))==teamK.getBody(getZoznamZapasovTeamu(teamK.getIdTeamu()))) {
						//ak sa rovna pocet bodov, kontrolujem rozdiel skore
						if((t.getStrelGoly()-t.getInkasGoly()>teamK.getStrelGoly()-teamK.getInkasGoly())) {
							teamK = teamy.get(i);
						}
					} else {
						teamK = teamy.get(i);
					}
				}
			}
			zznmsrtd.add(teamK);
			teamy.remove(teamK);			
		}
		return zznmsrtd;
	}
	public List<Hrac> sortStatsBrankari(List<Hrac> brankari) {
		List<Hrac> sorted = new ArrayList<Hrac>();
		while(!brankari.isEmpty()) {
			Hrac brankar = brankari.get(0);
			for (int i = 0; i < brankari.size(); i++) {
				if(brankari.get(i).getPriemer(45)<=brankar.getPriemer(45)) {
					if(brankari.get(i).getPriemer(45)==brankar.getPriemer(45)) {
						if(brankari.get(i).getOdchytMin()>brankar.getOdchytMin()) {
							brankar = brankari.get(i);
						}
					} else {
						brankar = brankari.get(i);
					}
				}
			}
			sorted.add(brankar);
			brankari.remove(brankar);
		}
	
		return sorted;
	}
	public List<Hrac> sortStatsHraci(List<Hrac> hraci) {
		List<Hrac> sorted = new ArrayList<Hrac>();
		while(!hraci.isEmpty()) {
			Hrac h = hraci.get(0);
			for (int i = 0; i < hraci.size(); i++) {
				if(hraci.get(i).getGoly()+hraci.get(i).getAsist()>=h.getGoly()+h.getAsist()) {
					if(hraci.get(i).getGoly()+hraci.get(i).getAsist()==h.getGoly()+h.getAsist()) {
						//ak maju dvaja hraci rovnaky pocet bodov, ide dalsie porovnavanie
						if(hraci.get(i).getGoly()>h.getGoly()) {
							h = hraci.get(i);
						} else if(hraci.get(i).getOdohratychZapasov()<h.getOdohratychZapasov()) {
								h = hraci.get(i);
						}
					}
				}
			}
			sorted.add(h);
			hraci.remove(h);
		}
		return sorted;
	}
}
