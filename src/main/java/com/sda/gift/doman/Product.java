package com.sda.gift.doman;

/**
 * Created by Allen on 2017/8/24.
 */
public class Product {
    private String guid;
    private String proName;
    private String proId;
    private String proUrl;
    private String proDescription;
    private String proPrice;
    private int avaliabe;

    public Product(String guid, String proName, String proId, String proUrl, String proDescription, String proPrice, int avaliabe) {
        this.guid = guid;
        this.proName = proName;
        this.proId = proId;
        this.proUrl = proUrl;
        this.proDescription = proDescription;
        this.proPrice = proPrice;
        this.avaliabe = avaliabe;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProUrl() {
        return proUrl;
    }

    public void setProUrl(String proUrl) {
        this.proUrl = proUrl;
    }

    public String getProDescription() {
        return proDescription;
    }

    public void setProDescription(String proDescription) {
        this.proDescription = proDescription;
    }

    public String getProPrice() {
        return proPrice;
    }

    public void setProPrice(String proPrice) {
        this.proPrice = proPrice;
    }

    public int getAvaliabe() {
        return avaliabe;
    }

    public void setAvaliabe(int avaliabe) {
        this.avaliabe = avaliabe;
    }
}
