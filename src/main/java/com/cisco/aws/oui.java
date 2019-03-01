package com.cisco.aws;

import org.json.JSONObject;

public class oui {
    private String mac;
    private String company;

    public oui() {  }

    public oui(String mac, String company) {
        this.setMac(mac);
        this.setCompany(company);
    }

    public String getMac() {
        return mac;
    }

    public String getCompany() {
        return company;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("name", mac);
        json.put("company", company);
        return json.toString();
    }
}

