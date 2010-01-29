package zaklad;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class XMLLoader extends DefaultHandler{
	private Liga liga;
	private String filename;
	
	private Team tempTeam;
	private Hrac tempHrac;
	private Zapas tempZapas;
	private int tempDataIdZapasu;
	private String tempStr;
	public XMLLoader(String filename){
		this.filename=filename;
		liga=new Liga();
		load();
	}
	private void load(){
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			
			//get a new instance of parser
			SAXParser parser = factory.newSAXParser();
			
			//parse the file and also register this class for call backs
			parser.parse(filename, this);
			
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}
		
	}
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//reset
		tempStr="";
		
		if(qName.equals("TEAM")) {
			tempTeam=new Team();
		}else if(qName.equals("HRAC")){
			tempHrac=new Hrac();
		}else if(qName.equals("ZAPAS")){
			tempZapas=new Zapas();
		}
		
		//else if(qName.equals("")){}
	}
	

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempStr = new String(ch,start,length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if(qName.equals("NAZOVLIGY")) {
			liga.setNazovLigy(tempStr);			
		}else if(qName.equals("DLZKAZAPASU")) {
			liga.setDlzkaZapasu(Integer.parseInt(tempStr));			
		}else if(qName.equals("TEAM")) {
			liga.addTeamZoznamTeamov(tempTeam);			
		}else if (qName.equals("IDTEAMU")) {
			tempTeam.setIdTeamu(Integer.parseInt(tempStr));
		}else if (qName.equals("NAZOV")) {
			tempTeam.setNazov(tempStr);			
		}else if (qName.equals("HRAC")) {
			tempTeam.getZoznamHracov().add(tempHrac);
		}else if (qName.equals("IDHRACA")) {
			tempHrac.setIdHraca(Integer.parseInt(tempStr));
		}else if (qName.equals("MENO")) {
			tempHrac.setMeno(tempStr);
		}else if (qName.equals("DATAIDZAPASU")) {
			tempDataIdZapasu=Integer.parseInt(tempStr);
		}else if (qName.equals("HRAL")) {
			tempHrac.addHralZapas(tempDataIdZapasu, Integer.parseInt(tempStr)==1);
		}else if (qName.equals("GOLY")) {
			tempHrac.addGoly(Integer.parseInt(tempStr));
		}else if (qName.equals("ASIST")) {
			tempHrac.addAsist(Integer.parseInt(tempStr));
		}else if (qName.equals("TRESTMIN")) {
			tempHrac.addTrestMin(Integer.parseInt(tempStr));
		}else if (qName.equals("ODCHYTMIN")) {
			tempHrac.addOdchytMin(Integer.parseInt(tempStr));
		}else if (qName.equals("INKASGOLY")) {
			tempHrac.addInkasGoly(Integer.parseInt(tempStr));
		}else if (qName.equals("IDZAPASU")) {
			tempZapas.setIdZapasu(Integer.parseInt(tempStr));
		}else if (qName.equals("IDTEAMU1")) {
			tempZapas.setIdTeamu1(Integer.parseInt(tempStr));
		}else if (qName.equals("IDTEAMU2")) {
			tempZapas.setIdTeamu2(Integer.parseInt(tempStr));
		}else if (qName.equals("VYSLEDOK")) {
			tempZapas.setVysledok(Integer.parseInt(tempStr));
		}else if (qName.equals("ZAPAS")) {
			liga.addZapasZoznamZapasov(tempZapas);
		}
		
		//else if (qName.equals("")) {}
		
	}
	public Liga result(){
		return liga;
	}
}
