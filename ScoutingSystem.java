import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ScoutingSystem {

    private ArrayList<Player> players;
    private String fileName;
    private Scanner scan;

    public ScoutingSystem() {
        players = new ArrayList<>();
        scan = new Scanner(System.in);
        File sessionFile = new File("newPlayers.csv");
        if (sessionFile.exists()) {
            System.out.println("A saved session was found. \n");
            System.out.print("Load saved session? (yes/no): ");
            String choice = scan.nextLine().trim();
            System.out.println();
            while (!choice.equalsIgnoreCase("yes") && !choice.equalsIgnoreCase("no")) {
                System.out.println("Invalid input. Please enter yes or no.");
                System.out.print("Load saved session? (yes/no): ");
                choice = scan.nextLine().trim();
                System.out.println();
            }
            if (choice.equalsIgnoreCase("yes")) {
                fileName = "newPlayers.csv";
            } else {
                fileName = "players.csv";
            }
        } else {
            fileName = "players.csv";
        }
    }

    public void runSystem() {
        loadFile();
        int choice = 0;
        do {
            choice = displayMenu();
            processMenuSelection(choice);
        } while (choice != 99);
        scan.close();
    }

    public void loadFile() {
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] fields = line.split(",");
                String fullName = fields[0].trim();
                int age = Integer.parseInt(fields[1].trim());
                String position = fields[2].trim();
                String team = fields[3].trim();
                int goals = Integer.parseInt(fields[4].trim());
                int assists = Integer.parseInt(fields[5].trim());
                int shots = Integer.parseInt(fields[6].trim());
                int passes = Integer.parseInt(fields[7].trim());
                double passAcc = Double.parseDouble(fields[8].trim());
                int tackles = Integer.parseInt(fields[9].trim());
                int gamesPlayed = Integer.parseInt(fields[10].trim());
                double rating = Double.parseDouble(fields[11].trim());
                Player p = null;
                if (position.equalsIgnoreCase("GK")) {
                    int saves = Integer.parseInt(fields[12].trim());
                    int cleanSheets = Integer.parseInt(fields[13].trim());
                    int goalsConceded = Integer.parseInt(fields[14].trim());
                    Goalkeeper gk = new Goalkeeper(fullName, age, team, goals, assists, shots, passes, passAcc, tackles,
                            gamesPlayed, rating, saves, cleanSheets, goalsConceded);
                    p = gk;
                } else if (position.equalsIgnoreCase("DEF")) {
                    Defender def = new Defender(fullName, age, team, goals, assists, shots, passes, passAcc, tackles,
                            gamesPlayed, rating);
                    p = def;
                } else if (position.equalsIgnoreCase("MID")) {
                    Midfielder mid = new Midfielder(fullName, age, team, goals, assists, shots, passes, passAcc,
                            tackles, gamesPlayed, rating);
                    p = mid;
                } else if (position.equalsIgnoreCase("FWD")) {
                    Forward fwd = new Forward(fullName, age, team, goals, assists, shots, passes, passAcc, tackles,
                            gamesPlayed, rating);
                    p = fwd;
                } else {
                    continue;
                }
                players.add(p);
            }
            fileScanner.close();
            System.out.println("Loaded " + players.size() + " players from " + fileName);
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("Error " + fileName + " not found.");
            System.out.println(e.getMessage());
            System.out.println();
            return;
        }
    }

    public int displayMenu() {
        String message = """
                
               =============================
                Soccer Scouting System Menu
               =============================

                (1)  Display Dataset Summary
                (2)  Add a New Player
                (3)  Remove a Player
                (4)  Age Group Analysis
                (5)  Display Top 10 Players for a position by Scouting Score
                (6)  Data Report for a Single Player
                (7)  Save Current Session
                (8)  Export Scouting Report
                (99) Quit
                Enter your choice:
                """;
        System.out.print(message);
        int choice = -1;
        boolean valid = false;
        while (!valid) {
            String choiceStr = scan.nextLine().trim();
            try {
                choice = Integer.parseInt(choiceStr);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print("Enter your choice: ");
            }
        }
        return choice;
    }

    public void processMenuSelection(int choice) {
        switch (choice) {
            case 1:
                displayDatasetSummary();
                break;
            case 2:
                addPlayer();
                break;
            case 3:
                removePlayer();
                break;
            case 4:
                ageGroupAnalysis();
                break;
            case 5:
                displayTopPlayers();
                break;
            case 6:
                displayPlayerReport();
                break;
            case 7:
                saveSession();
                break;
            case 8:
                exportScoutingReport();
                break;
            case 99:
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Invalid selection.");
        }
    }

    public void displayDatasetSummary() {
        int total = players.size();
        int gk = 0;
        int def = 0;
        int mid = 0;
        int fwd = 0;
        int age1823 = 0;
        int age2429 = 0;
        int age3035 = 0;
        int age3640 = 0;
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            if (p.getPosition().equalsIgnoreCase("GK")) {
                gk++;
            } else if (p.getPosition().equalsIgnoreCase("DEF")) {
                def++;
            } else if (p.getPosition().equalsIgnoreCase("MID")) {
                mid++;
            } else if (p.getPosition().equalsIgnoreCase("FWD")) {
                fwd++;
            }
            int age = p.getAge();
            if (age >= 18 && age <= 23) {
                age1823++;
            } else if (age >= 24 && age <= 29) {
                age2429++;
            } else if (age >= 30 && age <= 35) {
                age3035++;
            } else if (age >= 36 && age <= 40) {
                age3640++;
            }
        }
        System.out.println("\n -- Dataset Summary -- \n\n Total Players:    " + total + "\n Goalkeepers (GK): " + gk + "\n Defenders (DEF):  " + def + "\n Midfielders (MID): " + mid + "\n Forwards (FWD):   " + fwd + "\n\n Age 18-23: " + age1823 + "\n Age 24-29: " + age2429 + "\n Age 30-35: " + age3035 + "\n Age 36-40: " + age3640 + "\n\n");
    }

    public void addPlayer() {
        System.out.print("Enter the player's full name: ");
        String fullName = scan.nextLine().trim();
        while (!validateStringInput(fullName)) {
            System.out.println("Invalid name.");
            System.out.print("Enter the player's full name: ");
            fullName = scan.nextLine().trim();
        }

        Player existing = findPlayer(fullName);
        if (existing != null) {
            System.out.println("Player is already on the roster.");
            return;
        }

        int age = 0;
        System.out.print("Enter the player's age (18-40): ");
        String ageInput = scan.nextLine().trim();
        if (!ageInput.isEmpty()) {
            age = Integer.parseInt(ageInput);
        }
        while (ageInput.isEmpty() || age < 18 || age > 40) {
            if (ageInput.isEmpty()) {
                System.out.println("Input cannot be empty.");
            } else {
                System.out.println("Invalid age. Must be between 18 and 40.");
            }
            System.out.print("Enter the player's age (18-40): ");
            ageInput = scan.nextLine().trim();
            if (!ageInput.isEmpty()) {
                age = Integer.parseInt(ageInput);
            }
        }

        System.out.print("Enter the player's position (GK/DEF/MID/FWD): ");
        String position = scan.nextLine().trim();
        while (position.isEmpty() || (!position.equalsIgnoreCase("GK") && !position.equalsIgnoreCase("DEF") && !position.equalsIgnoreCase("MID") && !position.equalsIgnoreCase("FWD"))) {
            if (position.isEmpty()) {
                System.out.println("Position cannot be empty.");
            } else {
                System.out.println("Invalid position. Must be GK, DEF, MID or FWD.");
            }
            System.out.print("Enter the player's position (GK/DEF/MID/FWD): ");
            position = scan.nextLine().trim();
        }

        String[] validTeams = { "Atalanta", "Bologna", "Cagliari", "Como", "Empoli", "Genoa", "Verona", "Lazio", "Lecce", "Monza", "Parma", "Torino", "Udinese", "Venezia", "Inter", "Juventus", "Milan", "Napoli", "Roma", "Fiorentina" };
        System.out.print("Enter the player's team: ");
        String team = scan.nextLine().trim();
        boolean validTeam = false;
        for (int i = 0; i < validTeams.length; i++) {
            if (validTeams[i].equalsIgnoreCase(team)) {
                validTeam = true;
                break;
            }
        }
        while (team.isEmpty() || !validTeam) {
            if (team.isEmpty()) {
                System.out.println("Input cannot be empty.");
            } else {
                System.out.println("Invalid team. Must be one of the 20 Serie A teams.");
            }
            System.out.print("Enter the player's team: ");
            team = scan.nextLine().trim();
            validTeam = false;
            for (int i = 0; i < validTeams.length; i++) {
                if (validTeams[i].equalsIgnoreCase(team)) {
                    validTeam = true;
                    break;
                }
            }
        }

        int goals = 0;
        System.out.print("Enter goals scored: ");
        String goalsInput = scan.nextLine().trim();
        if (!goalsInput.isEmpty()) {
            goals = Integer.parseInt(goalsInput);
        }
        while (goalsInput.isEmpty() || goals < 0) {
            if (goalsInput.isEmpty()) {
                System.out.println("Input cannot be empty.");
            } else {
                System.out.println("Invalid goals scored. Must be >= 0.");
            }
            System.out.print("Enter goals scored: ");
            goalsInput = scan.nextLine().trim();
            if (!goalsInput.isEmpty()) {
                goals = Integer.parseInt(goalsInput);
            }
        }

        int assists = 0;
        System.out.print("Enter assists: ");
        String assistsInput = scan.nextLine().trim();
        if (!assistsInput.isEmpty()) {
            assists = Integer.parseInt(assistsInput);
        }
        while (assistsInput.isEmpty() || assists < 0) {
            if (assistsInput.isEmpty()) {
                System.out.println("Input cannot be empty.");
            } else {
                System.out.println("Invalid assists. Must be >= 0.");
            }
            System.out.print("Enter assists: ");
            assistsInput = scan.nextLine().trim();
            if (!assistsInput.isEmpty()) {
                assists = Integer.parseInt(assistsInput);
            }
        }

        int shots = 0;
        System.out.print("Enter shots on target: ");
        String shotsInput = scan.nextLine().trim();
        if (!shotsInput.isEmpty()) {
            shots = Integer.parseInt(shotsInput);
        }
        while (shotsInput.isEmpty() || shots < 0) {
            if (shotsInput.isEmpty()) {
                System.out.println("Input cannot be empty.");
            } else {
                System.out.println("Invalid shots on target. Must be >= 0.");
            }
            System.out.print("Enter shots on target: ");
            shotsInput = scan.nextLine().trim();
            if (!shotsInput.isEmpty()) {
                shots = Integer.parseInt(shotsInput);
            }
        }

        int passes = 0;
        System.out.print("Enter passes completed: ");
        String passesInput = scan.nextLine().trim();
        if (!passesInput.isEmpty()) {
            passes = Integer.parseInt(passesInput);
        }
        while (passesInput.isEmpty() || passes < 0) {
            if (passesInput.isEmpty()) {
                System.out.println("Input cannot be empty.");
            } else {
                System.out.println("Invalid passes completed. Must be >= 0.");
            }
            System.out.print("Enter passes completed: ");
            passesInput = scan.nextLine().trim();
            if (!passesInput.isEmpty()) {
                passes = Integer.parseInt(passesInput);
            }
        }

        double passAcc = 0.0;
        System.out.print("Enter pass accuracy (0.0 - 100.0): ");
        String passAccInput = scan.nextLine().trim();
        if (!passAccInput.isEmpty()) {
            passAcc = Double.parseDouble(passAccInput);
        }
        while (passAccInput.isEmpty() || passAcc < 0.0 || passAcc > 100.0) {
            if (passAccInput.isEmpty()) {
                System.out.println("Input cannot be empty.");
            } else {
                System.out.println("Invalid pass accuracy. Must be between 0.0 and 100.0.");
            }
            System.out.print("Enter pass accuracy (0.0 - 100.0): ");
            passAccInput = scan.nextLine().trim();
            if (!passAccInput.isEmpty()) {
                passAcc = Double.parseDouble(passAccInput);
            }
        }

        int tackles = 0;
        System.out.print("Enter tackles won: ");
        String tacklesInput = scan.nextLine().trim();
        if (!tacklesInput.isEmpty()) {
            tackles = Integer.parseInt(tacklesInput);
        }
        while (tacklesInput.isEmpty() || tackles < 0) {
            if (tacklesInput.isEmpty()) {
                System.out.println("Input cannot be empty.");
            } else {
                System.out.println("Invalid tackles won. Must be >= 0.");
            }
            System.out.print("Enter tackles won: ");
            tacklesInput = scan.nextLine().trim();
            if (!tacklesInput.isEmpty()) {
                tackles = Integer.parseInt(tacklesInput);
            }
        }

        int games = 0;
        System.out.print("Enter games played (0-38): ");
        String gamesInput = scan.nextLine().trim();
        if (!gamesInput.isEmpty()) {
            games = Integer.parseInt(gamesInput);
        }
        while (gamesInput.isEmpty() || games < 0 || games > 38) {
            if (gamesInput.isEmpty()) {
                System.out.println("Input cannot be empty.");
            } else {
                System.out.println("Invalid games played. Must be between 0 and 38.");
            }
            System.out.print("Enter games played (0-38): ");
            gamesInput = scan.nextLine().trim();
            if (!gamesInput.isEmpty()) {
                games = Integer.parseInt(gamesInput);
            }
        }

        double rating = 0.0;
        System.out.print("Enter average match rating (0.0 - 10.0): ");
        String ratingInput = scan.nextLine().trim();
        if (!ratingInput.isEmpty()) {
            rating = Double.parseDouble(ratingInput);
        }
        while (ratingInput.isEmpty() || rating < 0.0 || rating > 10.0) {
            if (ratingInput.isEmpty()) {
                System.out.println("Input cannot be empty.");
            } else {
                System.out.println("Invalid average match rating. Must be between 0.0 and 10.0.");
            }
            System.out.print("Enter average match rating (0.0 - 10.0): ");
            ratingInput = scan.nextLine().trim();
            if (!ratingInput.isEmpty()) {
                rating = Double.parseDouble(ratingInput);
            }
        }

        Player p = null;
        if (position.equalsIgnoreCase("GK")) {
            int saves = 0;
            System.out.print("Enter saves: ");
            String savesInput = scan.nextLine().trim();
            if (!savesInput.isEmpty()) {
                saves = Integer.parseInt(savesInput);
            }
            while (savesInput.isEmpty() || saves < 0) {
                if (savesInput.isEmpty()) {
                    System.out.println("Input cannot be empty.");
                } else {
                    System.out.println("Invalid saves. Must be >= 0.");
                }
                System.out.print("Enter saves: ");
                savesInput = scan.nextLine().trim();
                if (!savesInput.isEmpty()) {
                    saves = Integer.parseInt(savesInput);
                }
            }

            int cleanSheets = 0;
            System.out.print("Enter clean sheets: ");
            String cleanSheetsInput = scan.nextLine().trim();
            if (!cleanSheetsInput.isEmpty()) {
                cleanSheets = Integer.parseInt(cleanSheetsInput);
            }
            while (cleanSheetsInput.isEmpty() || cleanSheets < 0) {
                if (cleanSheetsInput.isEmpty()) {
                    System.out.println("Input cannot be empty.");
                } else {
                    System.out.println("Invalid clean sheets. Must be >= 0.");
                }
                System.out.print("Enter clean sheets: ");
                cleanSheetsInput = scan.nextLine().trim();
                if (!cleanSheetsInput.isEmpty()) {
                    cleanSheets = Integer.parseInt(cleanSheetsInput);
                }
            }

            int goalsConceded = 0;
            System.out.print("Enter goals conceded: ");
            String goalsConcededInput = scan.nextLine().trim();
            if (!goalsConcededInput.isEmpty()) {
                goalsConceded = Integer.parseInt(goalsConcededInput);
            }
            while (goalsConcededInput.isEmpty() || goalsConceded < 0) {
                if (goalsConcededInput.isEmpty()) {
                    System.out.println("Input cannot be empty.");
                } else {
                    System.out.println("Invalid goals conceded. Must be >= 0.");
                }
                System.out.print("Enter goals conceded: ");
                goalsConcededInput = scan.nextLine().trim();
                if (!goalsConcededInput.isEmpty()) {
                    goalsConceded = Integer.parseInt(goalsConcededInput);
                }
            }
            p = new Goalkeeper(fullName, age, team, goals, assists, shots, passes, passAcc, tackles, games, rating, saves, cleanSheets, goalsConceded);
        } else if (position.equalsIgnoreCase("DEF")) {
            p = new Defender(fullName, age, team, goals, assists, shots, passes, passAcc, tackles, games, rating);
        } else if (position.equalsIgnoreCase("MID")) {
            p = new Midfielder(fullName, age, team, goals, assists, shots, passes, passAcc, tackles, games, rating);
        } else if (position.equalsIgnoreCase("FWD")) {
            p = new Forward(fullName, age, team, goals, assists, shots, passes, passAcc, tackles, games, rating);
        }
        players.add(p);
        System.out.println(fullName + " has been added to the league.");
    }

    public void removePlayer() {
        System.out.print("Enter the full name of the player to remove: ");
        String name = scan.nextLine().trim();
        Player p = null;
        if (!name.isEmpty()) {
            p = findPlayer(name);
        }
        while (name.isEmpty() || p == null) {
            if (name.isEmpty()) {
                System.out.println("Input cannot be empty.");
            } else {
                System.out.println("Player does not exist.");
            }
            System.out.print("Enter the full name of the player to remove: ");
            name = scan.nextLine().trim();
            if (!name.isEmpty()) {
                p = findPlayer(name);
            }
        }
        players.remove(p);
        System.out.println(name + " has been successfully removed from the league.");
    }

    public void ageGroupAnalysis() {
        int age = 0;
        boolean valid = false;
        System.out.print("Enter age (18-40): ");
        while (!valid) {
            String ageInput = scan.nextLine().trim();
            if (ageInput.isEmpty()) {
                System.out.println("Input cannot be empty.");
                System.out.print("Enter age (18-40): ");
            } else {
                try {
                    age = Integer.parseInt(ageInput);
                    if (age < 18 || age > 40) {
                        System.out.println("Invalid age. Must be between 18 and 40.");
                        System.out.print("Enter age (18-40): ");
                    } else {
                        valid = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    System.out.print("Enter age (18-40): ");
                }
            }
        }

        int total = 0, gk = 0, def = 0, mid = 0, fwd = 0;
        double totalGames = 0;
        double totalRating = 0;
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            if (p.getAge() == age) {
                total++;
                if (p.getPosition().equalsIgnoreCase("GK")) {
                    gk++;
                } else if (p.getPosition().equalsIgnoreCase("DEF")) {
                    def++;
                } else if (p.getPosition().equalsIgnoreCase("MID")) {
                    mid++;
                } else if (p.getPosition().equalsIgnoreCase("FWD")) {
                    fwd++;
                }
                totalGames = totalGames + p.getGamesPlayed();
                totalRating = totalRating + p.getAverageMatchRating();
            }
        }
        if (total == 0) {
            System.out.println("No players found at age " + age);
            return;
        }
        double agp = totalGames / total;
        double aamr = totalRating / total;
        System.out.printf("\n -- Age Group Analysis -- \n" + "\n  Age: " + age + "\n  Total: " + total + "\n  GK: " + gk + "\n  DEF: " + def + "\n  MID: " + mid + "\n  FWD: " + fwd + "\n  Avg Games Played: %.2f \n  Avg Match Rating: %.2f \n\n", agp, aamr);
    }

    public void displayTopPlayers() {
        System.out.print("Enter position (GK/DEF/MID/FWD): ");
        String position = scan.nextLine().trim();
        while (position.isEmpty() || (!position.equalsIgnoreCase("GK") && !position.equalsIgnoreCase("DEF")
                && !position.equalsIgnoreCase("MID") && !position.equalsIgnoreCase("FWD"))) {
            if (position.isEmpty()) {
                System.out.println("Input cannot be empty.");
            } else {
                System.out.println("Invalid position. Enter GK, DEF, MID or FWD.");
            }
            System.out.print("Enter position (GK/DEF/MID/FWD): ");
            position = scan.nextLine().trim();
        }
        ArrayList<Player> filteredList = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            if (p.getPosition().equalsIgnoreCase(position)) {
                filteredList.add(p);
            }
        }
        sortByScoutingScore(filteredList);
        TopPlayersReport report = new TopPlayersReport(filteredList);
        report.generate();
    }

    public void displayPlayerReport() {
        System.out.print("Enter player full name: ");
        String name = scan.nextLine().trim();
        Player p = null;
        if (!name.isEmpty()) {
            p = findPlayer(name);
        }
        while (name.isEmpty() || p == null) {
            if (name.isEmpty()) {
                System.out.println("Input cannot be empty.");
            } else {
                System.out.println("Player not found, enter the name again.");
            }
            System.out.print("Enter player full name: ");
            name = scan.nextLine().trim();
            if (!name.isEmpty()) {
                p = findPlayer(name);
            }
        }
        ArrayList<Player> list = new ArrayList<>();
        list.add(p);
        PlayerReport report = new PlayerReport(list);
        report.generate();
    }

    public void saveSession() {
        try {
            PrintWriter pw = new PrintWriter("newPlayers.csv");
            for (int i = 0; i < players.size(); i++) {
                Player p = players.get(i);
                pw.printf("%s,%d,%s,%s,%d,%d,%d,%d,%.2f,%d,%d,%.2f", p.getName(), p.getAge(), p.getPosition(),
                        p.getTeam(), p.getGoalsScored(), p.getAssists(), p.getShotsOnTarget(), p.getPassesCompleted(),
                        p.getPassAccuracy(), p.getTacklesWon(), p.getGamesPlayed(), p.getAverageMatchRating());
                if (p instanceof Goalkeeper) {
                    Goalkeeper gk = (Goalkeeper) p;
                    pw.println("," + gk.getSaves() + "," + gk.getCleanSheets() + "," + gk.getGoalsConceded());
                } else {
                    pw.println();
                }
            }
            pw.close();
            System.out.println("Session saved to newPlayers.csv");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save session.");
        }
    }

    public void exportScoutingReport() {
        ExportReport report = new ExportReport(players);
        report.generate();
    }

    public boolean validateStringInput(String input) {
        if (input == null || input.isBlank()) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }

    public Player findPlayer(String fullName) {
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            if (p.getName().equalsIgnoreCase(fullName)) {
                return p;
            }
        }
        return null;
    }

    public void sortByScoutingScore(ArrayList<Player> players) {
        int size = players.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                Player current = players.get(j);
                Player next = players.get(j + 1);
                double currentScore = current.calcScoutingScore();
                double nextScore = next.calcScoutingScore();
                if (currentScore < nextScore) {
                    players.set(j, next);
                    players.set(j + 1, current);
                }
            }
        }
    }

}