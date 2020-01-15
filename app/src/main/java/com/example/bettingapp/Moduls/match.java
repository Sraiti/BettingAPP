package com.example.bettingapp.Moduls;


public class match {

public String team1;
String team2;
String League;
String time;
String cote;
String expectation;
String expectation2;
String expectation3;
int Statue;




public match() {
}

public match(String teamA, String teamB, String league, String time, String cote, String expectation, int statue) {
	team1 = teamA;
	team2 = teamB;
	League = league;
	this.time = time;
	this.cote = cote;
	this.expectation = expectation;
	Statue = statue;
	
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

public String getExpectation2() {
	return expectation2;
}

public void setExpectation2(String expectation2) {
	this.expectation2 = expectation2;
}

public String getExpectation3() {
	return expectation3;
}

public void setExpectation3(String expectation3) {
	this.expectation3 = expectation3;
}

public int getStatue() {
	return Statue;
}

public void setStatue(int statue) {
	Statue = statue;
}


}
