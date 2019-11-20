package com.example.bettingapp.Moduls;

import com.google.firebase.Timestamp;

import java.text.SimpleDateFormat;
import java.util.Date;


public class match {

    String TeamA;


    String TeamB;
    String League;
    String Time;
    String cote;
    String expectation;
    Timestamp timestamp;
    int Statue;
    String Image;
    String Day;

    public match() {
    }

    public match(String teamA, String teamB, String league, String time, String cote, String expectation, Timestamp timestamp, int statue, String image, String day) {
        TeamA = teamA;
        TeamB = teamB;
        League = league;
        Time = time;
        this.cote = cote;
        this.expectation = expectation;
        this.timestamp = timestamp;
        Statue = statue;
        Image = image;
        Day = day;
    }

    public String getTeamA() {
        return TeamA;
    }

    public void setTeamA(String teamA) {
        TeamA = teamA;
    }

    public String getTeamB() {
        return TeamB;
    }

    public void setTeamB(String teamB) {
        TeamB = teamB;
    }

    public String getLeague() {
        return League;
    }

    public void setLeague(String league) {
        League = league;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatue() {
        return Statue;
    }

    public void setStatue(int statue) {
        Statue = statue;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }
}
