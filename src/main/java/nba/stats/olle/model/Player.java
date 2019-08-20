package nba.stats.olle.model;

public class Player {

    private String teamAbbreviation;
    private String firstName;
    private String lastName;
    private int points;
    private int assists;
    private int steals;
    private int blocks;
    private int rebounds;

    public Player(String teamAbbreviation, String firstName, String lastName, int points, int assists, int steals, int blocks, int rebounds) {
        this.teamAbbreviation = teamAbbreviation;
        this.firstName = firstName;
        this.lastName = lastName;
        this.points = points;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
        this.rebounds = rebounds;
    }

    public String getTeamAbbreviation() {
        return teamAbbreviation;
    }

    public void setTeamAbbreviation(String teamAbbreviation) {
        this.teamAbbreviation = teamAbbreviation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getSteals() {
        return steals;
    }

    public void setSteals(int steals) {
        this.steals = steals;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public int getRebounds() {
        return rebounds;
    }

    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
    }
}
