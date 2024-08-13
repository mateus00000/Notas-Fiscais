package com.example.Notas.util;

public class ValidaEmail {
    public static boolean validarCaracterArroba(String input){
        return input != null && input.contains("@");
    }
}
