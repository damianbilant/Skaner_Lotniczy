package com.example.demo;

public class Samolot {
    public String nazwa;
    public Integer liczbaMiejsc;
    public Double maxWagaBagazu;

    public Samolot(String nazwa, Integer liczbaMiejsc, Double maxWagaBagazu) {
        this.nazwa = nazwa;
        this.liczbaMiejsc = liczbaMiejsc;
        this.maxWagaBagazu = maxWagaBagazu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Integer getLiczbaMiejsc() {
        return liczbaMiejsc;
    }

    public void setLiczbaMiejsc(Integer liczbaMiejsc) {
        this.liczbaMiejsc = liczbaMiejsc;
    }

    public Double getMaxWagaBagazu() {
        return maxWagaBagazu;
    }

    public void setMaxWagaBagazu(Double maxWagaBagazu) {
        this.maxWagaBagazu = maxWagaBagazu;
    }
}
