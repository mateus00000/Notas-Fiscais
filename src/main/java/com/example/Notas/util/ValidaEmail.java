package com.example.Notas.util;

public abstract  class ValidaEmail {
    public static boolean validarCaracterArroba(String input){
        return input != null && input.contains("@");
    }
}
