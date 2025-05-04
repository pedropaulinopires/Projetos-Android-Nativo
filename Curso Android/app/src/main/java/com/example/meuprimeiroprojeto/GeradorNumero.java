package com.example.meuprimeiroprojeto;

import java.util.Random;
public class GeradorNumero {

    public static String GeradorNumero(){
        return String.valueOf(new Random().nextInt(10));
    }

}
