package com.example.demo;

public class Pasazer {
    private String imie;
    private String kierunek;
    private Double wagaBagazu;

    public Pasazer(String imie, String kierunek, Double wagaBagazu) {
        this.imie = imie;
        this.kierunek = kierunek;
        this.wagaBagazu = wagaBagazu;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getKierunek() {
        return kierunek;
    }

    public void setKierunek(String kierunek) {
        this.kierunek = kierunek;
    }

    public Double getWagaBagazu() {
        return wagaBagazu;
    }

    public void setWagaBagazu(Double wagaBagazu) {
        this.wagaBagazu = wagaBagazu;
    }
}
