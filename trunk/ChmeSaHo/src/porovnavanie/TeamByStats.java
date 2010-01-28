package porovnavanie;

import java.util.Comparator;

import zaklad.Liga;
import zaklad.Team;

public class TeamByStats implements Comparator<Team>{
	Liga liga;
	public TeamByStats(Liga liga){
		this.liga = liga;
	}
	public int compare(Team team1, Team team2) {		
		if (team1.getBody(liga.getZoznamZapasovTeamu(team1.getIdTeamu()))
				>
		team2.getBody(liga.getZoznamZapasovTeamu(team2.getIdTeamu()))){
			return 1; 
		} else if(team1.getBody(liga.getZoznamZapasovTeamu(team1.getIdTeamu()))
				==
		team2.getBody(liga.getZoznamZapasovTeamu(team2.getIdTeamu()))){
			if(team1.getStrelGoly()-team1.getInkasGoly()
					>
			team2.getStrelGoly()-team2.getInkasGoly()) return 1; else return -1;
		} else return -1;
	}

}
