package com.example.demo;


import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service


public class LotSerwis {




    List<Pasazer> listaPasazerow = new ArrayList<>();

    List<Kierunek> wszystkieKierunki = new ArrayList<>();

    KierunekSerwis kierunekSerwis = new KierunekSerwis();

    public List<Kierunek> dodajKierunki() {
        wszystkieKierunki.add(kierunekSerwis.kierunek1);
        wszystkieKierunki.add(kierunekSerwis.kierunek2);
        wszystkieKierunki.add(kierunekSerwis.kierunek3);
        wszystkieKierunki.add(kierunekSerwis.kierunek4);
        wszystkieKierunki.add(kierunekSerwis.kierunek5);

        return wszystkieKierunki;
    }





    public void konfiguracja() {
        dodajKierunki();

    }


    public String podajImie() {
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

    public Double podajWageBagazu() throws ObslugaBledow {
        System.out.println("Proszę podać wagę bagażu:");
        Scanner scanner = new Scanner(System.in);
        Double waga = 0.0;
        try {
             waga = scanner.nextDouble();


            return waga;
        } catch (Exception e) {
            new ObslugaBledow(TypBledu.BRAK_WARTOSCI, "Waga musi być podana");
        }
        return waga;
    }

    public void start(String imie, String cel, Double waga) throws ObslugaBledow {
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

    public void sprawdzStatus(Kierunek kierunek, Pasazer pasazer, Samolot samolot) throws ObslugaBledow {
        if (kierunek.getStatus().equals(Status.OBSLUGIWANY)) {
            sprawdzWageLiczbeMiejsc(pasazer, samolot);
            czyDodacPasazera();
        } else {
            System.out.println("Obslugujemy tylko kierunki:");
            for (Kierunek destynacja : wszystkieKierunki) {
                if (destynacja.getStatus().equals(Status.OBSLUGIWANY)) {
                    System.out.println(destynacja.getDestynacja());
                }
            }
            System.out.println("Konieczna zmiana kierunku i podanie jeszcze raz danych");
            start(podajImie(), podajCelPodrozy(), podajWageBagazu());
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

    public void sprawdzWageLiczbeMiejsc(Pasazer pasazer, Samolot samolot) throws ObslugaBledow {

        Integer jedenKierunek = liczbaPodroznych(pasazer);
        Double jednaWaga = wagaBagazy(pasazer);

        if (jedenKierunek + 1 <= samolot.getLiczbaMiejsc()) {
            if (jednaWaga + pasazer.getWagaBagazu() <= samolot.getMaxWagaBagazu()) {
                listaPasazerow.add(pasazer);
                wypisaniePasazerow();
            } else {
                System.out.println("Brak wolnego bagażu do " + pasazer.getKierunek());
                szczegolyKierunkuWaga(pasazer);
                szczegolyKierunkuMiejsce(pasazer);
            }
        } else {
            System.out.println("Brak miejsc do " + pasazer.getKierunek());
            szczegolyKierunkuMiejsce(pasazer);
            start(podajImie(), podajCelPodrozy(), podajWageBagazu());


        }
    }


    public void szczegolyKierunkuMiejsce(Pasazer pasazer) {
        Integer liczbaWolnychMiejsc = 0;
        for (Kierunek kierunek : wszystkieKierunki) {
            if (kierunek.getDestynacja().equals(pasazer.getKierunek())) {
                Integer liczbaMiejsc = kierunek.getSamolot().getLiczbaMiejsc();
                Integer liczbaPodroznych = liczbaPodroznych(pasazer);
                liczbaWolnychMiejsc = liczbaMiejsc - liczbaPodroznych;
            }
        }
        System.out.println("Liczba wolnych miejsc do " + pasazer.getKierunek() + ": " + liczbaWolnychMiejsc);
    }

    public void szczegolyKierunkuWaga(Pasazer pasazer) {
        Double wagaWolnegoBagazu = 0.0;
        for (Kierunek kierunek : wszystkieKierunki) {
            if (kierunek.getDestynacja().equals(pasazer.getKierunek())) {
                Double wagaBagazy = kierunek.getSamolot().getMaxWagaBagazu();
                Double wagaBagazu = wagaBagazy(pasazer);
                wagaWolnegoBagazu = wagaBagazy - wagaBagazu;
                if (wagaWolnegoBagazu < 0.0) {
                    wagaWolnegoBagazu = 0.0;
                }
            }
        }
        System.out.println("Liczba kg wolnego bagażu do " + pasazer.getKierunek() + ": " + wagaWolnegoBagazu);
    }


    public void wypisaniePasazerow() {
        for (Kierunek kierunek : wszystkieKierunki) {
            if (kierunek.getStatus().equals(Status.OBSLUGIWANY)) {
                System.out.println("Pasażerowie lecący do " + kierunek.getDestynacja() + ":");
                for (Pasazer pasazer : listaPasazerow) {
                    if (pasazer.getKierunek().equals(kierunek.getDestynacja())) {
                        System.out.println(pasazer.getImie());
                    }

                }
            }

        }
    }
        /*public void wypisaniePasazerow2(){
            for (Kierunek kierunek : wszystkieKierunki) {
                if (kierunek.getStatus().equals(Status.NIEOBSLUGIWANY)) {
                    System.out.println("Nic");
                } else if (kierunek.getStatus().equals(Status.ZAWIESZONY)) {
                    System.out.println("Nic");
                } else {
                    System.out.println("Pasażerowie lecący do " + kierunek.getDestynacja() + ":");
                    for (Pasazer pasazer : listaPasazerow) {
                        if (pasazer.getKierunek().equals(kierunek.getDestynacja())) {
                            System.out.println(pasazer.getImie());
                        }

                    }
                }

            }
        }*/
    /*public void wypisywanie (){
        for (int i =0; i<6; i++){
            //System.out.println(i);
            for(int j=0; j<2; j++){
                System.out.println(i +" "+ j + " Druga pętla");
            }
        }
    }*/

    public void czyDodacPasazera() throws ObslugaBledow {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Czy chcesz dodać kolejnego pasażera?");
        String odpowiedz = scanner.nextLine();

        if (odpowiedz.equals("tak")) {
            start(podajImie(), podajCelPodrozy(), podajWageBagazu());

        } else if (odpowiedz.equals("nie")) {
            wypisaniePasazerow();

        } else {
            System.out.println("Błędna komenda");
            czyDodacPasazera();
        }
    }
}

