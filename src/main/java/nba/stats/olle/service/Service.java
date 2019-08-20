package nba.stats.olle.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nba.stats.olle.Constants;
import nba.stats.olle.model.Game;
import nba.stats.olle.model.Player;
import nba.stats.olle.model.Stats;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@org.springframework.stereotype.Service
public class Service {

    private ObjectMapper mapper;

    public Service(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public List<Stats> getDoubleDouble(String date) {
        List<String> gameIdList = getGameIds(date);
        List<Game> gameList = new ArrayList<>();
        List<Stats> statsList = new ArrayList<>();

        for (String gameId : gameIdList) {
            gameList.add(getGame(gameId, date));
        }

        for (Game game : gameList) {
            Stats stats = getStats(game);
            if (!stats.getDdPlayers().isEmpty()) statsList.add(getStats(game));
        }

        return statsList;
    }

    private List<String> getGameIds(String date) {
        List<String> gameIds = new ArrayList<>();

        try {
            URL uri = new URL(Constants.GAMES_URL.replace("{DATE}", date));

            JsonNode root = mapper.readTree(uri);
            JsonNode games = root.get("sports_content").get("games").get("game");

            for (JsonNode game : games) {
                gameIds.add(game.get("id").textValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return gameIds;
    }

    private Game getGame(String gameId, String date) {
        Game game = new Game();

        try {
            URL uri = new URL(Constants.SCORE_URL.replace("{GAME_ID}", gameId).replace("{DATE}", date));

            JsonNode root = mapper.readTree(uri);

            JsonNode home = root.get("sports_content").get("game").get("home");
            JsonNode visitor = root.get("sports_content").get("game").get("visitor");

            String homeTeamName = String.format("%s %s", home.get("city").textValue(), home.get("nickname").textValue());
            String visitorTeamName = String.format("%s %s", visitor.get("city").textValue(), visitor.get("nickname").textValue());

            String homeTeamAbbreviation = home.get("abbreviation").textValue();
            String visitorTeamAbbreviation = visitor.get("abbreviation").textValue();

            JsonNode homePlayers = home.get("players").get("player");
            JsonNode visitorPlayers = visitor.get("players").get("player");

            List<Player> homePlayerList = new ArrayList<>();
            List<Player> visitorPlayerList = new ArrayList<>();

            getPlayers(homeTeamAbbreviation, homePlayers, homePlayerList);
            getPlayers(visitorTeamAbbreviation, visitorPlayers, visitorPlayerList);

            game.setHomeTeamName(homeTeamName);
            game.setVisitorTeamName(visitorTeamName);
            game.setHomePlayers(homePlayerList);
            game.setVisitorPlayers(visitorPlayerList);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return game;
    }

    private void getPlayers(String teamAbbreviation, JsonNode playerNode, List<Player> playerList) {
        if (playerNode != null) {
            for (JsonNode homePlayer : playerNode) {
                Player player = new Player(
                        teamAbbreviation,
                        homePlayer.get("first_name").textValue(),
                        homePlayer.get("last_name").textValue(),
                        toInt(homePlayer.get("points").textValue()),
                        toInt(homePlayer.get("assists").textValue()),
                        toInt(homePlayer.get("steals").textValue()),
                        toInt(homePlayer.get("blocks").textValue()),
                        toInt(homePlayer.get("rebounds_offensive").textValue()) + toInt(homePlayer.get("rebounds_defensive").textValue())
                );
                playerList.add(player);
            }
        }
    }

    private Stats getStats(Game game) {
        List<Player> allPlayers = Stream.concat(
                game.getHomePlayers().stream(),
                game.getVisitorPlayers().stream()
        ).collect(Collectors.toList());

        List<Player> ddPlayers = new ArrayList<>();

        int doubleDigit = 0;
        for (Player player : allPlayers) {
            if (player.getPoints() >= 10) doubleDigit += 1;
            if (player.getAssists() >= 10) doubleDigit += 1;
            if (player.getBlocks() >= 10) doubleDigit += 1;
            if (player.getRebounds() >= 10) doubleDigit += 1;
            if (player.getSteals() >= 10) doubleDigit += 1;
            if (doubleDigit == 2) ddPlayers.add(player);
            doubleDigit = 0;
        }

        return new Stats(game.getHomeTeamName(), game.getVisitorTeamName(), ddPlayers);
    }

    private Integer toInt(String text) {
        return Integer.valueOf(text);
    }

}
