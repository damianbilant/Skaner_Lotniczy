package com.example.demo;

import org.springframework.stereotype.Service;

@Service

public class KierunekSerwis {

    private final SamolotSerwis samolotSerwis = new SamolotSerwis();
    Kierunek kierunek1 = new Kierunek("Polska", Status.OBSLUGIWANY, samolotSerwis.samolotA);
    Kierunek kierunek2 = new Kierunek("USA", Status.OBSLUGIWANY, samolotSerwis.samolotB);
    Kierunek kierunek3 = new Kierunek("Chiny", Status.NIEOBSLUGIWANY, samolotSerwis.samolotC);
    Kierunek kierunek4 = new Kierunek("Japonia", Status.ZAWIESZONY, samolotSerwis.samolotC);
    Kierunek kierunek5 = new Kierunek("RPA", Status.NIEOBSLUGIWANY, samolotSerwis.samolotB);
}
