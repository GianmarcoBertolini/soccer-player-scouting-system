public class Goalkeeper extends Player {

    private int saves;
    private int cleanSheets;
    private int goalsConceded;

    public Goalkeeper(String fullName, int age, String team, int goalsScored, int assists, int shotsOnTarget, int passesCompleted, double passAccuracy, int tacklesWon, int gamesPlayed, double averageMatchRating, int saves, int cleanSheets, int goalsConceded) {

        super(fullName, age, "GK", team, goalsScored, assists, shotsOnTarget, passesCompleted, passAccuracy, tacklesWon, gamesPlayed, averageMatchRating);

        if (saves < 0) {
            this.saves = 0;
        } else {
            this.saves = saves;
        }

        if (cleanSheets < 0) {
            this.cleanSheets = 0;
        } else {
            this.cleanSheets = cleanSheets;
        }

        if (goalsConceded < 0) {
            this.goalsConceded = 0;
        } else {
            this.goalsConceded = goalsConceded;
        }

        setScoutingScore(calcScoutingScore());
    }

    public double calcScoutingScore() {
        return (saves * 0.35) + (cleanSheets * 0.25) + (getPassAccuracy() * 0.25)  + (getAverageMatchRating() * 0.15) - (goalsConceded * 0.10);
    }

    public int getSaves() {
        return saves;
    }

    public int getCleanSheets() {
        return cleanSheets;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }

    public String toString() {
        return super.toString() + String.format("\n Saves: %d \n Clean Sheets: %d \n Goals Conceded: %d", saves, cleanSheets, goalsConceded);
    }

    // test main for positive result of the scouting score
    //public static void main(String[] args) {
    //    Goalkeeper gk = new Goalkeeper("Michele Di Gregorio", 28, "Juventus", 0, 0, 0, 20, 65.0, 5, 30, 7.2, 95, 12, 28);
    //    System.out.println(gk);
    //    System.out.println(" ");
    //    System.out.println("Scouting Score: " + gk.calcScoutingScore());
    //}

    // test main for negative result of the scouting score
    // public static void main(String[] args) {
    // Goalkeeper gk = new Goalkeeper("Andre Pinsoglio", 34, "Juventus", 0, 0, 0, 0, 0.0, 0, 1, 0.0, 0, 0, 2);
    // System.out.println(gk);
    // System.out.println(" ");
    // System.out.println("Scouting Score: " + gk.calcScoutingScore());
    //}
}
