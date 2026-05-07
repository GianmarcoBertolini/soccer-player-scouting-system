import java.util.ArrayList;

public class PlayerReport extends ScoutingReport {

    public PlayerReport(ArrayList<Player> players) {
        super(players);
    }

    public void generate() {
        ArrayList<Player> list = getPlayers();
        Player p = list.get(0);

        System.out.println("\n-- Player Data Report --");
        System.out.println(p.toString());
        System.out.printf("\n Scouting Score: %.2f \n\n", p.calcScoutingScore());
    }
}