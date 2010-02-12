package zaklad;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import porovnavanie.BrankarByStats;
import porovnavanie.HracByStats;
import porovnavanie.TeamByStats;


public class Liga {
	private List<Team> zoznamTeamov;
	private List<Zapas> zoznamZapasov;
	private int dlzkaZapasu;
	private int bodyZaVyhru;
	private String nazovLigy;
	public Liga() {
		dlzkaZapasu=45;
		bodyZaVyhru=3;
		this.zoznamTeamov=new ArrayList<Team>();
		this.zoznamZapasov=new ArrayList<Zapas>();
	}	
	public void generateZapasy(int pocetTeamov, int pocetOdviet) {
		// pocet odviet udava kolko krat sa spolu teamy este stretnu po prvej serii
		boolean neparnyPocetTeamov;
		File f;
		if(pocetTeamov%2==1) {
			f = new File("berger//"+(pocetTeamov+1)+".txt");
			neparnyPocetTeamov=true; //pamataj si ze je neparny pocet teamov
		} else {
			f = new File("berger//"+pocetTeamov+".txt");
			neparnyPocetTeamov=false; // nie je neparny, je parny
		}
		try {
			Scanner scFile = new Scanner(f);
			int i = 0; //pocitadlo poctu riadkov a id zapasov
			while (scFile.hasNextLine()) {
				String riadok = scFile.nextLine();
				Scanner scRiadok = new Scanner(riadok);
				int id1=scRiadok.nextInt();
				int id2=scRiadok.nextInt();
				if(neparnyPocetTeamov) { 
					if(id1-1<pocetTeamov&&id2-1<pocetTeamov) {
						// v pripade ze je neparny pocet teamov, tak je nutne skontrolovat
						// ci nie je jeden z tymov "falosny" teda ten posledny, umelo pridany
						zoznamZapasov.add(new Zapas(i,zoznamTeamov.get(id1-1).getIdTeamu(),zoznamTeamov.get(id2-1).getIdTeamu()));
						i++; 
						}
				} else {
					//ak je parny pocet teamov, nestaram sa a pridavam
					zoznamZapasov.add(new Zapas(i,zoznamTeamov.get(id1-1).getIdTeamu(),zoznamTeamov.get(id2-1).getIdTeamu()));
					i++;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("error: berger's file not found");
			e.printStackTrace();
		}
		// generovanie dalsich serii zapasov (odviet)
		int pocetZapasov = zoznamZapasov.size(); // zapamatam si, kolko zapasov je v zakladnej serii zapasov
		for (int i = 0; i < pocetOdviet; i++) {
			for (int j = 0; j < pocetZapasov; j++) {
				if(i%2==1) {
					// toto je rozlisenie, na to aby sa pri odvetach striedali teamy, v prvej serii 2-4, v druhej 4-2, v tretej 2-4 atd
					// malo by to vplyv keby sme rozlisovali vysledok doma a vonku, ale je to len kozmeticka vec
					zoznamZapasov.add(new Zapas(i*pocetZapasov+j+1,zoznamZapasov.get(j).getIdTeamu2(),zoznamZapasov.get(j).getIdTeamu1()));
				} else {
					zoznamZapasov.add(new Zapas(i*pocetZapasov+j+1,zoznamZapasov.get(j).getIdTeamu1(),zoznamZapasov.get(j).getIdTeamu2()));
				}
			}
		}
		//vypis pre kontrolu
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
	public Team getTeamHraca(int idHraca){
		for (int i = 0; i < zoznamTeamov.size(); i++) {
			for(int j=0;j<zoznamTeamov.get(i).getZoznamHracov().size();j++){
				if(zoznamTeamov.get(i).getZoznamHracov().get(j).getIdHraca()==idHraca){
					return zoznamTeamov.get(i);
				}
			}			
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
	public List<Zapas> getZoznamZapasovTeamu(int idTeamu) {
		List<Zapas> zznm = new ArrayList<Zapas>();
		for (Zapas zapas : zoznamZapasov) {
			if(zapas.getIdTeamu1()==idTeamu||zapas.getIdTeamu2()==idTeamu) zznm.add(zapas);
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
	public int getDlzkaZapasu() {
		return dlzkaZapasu;
	}
	public void setDlzkaZapasu(int dlzkaZapasu) {
		this.dlzkaZapasu = dlzkaZapasu;
	}
	public String getNazovLigy() {
		return nazovLigy;
	}
	public void setNazovLigy(String nazovLigy) {
		this.nazovLigy = nazovLigy;
	}
	public void addTeamZoznamTeamov(Team t) {
		zoznamTeamov.add(t);
	}
	public void addZapasZoznamZapasov(Zapas z) {
		zoznamZapasov.add(z);
	}
	public List<Team> sortTeamy(List<Team> teamy) {		
		Collections.sort(teamy,Collections.reverseOrder(new TeamByStats(this)));
		return teamy;
	}
	public List<Hrac> sortStatsBrankari(List<Hrac> brankari) {
		Collections.sort(brankari,new BrankarByStats(this));		
		return brankari;
	}
	public List<Hrac> sortStatsHraci(List<Hrac> hraci) {
		Collections.sort(hraci,Collections.reverseOrder(new HracByStats(this)));
		return hraci;
	}
	public List<Hrac> getZoznamHracov(){
		List<Hrac> vysledok = new ArrayList<Hrac>();
		for (int i = 0; i < zoznamTeamov.size(); i++) {
			for(int j=0;j<zoznamTeamov.get(i).getZoznamHracov().size();j++){
				vysledok.add(zoznamTeamov.get(i).getZoznamHracov().get(j));
			}
			
		} 
		return vysledok;
	}
	public List<Hrac> getZoznamBrankarov(){
		// vrati zoznam hracov ktori maju odchytanu aspon minutu
		List<Hrac> vysledok = new ArrayList<Hrac>();
		for (int i = 0; i < zoznamTeamov.size(); i++) {
			for(int j=0;j<zoznamTeamov.get(i).getZoznamHracov().size();j++){
				if(zoznamTeamov.get(i).getZoznamHracov().get(j).jeBrankar()==true)vysledok.add(zoznamTeamov.get(i).getZoznamHracov().get(j));
			}
			
		} 
		return vysledok;
	}
	public int getVolneIdHraca(){
		int max=-1;
		for(int i=0;i<zoznamTeamov.size();i++){
			for (int j = 0; j < zoznamTeamov.get(i).getZoznamHracov().size(); j++) {
				if(zoznamTeamov.get(i).getZoznamHracov().get(j).getIdHraca()>max) max = zoznamTeamov.get(i).getZoznamHracov().get(j).getIdHraca();
			}
			
		}
		return max+1;
		
	}
	public int getBodyZaVyhru() {
		return bodyZaVyhru;
	}
	public void setBodyZaVyhru(int bodyZaVyhru) {
		this.bodyZaVyhru = bodyZaVyhru;
	}
}
