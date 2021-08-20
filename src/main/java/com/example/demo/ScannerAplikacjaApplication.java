package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ScannerAplikacjaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScannerAplikacjaApplication.class, args);

		Bilety bilety = new Bilety();
		bilety.dodajKierunki();
		bilety.dodajZakazaneKierunki();
		String imie = bilety.podajImie();
		bilety.podajCelPodrozy(imie,bilety.listaPasazerow);
		bilety.czyDodacPasazera(bilety.listaPasazerow);







	}

}
