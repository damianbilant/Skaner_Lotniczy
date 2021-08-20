package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScannerAplikacjaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScannerAplikacjaApplication.class, args);

		SamolotSerwis samolotSerwis = new SamolotSerwis();
		samolotSerwis.stworzSamoloty();
		LotSerwis lotSerwis = new LotSerwis();
		lotSerwis.dodajKierunki();
		lotSerwis.dodajZakazaneKierunki();

		String imie = lotSerwis.podajImie();
		lotSerwis.podajCelPodrozy(imie, lotSerwis.listaPasazerow);








	}

}
