public class Forward extends Player {

    public Forward(String fullName, int age, String team, int goalsScored, int assists, int shotsOnTarget, int passesCompleted, double passAccuracy, int tacklesWon, int gamesPlayed, double averageMatchRating) {

        super(fullName, age, "FWD", team, goalsScored, assists, shotsOnTarget, passesCompleted, passAccuracy, tacklesWon, gamesPlayed, averageMatchRating);

        setScoutingScore(calcScoutingScore());
    }

    public double calcScoutingScore() {
        return (getGoalsScored() * 0.40) + (getShotsOnTarget() * 0.30) + (getAssists() * 0.15) + (getAverageMatchRating() * 0.15);
    }
}
