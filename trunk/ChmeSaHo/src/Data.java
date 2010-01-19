/**
 * @author Laky
 * Trieda urcena na ukladanie, nacitanie a export dat
 */
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
}
