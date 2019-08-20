package nba.stats.olle.model;

import java.util.List;

public class Game {

    private String homeTeamName;
    private String visitorTeamName;
    private List<Player> homePlayers;
    private List<Player> visitorPlayers;

    public Game() {
    }

    public Game(String homeTeamName, String visitorTeamName, List<Player> homePlayers, List<Player> visitorPlayers) {
        this.homeTeamName = homeTeamName;
        this.visitorTeamName = visitorTeamName;
        this.homePlayers = homePlayers;
        this.visitorPlayers = visitorPlayers;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getVisitorTeamName() {
        return visitorTeamName;
    }

    public void setVisitorTeamName(String visitorTeamName) {
        this.visitorTeamName = visitorTeamName;
    }

    public List<Player> getHomePlayers() {
        return homePlayers;
    }

    public void setHomePlayers(List<Player> homePlayers) {
        this.homePlayers = homePlayers;
    }

    public List<Player> getVisitorPlayers() {
        return visitorPlayers;
    }

    public void setVisitorPlayers(List<Player> visitorPlayers) {
        this.visitorPlayers = visitorPlayers;
    }
}
