
public class LigaMain {

	
	public static void main(String[] args) {
		Liga league;
		Data data = new Data();
		league = data.loadXML("Data\\liga1.xml");
		System.out.println(league.zoznamTeamov.get(0).zoznamHracov.get(0).getMeno());
		// league.generateZapasy(28);
	}

}
