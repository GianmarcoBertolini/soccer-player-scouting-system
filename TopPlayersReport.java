import java.util.ArrayList;

public class TopPlayersReport extends ScoutingReport {

    public TopPlayersReport(ArrayList<Player> players) {
        super(players);
    }

    public void generate() {
        ArrayList<Player> list = getPlayers();
        
        System.out.println("\n---- Top 10 Players ----");
        System.out.println("Name:\n Age\tTeam\tScouting Score");
        System.out.println();

        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (count == 10) {
                break;
            }
            Player p = list.get(i);
            System.out.printf("%s:\n %d\t%s\t%.2f\n---------------------------\n", p.getName(), p.getAge(), p.getTeam(), p.calcScoutingScore());
            count++;
        }
    }

}

