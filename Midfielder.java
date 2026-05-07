public class Midfielder extends Player {

    public Midfielder(String fullName, int age, String team, int goalsScored, int assists, int shotsOnTarget, int passesCompleted, double passAccuracy, int tacklesWon, int gamesPlayed, double averageMatchRating) {

        super(fullName, age, "MID", team, goalsScored, assists, shotsOnTarget, passesCompleted, passAccuracy, tacklesWon, gamesPlayed, averageMatchRating);

        setScoutingScore(calcScoutingScore());
    }

    public double calcScoutingScore() {
        return (getPassAccuracy() * 0.30) + (getAssists() * 0.25) + (getGoalsScored() * 0.20) + (getTacklesWon() * 0.15) + (getAverageMatchRating() * 0.10);
    }
}

