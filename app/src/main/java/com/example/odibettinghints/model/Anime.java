package com.example.odibettinghints.model;

public class Anime {

    private int match_start_time;
    private String home_team;
    private  String away_team;
    private String competition_cluster;
    private String competition_name;
    private int predictions;
    private float odds;
    private  float home_strength;
    private float away_strength;


    public int getMatch_start_time() {
        return match_start_time;
    }

    public void setMatch_start_time(int match_start_time) {
        this.match_start_time = match_start_time;
    }

    public String getHome_team() {
        return home_team;
    }

    public void setHome_team(String home_team) {
        this.home_team = home_team;
    }

    public String getAway_team() {
        return away_team;
    }

    public void setAway_team(String away_team) {
        this.away_team = away_team;
    }

    public String getCompetition_cluster() {
        return competition_cluster;
    }

    public void setCompetition_cluster(String competition_cluster) {
        this.competition_cluster = competition_cluster;
    }

    public String getCompetition_name() {
        return competition_name;
    }

    public void setCompetition_name(String competition_name) {
        this.competition_name = competition_name;
    }

    public int getPredictions() {
        return predictions;
    }

    public void setPredictions(int predictions) {
        this.predictions = predictions;
    }

    public float getOdds() {
        return odds;
    }

    public void setOdds(float odds) {
        this.odds = odds;
    }

    public float getHome_strength() {
        return home_strength;
    }

    public void setHome_strength(float home_strength) {
        this.home_strength = home_strength;
    }

    public float getAway_strength() {
        return away_strength;
    }

    public void setAway_strength(float away_strength) {
        this.away_strength = away_strength;
    }

    public Anime() {



    }
}
