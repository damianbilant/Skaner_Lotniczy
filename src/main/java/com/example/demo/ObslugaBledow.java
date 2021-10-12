package com.example.demo;


import java.util.InputMismatchException;

public class ObslugaBledow extends InputMismatchException {
private  TypBledu typBledu;
private  String opisBledu;

public ObslugaBledow (TypBledu typBledu, String opisBledu){
    this.typBledu = typBledu;
    this.opisBledu = opisBledu;
}
}


