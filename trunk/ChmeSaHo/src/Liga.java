import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Liga {
	List<Team> zoznamTeamov;
	List<Zapas> zoznamZapasov;
	public Liga() {
		this.zoznamTeamov=new ArrayList<Team>();
		this.zoznamZapasov=new ArrayList<Zapas>();
	}
	public void generateZapasy(int pocetTeamov) {
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
				System.out.println("Zapas s id "+i+" pridany: "+id1+" vs. "+id2);
				i++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("error: berger's file not found");
			e.printStackTrace();
		}
	}
}
