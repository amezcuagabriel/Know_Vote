package com.example.gabe.politicianspulse;

public class Refs {
    private String refTitle;
    private String refSub;
    private String refUrl;

    public Refs(String refTitle, String refSub, String refUrl) {
        this.refTitle = refTitle;
        this.refSub = refSub;
        this.refUrl = refUrl;
    }

    public Refs(String referendumTitle, String referendumSubtitle) {
        this.refTitle = referendumTitle;
        this.refSub = referendumSubtitle;
    }

    public String getRefTitle() {
        return refTitle;
    }

    public void setRefTitle(String refTitle) {
        this.refTitle = refTitle;
    }

    public String getRefSub() {
        return refSub;
    }

    public void setRefSub(String refSub) {
        this.refSub = refSub;
    }

    public String getRefUrl() {
        return refUrl;
    }

    public void setRefUrl(String refUrl) {
        this.refUrl = refUrl;
    }
}
