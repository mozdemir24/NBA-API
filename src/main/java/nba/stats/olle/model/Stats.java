package nba.stats.olle.model;

import java.util.List;

public class Stats {

    private String homeTeamName;
    private String visitorTeamName;
    private List<Player> ddPlayers;

    public Stats(String homeTeamName, String visitorTeamName, List<Player> ddPlayers) {
        this.homeTeamName = homeTeamName;
        this.visitorTeamName = visitorTeamName;
        this.ddPlayers = ddPlayers;
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

    public List<Player> getDdPlayers() {
        return ddPlayers;
    }

    public void setDdPlayers(List<Player> ddPlayers) {
        this.ddPlayers = ddPlayers;
    }
}
