import java.util.ArrayList;

public abstract class ScoutingReport {

    private ArrayList<Player> players;

    public ScoutingReport(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public abstract void generate();

}

