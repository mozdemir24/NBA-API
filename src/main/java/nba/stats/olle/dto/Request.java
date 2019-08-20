package nba.stats.olle.dto;

public class Request {

    private String date;

    public Request() {
    }

    public Request(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
