public abstract class Player {

    private String fullName;
    private int age;
    private String position;
    private String team;
    private int goalsScored;
    private int assists;
    private int shotsOnTarget;
    private int passesCompleted;
    private double passAccuracy;
    private int tacklesWon;
    private int gamesPlayed;
    private double averageMatchRating;
    private double scoutingScore;

    public Player(String fullName, int age, String position, String team, int goalsScored, int assists, int shotsOnTarget, int passesCompleted, double passAccuracy, int tacklesWon, int gamesPlayed, double averageMatchRating) {
        if (fullName == null || fullName.isBlank()) {
            this.fullName = "Unknown";
        } else {
            this.fullName = fullName;
        }
        if (age < 18) {
            this.age = 18;
        }else if(age > 40){
            this.age = 40;
        }else {
            this.age = age;
        }
        if (position == null || position.isBlank()) {
            this.position = "Unknown";
        } else {
            this.position = position;
        }
        if (team == null || team.isBlank()) {
            this.team = "Unknown";
        } else {
            this.team = team;
        }
        if (goalsScored < 0) {
            this.goalsScored = 0;
        } else {
            this.goalsScored = goalsScored;
        }
        if (assists < 0) {
            this.assists = 0;
        } else {
            this.assists = assists;
        }
        if (shotsOnTarget < 0) {
            this.shotsOnTarget = 0;
        } else {
            this.shotsOnTarget = shotsOnTarget;
        }
        if (passesCompleted < 0) {
            this.passesCompleted = 0;
        } else {
            this.passesCompleted = passesCompleted;
        }
        if (passAccuracy < 0.0 || passAccuracy > 100.0) {
            this.passAccuracy = 0.0;
        } else {
            this.passAccuracy = passAccuracy;
        }
        if (tacklesWon < 0) {
            this.tacklesWon = 0;
        } else {
            this.tacklesWon = tacklesWon;
        }
        if (gamesPlayed < 0 || gamesPlayed > 38) {
            this.gamesPlayed = 0;
        } else {
            this.gamesPlayed = gamesPlayed;
        }
        if (averageMatchRating < 0.0 || averageMatchRating > 10.0) {
            this.averageMatchRating = 0.0;
        } else {
            this.averageMatchRating = averageMatchRating;
        }
        this.scoutingScore = 0.0;
    }

    public abstract double calcScoutingScore();

    public String getName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }

    public String getTeam() {
        return team;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public int getAssists() {
        return assists;
    }

    public int getShotsOnTarget() {
        return shotsOnTarget;
    }

    public int getPassesCompleted() {
        return passesCompleted;
    }

    public double getPassAccuracy() {
        return passAccuracy;
    }

    public int getTacklesWon() {
        return tacklesWon;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public double getAverageMatchRating() {
        return averageMatchRating;
    }

    public double getScoutingScore() {
        return scoutingScore;
    }

    public void setScoutingScore(double score) {
        if (score < 0) {
            scoutingScore = 0;
        } else {
            scoutingScore = score;
        }
    }

    public String toString() {
        return String.format(" Name: %s \n Age: %d \n Position: %s  \n Team: %s \n Goals: %d \n Assists: %d \n Shots on Target: %d \n Passes Completed: %d \n Pass Accuracy: %.1f \n Tackles Won: %d \n Games Played: %d \n Avg Match Rating: %.1f", fullName, age, position, team, goalsScored, assists, shotsOnTarget, passesCompleted, passAccuracy, tacklesWon, gamesPlayed, averageMatchRating);
    }
}
