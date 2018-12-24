package com.example.gabe.politicianspulse;

import android.widget.ImageView;

public class Reps {
    private String officeName;
    private String officialName;
    private String party;
    private String photoUrl;
    private String line1;
    private String city;
    private String state;
    private String zip;
    private String phones;


    //continue to develop this with other values such as phone, photo, address etc.

    public Reps(){

    }

    public Reps(String officeName, String officialName, String party, String photoUrl, String line1, String city, String state, String zip, String phones) {
        this.officeName = officeName;
        this.officialName = officialName;
        this.party = party;
        this.photoUrl = photoUrl;
        this.line1 = line1;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phones = phones;
    }

    public Reps(String officeName, String officialName, String party, String photoUrl) {
        this.officeName = officeName;
        this.officialName = officialName;
        this.party = party;
        this.photoUrl = photoUrl;
    }



    public Reps(String officeName, String officialName, String party) {
        this.officeName = officeName;
        this.officialName = officialName;
        this.party = party;
    }

    public Reps(String officeName) {
        this.officeName = officeName;
    }

    public Reps(String name, String party) {
        this.officialName = name;
        this.party = party;
    }


    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }
}
