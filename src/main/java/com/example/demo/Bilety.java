package com.example.demo;

import javax.swing.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bilety {
    List<String> oblugiwaneKierunki = new ArrayList<>();

    public List<String> dodajKierunki() {
        oblugiwaneKierunki.add("Polska");
        oblugiwaneKierunki.add("USA");
        return oblugiwaneKierunki;
    }

    List<String> nieobslugiwaneChwilowoKierunki = new ArrayList<>();

    public List<String> dodajZakazaneKierunki() {
        nieobslugiwaneChwilowoKierunki.add("Kanada");
        nieobslugiwaneChwilowoKierunki.add("Rosja");
        nieobslugiwaneChwilowoKierunki.add("Chiny");
        nieobslugiwaneChwilowoKierunki.add("RPA");
        return nieobslugiwaneChwilowoKierunki;


    }

    public String podajImie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Proszę podać imię");
        String imie = scanner.nextLine();
        return imie;
    }

    public void podajCelPodrozy(String imie,List<String> listaPasazerow) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Proszę podać cel podróży");
        String cel = scanner.nextLine();

        if (oblugiwaneKierunki.contains(cel)) {
            System.out.println("Lecimy do tego kraju");
            System.out.println("Proszę podać wagę bagażu:");
            Double waga = scanner.nextDouble();
            dodajPasazera(imie);
            dodajWageBagazu(waga);
            czyDodacPasazera(listaPasazerow);



        } else if (nieobslugiwaneChwilowoKierunki.contains(cel)) {
            System.out.println("Chwilowo nie lecimy do tego kraju");
        } else {
            System.out.println("Nie lecimy do tego kraju");
        }

    }


    List<String> listaPasazerow = new ArrayList<>();

    public List<String> dodajPasazera(String imie) {
        listaPasazerow.add(imie);
        return listaPasazerow;

    }

    public void wypiszPasazerow(List<String> listaPasazerow) {
        System.out.println("Lista pasażerów to:");
        for (String pasazer : listaPasazerow) {
            System.out.println(pasazer);
        }


    }

    List<Double> listaWagBagazy = new ArrayList<>();

    public List<Double> dodajWageBagazu (Double waga) {
        listaWagBagazy.add(waga);
        return listaWagBagazy;
    }

    public void wypiszWagiBagazy(List<Double> listaWagBagazy){
        System.out.println("Lista wag bagaży to:");
        for (Double bagaz : listaWagBagazy) {
            System.out.println(bagaz);

        }
    }

    public void sumaBagazy(List<Double> listaWagBagazy){
        System.out.println("Suma wag bagaży to:");
        Double suma = 0.0;
        for (Double wagaBagazu : listaWagBagazy) {
            suma += wagaBagazu;
        }
        System.out.println(suma);
        System.out.println("aaaa");


    }

    public void czyDodacPasazera(List<String> listaPasazerow){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Czy chcesz dodać kolejnego pasażera?");
        String odpowiedz = scanner.nextLine();

        if (odpowiedz.equals("tak")){
            podajCelPodrozy(podajImie(),listaPasazerow);

        } else if (odpowiedz.equals("nie")) {
            wypiszPasazerow(listaPasazerow);
            wypiszWagiBagazy(listaWagBagazy);
            sumaBagazy(listaWagBagazy);
        } else {
            System.out.println("Błędna komenda");
            czyDodacPasazera(listaPasazerow);
        }
    }
}