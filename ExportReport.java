import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class ExportReport extends ScoutingReport {

    public ExportReport(ArrayList<Player> players) {
        super(players);
    }

    public void generate() {
        ArrayList<Player> list = getPlayers();
        String[] positions = {"GK", "DEF", "MID", "FWD"};
        try {
            PrintWriter pw = new PrintWriter("scoutingReport.txt");
            pw.println("\n SOCCER SCOUTING REPORT \n\n");
            for (int i = 0; i < positions.length; i++) {
                String pos = positions[i];
                Player best = null;
                for (int j = 0; j < list.size(); j++) {
                    Player p = list.get(j);
                    if (p.getPosition().equalsIgnoreCase(pos) && p.getGamesPlayed() > 0) {
                        if (best == null || p.calcScoutingScore() > best.calcScoutingScore()) {
                            best = p;
                        }
                    }
                }
                if (best != null) {
                    pw.println("\t Position: " + pos);
                    pw.println("----------------------------------------");
                    pw.println(best.toString());
                    pw.printf("\n Scouting Score: %.2f \n\n\n", best.calcScoutingScore());
                }
            }
            pw.println("\n These players are the best profiles for each position based on the Scouting Score value.");
            pw.close();
            System.out.println("Scouting report exported to scoutingReport.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to export scouting report.");
        }
    }

}
