package zaklad;
import java.io.IOException;


public class LigaMain {

	
	public static void main(String[] args) throws IOException {
		Liga league /*= new Liga()*/;
		Data data = new Data();
		league = data.loadXML("Data\\liga1.xml");
		System.out.println(league.getZoznamTeamov().get(0).getZoznamHracov().get(0).getMeno());
		for (Zapas z : league.getZoznamZapasov()) {
			System.out.println("Zapas s id "+z.getIdZapasu()+" je medzi: "+league.getTeam(z.getIdTeamu1()).getNazov()+" ("+z.getIdTeamu1()+") vs. "+league.getTeam(z.getIdTeamu2()).getNazov()+"("+z.getIdTeamu2()+")");
		}
		//league.generateZapasy(5,0);
		
		/*
		data = new Data(league);
		data.saveXML("Data\\save.xml");
		*/
	}

}
