package com.example.demo;

import java.util.List;

public class Kierunek {
    private String destynacja;
    private Status status;
    private Samolot samolot;

    public Kierunek(String destynacja, Status status, Samolot samolot) {
        this.destynacja = destynacja;
        this.status = status;
        this.samolot = samolot;
    }

    public String getDestynacja() {
        return destynacja;
    }

    public void setDestynacja(String destynacja) {
        this.destynacja = destynacja;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Samolot getSamolot() {
        return samolot;
    }

    public void setSamolot(Samolot samolot) {
        this.samolot = samolot;
    }
}


