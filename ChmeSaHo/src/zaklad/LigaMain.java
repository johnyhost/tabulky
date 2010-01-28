package zaklad;
import java.io.IOException;


public class LigaMain {

	
	public static void main(String[] args) throws IOException {
		Liga league;
		Data data = new Data();
		league = data.loadXML("Data\\liga1.xml");
		System.out.println(league.getZoznamTeamov().get(0).getZoznamHracov().get(0).getMeno());
		// league.generateZapasy(28);
		
		
		data = new Data(league);
		data.saveXML("Data\\save.xml");
		
	}

}
