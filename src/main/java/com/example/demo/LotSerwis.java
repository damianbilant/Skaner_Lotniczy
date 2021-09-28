package com.example.demo;


import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service



public class LotSerwis {

    /*private final SamolotSerwis samolotSerwis;

    public LotSerwis(SamolotSerwis samolotSerwis) {
        this.samolotSerwis = samolotSerwis;
    }

    SamolotSerwis samolotSerwis2 = new SamolotSerwis();
    samolotSerwis2.*/


    Samolot samolot1 = new Samolot("Dreamliner", 3, 25.5);
    Samolot samolot2 = new Samolot("Airbus", 2, 20.1);
    Samolot samolot3 = new Samolot("Tupolev", 1, 15.4);

    //TODO: zrobić jedną listę i dodać wszystkie kierunki do niej
    List<Pasazer> listaPasazerow = new ArrayList<>();

    List<Kierunek> wszystkieKierunki = new ArrayList<>();




    public List<Kierunek> dodajKierunki() {
        wszystkieKierunki.add(kierunek1);
        wszystkieKierunki.add(kierunek2);
        wszystkieKierunki.add(kierunek3);
        wszystkieKierunki.add(kierunek4);
        wszystkieKierunki.add(kierunek5);

        return wszystkieKierunki;
    }


    Kierunek kierunek1 = new Kierunek("Polska", Status.OBSLUGIWANY, samolot1);
    Kierunek kierunek2 = new Kierunek("USA", Status.OBSLUGIWANY, samolot2);
    Kierunek kierunek3 = new Kierunek("Chiny", Status.NIEOBSLUGIWANY, samolot3);
    Kierunek kierunek4 = new Kierunek("Japonia", Status.ZAWIESZONY, samolot3);
    Kierunek kierunek5 = new Kierunek("RPA", Status.NIEOBSLUGIWANY, samolot2);


    /*public void sprawdzenie() {
        Integer obojetnie = kierunek1.getStatus().getMinPaliwo();
        System.out.println(obojetnie);
        }*/


    public String podajImie() {
        dodajKierunki();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Proszę podać imię");
        String imie = scanner.nextLine();
        return imie;
    }


    public String podajCelPodrozy() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Proszę podać cel podróży");
        String cel = scanner.nextLine();
        return cel;
    }

    public Double podajWageBagazu() {
        System.out.println("Proszę podać wagę bagażu:");
        Scanner scanner = new Scanner(System.in);
        Double waga = scanner.nextDouble();
        return waga;

    }

    public void start(String imie, String cel, Double waga) {
        Pasazer pasazer = new Pasazer(imie, cel, waga);
        Kierunek destynacja = sprawdzKierunek(cel);
        sprawdzStatus(destynacja, pasazer, destynacja.getSamolot());
    }

    public Kierunek sprawdzKierunek(String cel) {
        Kierunek tamGdzielece = null;
        for (Kierunek destynacja : wszystkieKierunki) {
            if (destynacja.getDestynacja().equals(cel)) {
                tamGdzielece = destynacja;
            }
        }
        return tamGdzielece;
    }

    public void sprawdzStatus(Kierunek kierunek, Pasazer pasazer, Samolot samolot) {
        if (kierunek.getStatus().equals(Status.OBSLUGIWANY)) {
            sprawdzWageLiczbeMiejsc(pasazer, samolot);
            czyDodacPasazera();
        } else {
            System.out.println("Obslugujemy tylko kierunki:");
            for (Kierunek destynacja : wszystkieKierunki) {
                if (kierunek.getStatus().equals(Status.OBSLUGIWANY)) {
                    System.out.println(destynacja.getDestynacja());
                }
            }
            System.out.println("Konieczna zmiana kierunku i podanie jeszcze raz danych");
            start(podajImie(),podajCelPodrozy(),podajWageBagazu());
            //przekierować pasażera do wyboru kierunku (wrzucić metodę która pozwala podać znowu cel podróży)
        }
    }


    public Integer liczbaPodroznych(Pasazer pasazer) {
        Integer liczba = 0;
        for (Pasazer podrozny : listaPasazerow) {
            if (podrozny.getKierunek().equals(pasazer.getKierunek())) {
                liczba += 1;

            }
        }
        return liczba;
    }

    public Double wagaBagazy(Pasazer pasazer) {
        Double waga = 0.0;
        for (Pasazer podrozny : listaPasazerow) {
            if (podrozny.getKierunek().equals(pasazer.getKierunek())) {
                waga += podrozny.getWagaBagazu();

            }
        }
        return waga;
    }

    public void sprawdzWageLiczbeMiejsc(Pasazer pasazer, Samolot samolot) {

        Integer jedenKierunek = liczbaPodroznych(pasazer);
        Double jednaWaga = wagaBagazy(pasazer);

        if (jedenKierunek < samolot.getLiczbaMiejsc() && jednaWaga < samolot.getMaxWagaBagazu()) {
            listaPasazerow.add(pasazer);
        } else {
            System.out.println("Brak miejsc do " + pasazer.getKierunek() + " lub bagaż za duży.");
            start(podajImie(),podajCelPodrozy(),podajWageBagazu());
            //liczba miejsc lub waga przekroczona

            //TODO: wypisać szczegóły obsługiwanego kierunku (ile wolnych miejsc, ile wolnych kg dla bagażu)

        }
    }



    public void czyDodacPasazera() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Czy chcesz dodać kolejnego pasażera?");
        String odpowiedz = scanner.nextLine();

        if (odpowiedz.equals("tak")) {
            start(podajImie(),podajCelPodrozy(),podajWageBagazu());

        } else if (odpowiedz.equals("nie")) {
            //TODO: stworzyć metodę i tu ją wywołać, która wypisze wszystkich pasażerów z podziałem na destynację
        } else {
            System.out.println("Błędna komenda");
            czyDodacPasazera();
        }

    }
}