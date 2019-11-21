package com.example.bettingapp.Moduls;


public class match {

    String team1;
    String team2;
    String League;
    String time;
    String cote;
    String expectation;
    int Statue;
    String Day;

    public match() {
    }

    public match(String teamA, String teamB, String league, String time, String cote, String expectation, int statue, String day) {
        team1 = teamA;
        team2 = teamB;
        League = league;
        this.time = time;
        this.cote = cote;
        this.expectation = expectation;
        Statue = statue;
        Day = day;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getLeague() {
        return League;
    }

    public void setLeague(String league) {
        League = league;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCote() {
        return cote;
    }

    public void setCote(String cote) {
        this.cote = cote;
    }

    public String getExpectation() {
        return expectation;
    }

    public void setExpectation(String expectation) {
        this.expectation = expectation;
    }

    public int getStatue() {
        return Statue;
    }

    public void setStatue(int statue) {
        Statue = statue;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }
}
