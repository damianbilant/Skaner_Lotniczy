package com.example.demo;


import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service

public class LotSerwis {


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


    //TODO: po każdym dodaniu pasażera musi sprawdzić czy waga i ilość miejsc dla wszystkich pasażerów którzy lecą w tym samym kierunku w jakimś samolocie jest dopuszczalna

    //TODO: użyć SWITCH


    public String podajImie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Proszę podać imię");
        String imie = scanner.nextLine();
        return imie;
    }


    public void podajCelPodrozy(String imie) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Proszę podać cel podróży");

        String cel = scanner.nextLine();
        System.out.println("Proszę podać wagę bagażu:");
        Double waga = scanner.nextDouble();
        Pasazer pasazer = new Pasazer(imie, cel, waga);
        Kierunek destynacja = sprawdzKierunek(cel);
        sprawdzStatus(destynacja, pasazer, destynacja.getSamolot());
    }

    public Kierunek sprawdzKierunek(String cel) {
        Kierunek kierunek = null;
        for (Kierunek destynacja : wszystkieKierunki) {
            if (kierunek.getDestynacja().equals(cel)) {
                kierunek = destynacja;
            }
        }
        return kierunek;
    }

    public void sprawdzStatus(Kierunek kierunek, Pasazer pasazer, Samolot samolot) {
        if (kierunek.getStatus().equals(Status.OBSLUGIWANY)) {
            sprawdzWageLiczbeMiejsc(pasazer, samolot);
        } else {
            System.out.println("Obslugujemy tylko kierunki:");
            for (Kierunek destynacja : wszystkieKierunki) {
                if (kierunek.getStatus().equals(Status.OBSLUGIWANY)) {
                    System.out.println(destynacja.getDestynacja());
                }
            }
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
            //liczba miejsc lub waga przekroczona
        }
    }


        /*public List<Pasazer> dodajPasazera (Pasazer pasazer){
            listaPasazerow.add(pasazer);
            return listaPasazerow;

        }

        public void wypiszPasazerow (List < Pasazer > listaPasazerow) {
            System.out.println("Lista pasażerów to:");
            for (Pasazer pasazer : listaPasazerow) {
                System.out.println(pasazer.getImie());
            }


        }*/


     /*public void czyDodacPasazera(List<Pasazer> listaPasazerow){
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
//    }*/
//    }
    }