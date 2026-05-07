public class Defender extends Player {

    public Defender(String fullName, int age, String team, int goalsScored, int assists, int shotsOnTarget, int passesCompleted, double passAccuracy, int tacklesWon, int gamesPlayed, double averageMatchRating) {

        super(fullName, age, "DEF", team, goalsScored, assists, shotsOnTarget, passesCompleted, passAccuracy, tacklesWon, gamesPlayed, averageMatchRating);

        setScoutingScore(calcScoutingScore());
    }

    public double calcScoutingScore() {
        return (getTacklesWon() * 0.35) + (getPassAccuracy() * 0.30) + (getAverageMatchRating() * 0.20) + (getGoalsScored() * 0.15);
    }

    // test main for Defender
    //public static void main(String[] args) {
    //    Defender def = new Defender("Alessandro Bastoni", 24, "Inter",2, 4, 8, 320, 88.5, 55, 33, 7.1);
    //    System.out.println(def);
    //    System.out.println(" ");
    //    System.out.println("Scouting Score: " + def.calcScoutingScore());
    //}
}
