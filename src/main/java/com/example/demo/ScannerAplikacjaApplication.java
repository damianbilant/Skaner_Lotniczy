package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;

@SpringBootApplication
public class ScannerAplikacjaApplication {

    public static void main(String[] args) throws ObslugaBledow {
        SpringApplication.run(ScannerAplikacjaApplication.class, args);

        LotSerwis lotSerwis = new LotSerwis();

        lotSerwis.konfiguracja();


        lotSerwis.start(lotSerwis.podajImie(), lotSerwis.podajCelPodrozy(), lotSerwis.podajWageBagazu());

        //lotSerwis.sprawdzenie();

        //lotSerwis.kierunek1.getSamolot().getLiczbaMiejsc();


        //lotSerwis.dodajKierunki();
        //lotSerwis.dodajZakazaneKierunki();

//		String imie = lotSerwis.podajImie();
//		lotSerwis.podajCelPodrozy(imie, lotSerwis.listaPasazerow);


    }

}
