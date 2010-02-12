package zaklad;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// trieda urcena na ukladanie existujucej ligy do XML
public class XMLSaver {
	private Liga liga;
	private String filename;
	public XMLSaver(String filename,Liga liga){
		this.liga=liga;
		this.filename=filename;
		save();
	}
	private void save(){
		try {
			
			File f = new File(filename);
			if(f.exists()){
				boolean zmazany = f.delete();
			    if (!zmazany)
			      throw new IllegalArgumentException("Subor sa nepodarilo vymazat");
			}
			
			BufferedWriter bwout = new BufferedWriter(new FileWriter(filename));
			if(liga==null) {
				bwout.close();
				return;
			}
			bwout.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");bwout.newLine();
			//bwout.write("<!DOCTYPE LIGA SYSTEM \"Data\\liga.dtd\" >");bwout.newLine();
			bwout.write("<LIGA>");bwout.newLine();
			bwout.write("	<NAZOVLIGY>"+liga.getNazovLigy()+"</NAZOVLIGY>");bwout.newLine();
			bwout.write("	<DLZKAZAPASU>"+liga.getDlzkaZapasu()+"</DLZKAZAPASU>");bwout.newLine();
			for(Team team: liga.getZoznamTeamov()){
				for(String riadok: teamToXML(team)){
					bwout.write(riadok);bwout.newLine();
				}
			}
			for(Zapas zapas: liga.getZoznamZapasov()){
				for(String riadok: zapasToXML(zapas)){
					bwout.write(riadok);bwout.newLine();
				}
			}
			bwout.write("</LIGA>");
			bwout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private ArrayList<String> hracToXML(Hrac hrac){
		ArrayList<String> vysledok = new ArrayList<String>();
		vysledok.add("		<HRAC>");
		vysledok.add("			<IDHRACA>"+hrac.getIdHraca()+"</IDHRACA>");
		vysledok.add("			<MENO>"+hrac.getMeno()+"</MENO>");
		for(int i=0;i<hrac.getIdZapasov().size();i++){
			int idZapasu=hrac.getIdZapasov().get(i);
			int hral=0;
			if(hrac.getHralZapas(idZapasu))hral=1;
			vysledok.add("			<DATAZAPASU>");
			vysledok.add("				<DATAIDZAPASU>"+idZapasu+"</DATAIDZAPASU>");
			
			vysledok.add("				<HRAL>"+hral+"</HRAL>");
			vysledok.add("				<GOLY>"+hrac.getGoly(idZapasu)+"</GOLY>");
			vysledok.add("				<ASIST>"+hrac.getAsist(idZapasu)+"</ASIST>");
			vysledok.add("				<TRESTMIN>"+hrac.getTrestMin(idZapasu)+"</TRESTMIN>");
			vysledok.add("				<ODCHYTMIN>"+hrac.getOdchytMin(idZapasu)+"</ODCHYTMIN>");
			vysledok.add("				<INKASGOLY>"+hrac.getInkasGoly(idZapasu)+"</INKASGOLY>");
			vysledok.add("			</DATAZAPASU>");
		}
		vysledok.add("	</HRAC>");
		return vysledok;
	}
	private ArrayList<String> teamToXML(Team team){
		ArrayList<String> vysledok = new ArrayList<String>();
		vysledok.add("	<TEAM>");
		vysledok.add("		<IDTEAMU>"+team.getIdTeamu()+"</IDTEAMU>");
		vysledok.add("		<NAZOV>"+team.getNazov()+"</NAZOV>");
		for(Hrac hrac: team.getZoznamHracov()){
			vysledok.addAll(vysledok.size(), hracToXML(hrac));
		}
		vysledok.add("	</TEAM>");
		return vysledok;
	}
	private ArrayList<String> zapasToXML(Zapas zapas){
		ArrayList<String> vysledok = new ArrayList<String>();
		vysledok.add("	<ZAPAS>");
		vysledok.add("		<IDZAPASU>"+zapas.getIdZapasu()+"</IDZAPASU>");
		vysledok.add("		<IDTEAMU1>"+zapas.getIdTeamu1()+"</IDTEAMU1>");
		vysledok.add("		<IDTEAMU2>"+zapas.getIdTeamu2()+"</IDTEAMU2>");
		vysledok.add("		<VYSLEDOK>"+zapas.getVysledok()+"</VYSLEDOK>");		
		vysledok.add("	</ZAPAS>");
		return vysledok;
	}
}




 
  
  
  
  
  

